package controlstate;

import enums.AxisEnum;
import enums.DirectionEnum;
import objectdata.*;
import rasterdata.Raster;
import rasterops.*;
import transforms.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Base class for new control state implementation
 */
public class BaseState implements State {
    protected JPanel panel;
    protected Raster raster;
    protected Liner liner;
    protected int defaultBgColor = 0x000000;
    protected Scene scene;
    protected Camera camera;
    private int x, y;
    private final float moveSpeed = 0.2F;
    private final Renderer3DLine renderer3DLine;
    protected DirectionEnum observerMoveDirection = DirectionEnum.NONE;


    public BaseState(Raster raster, JPanel panel, Liner liner) {
        this.raster = raster;
        this.panel = panel;
        this.liner = liner;
        scene = new Scene();

        Timer moveTimer = new Timer(17, e -> {
            try {
                moveCamera();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        moveTimer.start();

        renderer3DLine = new Renderer3DLine();
        scene.addObject(new Cube());
        scene.addObject(new Pyramid());

        // axis
        scene.addObject(new Line(new Vertex(0, 0, 0), new Vertex(3, 0, 0), 0xff0000, false));
        scene.addObject(new Line(new Vertex(0, 0, 0), new Vertex(0, 3, 0), 0x00ff00, false));
        scene.addObject(new Line(new Vertex(0, 0, 0), new Vertex(0, 0, 3), 0x0000ff, false));

        Vec3D observerPosition = new Vec3D(3.5, 2, 3);
        this.camera = new Camera()
                .withPosition(observerPosition)
                .withAzimuth(azimuthToOrigin(observerPosition))
                .withZenith(-zenithToOrigin(observerPosition));
    }

    @Override
    public void mousePressed(MouseEvent e, ArrayList<Object> objects) throws Exception {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e, ArrayList<Object> objects) throws Exception {

    }

    @Override
    public void mouseDragged(MouseEvent e, ArrayList<Object> objects) throws Exception {
        double dx = x - e.getX();
        double dy = y - e.getY();
        camera = camera.addAzimuth(dx / 100).addZenith(dy / 100);
        repaintObjects();
        panel.repaint();

        x = e.getX();
        y = e.getY();
    }

    @Override
    public void keyPressed(KeyEvent e, ArrayList<Object> objects) throws Exception {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_C:
                objects.clear();
                clear();
                panel.repaint();
                break;
            case KeyEvent.VK_LEFT:
                // to the left
                scene.translateAllObjects(-moveSpeed, AxisEnum.X);
                repaintObjects();
                break;
            case KeyEvent.VK_RIGHT:
                // to the right
                scene.translateAllObjects(moveSpeed, AxisEnum.X);
                repaintObjects();
                break;
            case KeyEvent.VK_UP:
                // more far
                scene.translateAllObjects(-moveSpeed, AxisEnum.Y);
                repaintObjects();
                break;
            case KeyEvent.VK_DOWN:
                // closer
                scene.translateAllObjects(moveSpeed, AxisEnum.Y);
                repaintObjects();
                break;
            case KeyEvent.VK_Q:
                scene.rotateAllObjects(moveSpeed, AxisEnum.Z);
                repaintObjects();
                break;
            case KeyEvent.VK_E:
                scene.rotateAllObjects(-moveSpeed, AxisEnum.Z);
                repaintObjects();
                break;
            case KeyEvent.VK_R:
                scene.scaleAllObjects(1.1F);
                repaintObjects();
                break;
            case KeyEvent.VK_F:
                scene.scaleAllObjects(0.9F);
                repaintObjects();
                break;
            case KeyEvent.VK_W:
                observerMoveDirection = DirectionEnum.FORWARD;
                break;
            case KeyEvent.VK_S:
                observerMoveDirection = DirectionEnum.BACKWARD;
                break;
            case KeyEvent.VK_A:
                observerMoveDirection = DirectionEnum.LEFT;
                break;
            case KeyEvent.VK_D:
                observerMoveDirection = DirectionEnum.RIGHT;
                break;
            case KeyEvent.VK_SHIFT:
                observerMoveDirection = DirectionEnum.UP;
                break;
            case KeyEvent.VK_CONTROL:
                observerMoveDirection = DirectionEnum.DOWN;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e, ArrayList<Object> objects) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W,
                 KeyEvent.VK_S,
                 KeyEvent.VK_A,
                 KeyEvent.VK_D,
                 KeyEvent.VK_SHIFT,
                 KeyEvent.VK_CONTROL:
                    observerMoveDirection = DirectionEnum.NONE;
                break;
        }
    }

    private void moveCamera() throws Exception {
        if (observerMoveDirection == DirectionEnum.NONE) {
            return;
        }

        camera = switch(observerMoveDirection) {
            case DirectionEnum.FORWARD -> camera.forward(moveSpeed);
            case DirectionEnum.BACKWARD -> camera.backward(moveSpeed);
            case DirectionEnum.LEFT -> camera.left(moveSpeed);
            case DirectionEnum.RIGHT -> camera.right(moveSpeed);
            case DirectionEnum.UP -> camera.up(moveSpeed);
            case DirectionEnum.DOWN -> camera.down(moveSpeed);
            default -> throw new IllegalArgumentException("Should not reach here");
        };

        repaintObjects();
    }

    /**
     * Clears raster, draw objects from objects array and repaint it on panel.
     */
    protected void repaintObjects() {
        clear();
        boolean isPerspectiveMat = true;
        Mat4 secondView = new Mat4OrthoRH(panel.getWidth() * 0.01, panel.getHeight() * 0.01, 0.01, 25);
        Mat4 viewMat = isPerspectiveMat ?
                new Mat4PerspRH(Math.PI / 2, (double) (raster.getWidth() / raster.getHeight()), 0.01, 20) :
                secondView;

        renderer3DLine.renderScene(
                scene,
                raster,
                camera.getViewMatrix(),
                viewMat,
                liner
        );
        panel.repaint();
    }

    /**
     * Clear raster and fill it with default background color.
     */
    protected void clear() {
        raster.clear(defaultBgColor);
    }

    protected double zenithToOrigin(Vec3D observerPosition) {
        Vec3D viewVector = observerPosition.opposite();
        return viewVector
                .normalized()
                .map(normalizedViewVector -> {
                    double alpha = normalizedViewVector.dot(new Vec3D(0,0,1));
                    return Math.PI / 2 - alpha;
                }).orElse(0.0);
    }

    protected double azimuthToOrigin(Vec3D observerPosition) {
        Vec3D viewVector = observerPosition.opposite();
        return viewVector
                .withZ(0)
                .normalized()
                .map(viewProjection -> {
                    double angle = Math.acos(viewProjection.dot(new Vec3D(0,0,1)));
                    return (viewProjection.getY() > 0) ? angle : 2 * Math.PI - angle;
                }).orElse(0.0);
    }
}
