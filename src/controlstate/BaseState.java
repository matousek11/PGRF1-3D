package controlstate;

import enums.AxisEnum;
import enums.DirectionEnum;
import objectdata.*;
import objectdata.CameraService;
import rasterdata.Raster;
import rasterops.*;
import transforms.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Base class for new control state implementation
 */
public class BaseState implements Animate, State {
    protected JPanel panel;
    protected Raster raster;
    protected Liner liner;
    protected int defaultBgColor = 0x000000;
    protected Scene scene;
    protected CameraService cameraService;
    private int x, y;
    protected final float moveSpeed = 0.2F;
    private final Renderer3DLine renderer3DLine;
    private DirectionEnum observerMoveDirection = DirectionEnum.NONE;
    private boolean isRotating = false;


    public BaseState(Raster raster, JPanel panel, Liner liner, Vec3D cameraStartingPosition) {
        this.raster = raster;
        this.panel = panel;
        this.liner = liner;
        scene = new Scene();
        renderer3DLine = new Renderer3DLine();

        // position for ortho
        //observerPosition = new Vec3D(0, 0, 0);
        this.cameraService = new CameraService(cameraStartingPosition);


        // axis
        scene.addObject(new Line(new Vertex(0, 0, 0), new Vertex(3, 0, 0), 0xff0000, false));
        scene.addObject(new Line(new Vertex(0, 0, 0), new Vertex(0, 3, 0), 0x00ff00, false));
        scene.addObject(new Line(new Vertex(0, 0, 0), new Vertex(0, 0, 3), 0x0000ff, false));
    }

    @Override
    public void mousePressed(MouseEvent e) throws Exception {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) throws Exception {

    }

    @Override
    public void mouseDragged(MouseEvent e) throws Exception {
        double dx = x - e.getX();
        double dy = y - e.getY();
        cameraService.changeCameraView(dx, dy);
        repaintObjects();
        panel.repaint();

        x = e.getX();
        y = e.getY();
    }

    @Override
    public void keyPressed(KeyEvent e) throws Exception {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_C:
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
            case KeyEvent.VK_I:
                isRotating = !isRotating;
                break;
            case KeyEvent.VK_X:
                repaintObjects();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
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

    public void moveCamera() {
        cameraService.moveCamera(observerMoveDirection);
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
                cameraService.getCamera().getViewMatrix(),
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

    @Override
    public void animateObjects() {

    }
}
