package controlstate;

import objectdata.Cube;
import objectdata.Curve;
import objectdata.Pyramid;
import rasterdata.Raster;
import rasterops.Liner;
import transforms.Cubic;
import transforms.Point3D;
import transforms.Vec3D;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class HouseStateWithCubic extends BaseState {
    private int numberOfPointsInCubic = 5;

    public HouseStateWithCubic(Raster raster, JPanel panel, Liner liner) {
        super(raster, panel, liner, new Vec3D(3.5, 2, 3));

        scene.addObject(new Cube());
        scene.addObject(new Pyramid());

        Point3D[] controlPoints = {
                new Point3D(-1, -1, -1),
                new Point3D(1, -4, 1),
                new Point3D(1, -2, 1),
                new Point3D(1, -1, 1)
        };

        scene.addObject(new Curve(controlPoints, numberOfPointsInCubic, Cubic.BEZIER, 0x00ffff));
        scene.addObject(new Curve(controlPoints, numberOfPointsInCubic, Cubic.COONS, 0xff00ff));
        scene.addObject(new Curve(controlPoints, numberOfPointsInCubic, Cubic.FERGUSON,0x0000ff));
    }

    @Override
    public void keyPressed(KeyEvent e) throws Exception {
        switch (e.getKeyCode()) {
            /*case KeyEvent.VK_Z:
                // less points in cubic
                if (numberOfPointsInCubic > 0) {
                    numberOfPointsInCubic--;
                    repaintObjects();
                }
                break;
            case KeyEvent.VK_U:
                // more points in cubic
                if (numberOfPointsInCubic < 500) {
                    numberOfPointsInCubic++;
                    repaintObjects();
                }
                break;*/
        }

        super.keyPressed(e);
    }
}
