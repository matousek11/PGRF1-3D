package controlstate;

import objectdata.*;
import rasterdata.Raster;
import rasterops.*;
import transforms.Camera;
import transforms.Mat4PerspRH;
import transforms.Vec3D;

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
    private final Renderer3DLine renderer3DLine;


    public BaseState(Raster raster, JPanel panel, Liner liner) {
        this.raster = raster;
        this.panel = panel;
        this.liner = liner;
        scene = new Scene();

        renderer3DLine = new Renderer3DLine();
        scene.addObject(new Cube());
        scene.addObject(new Pyramid());
        // axes
        scene.addObject(new Line(new Vertex(0, 0, 0), new Vertex(3, 0, 0), 0xff0000));
        scene.addObject(new Line(new Vertex(0, 0, 0), new Vertex(0, 3, 0), 0x00ff00));
        scene.addObject(new Line(new Vertex(0, 0, 0), new Vertex(0, 0, 3), 0x0000ff));

        Vec3D observerPosition = new Vec3D(3.5, 2, 3);
        this.camera = new Camera()
                .withPosition(observerPosition)
                .withAzimuth(azimuthToOrigin(observerPosition))
                .withZenith(zenithToOrigin(observerPosition));
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
        repaintObjects(objects);
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
        }
    }

    @Override
    public void keyReleased(KeyEvent e, ArrayList<Object> objects) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SHIFT:

                break;
        }
    }

    /**
     * Draw already added objects
     *
     * @param objects objects to be drawn
     * @throws Exception Is thrown when renderer doesn't know object
     */
    protected void drawObjects(ArrayList<Object> objects) throws Exception {
        repaintObjects(objects);
    }

    /**
     * Clears raster, draw objects from objects array and repaint it on panel.
     *
     * @param objects Object that will be on panel
     * @throws Exception When unknown object was expected to be rendered
     */
    protected void repaintObjects(ArrayList<Object> objects) throws Exception {
        clear();

        renderer3DLine.renderScene(
                scene,
                raster,
                camera.getViewMatrix(),
                new Mat4PerspRH(Math.PI / 2, (double) (raster.getWidth() / raster.getHeight()), 0.01, 20),
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
