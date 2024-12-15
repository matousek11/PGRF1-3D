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
import java.util.Optional;

/**
 * Scene with 3D house and cubic curves around.
 */
public class HouseStateWithCubic extends BaseState {
    private final int numberOfPointsInCubic = 6;

    public HouseStateWithCubic(Raster raster, JPanel panel, Liner liner) {
        super(raster, panel, liner, new Vec3D(3.5, 2, 3));

        scene.addObject(new Cube());
        scene.addObject(new Pyramid(Optional.empty(), Optional.empty()));

        Point3D[] controlPoints = {
                new Point3D(-1, -1, -1),
                new Point3D(1, -4, 1),
                new Point3D(1, -2, 1),
                new Point3D(1, -1, 1)
        };

        scene.addObject(new Curve(controlPoints, numberOfPointsInCubic, Cubic.BEZIER, 0x00ffff, Optional.empty()));
        scene.addObject(new Curve(controlPoints, numberOfPointsInCubic, Cubic.COONS, 0xff00ff, Optional.empty()));
        scene.addObject(new Curve(controlPoints, numberOfPointsInCubic, Cubic.FERGUSON,0x0000ff, Optional.empty()));
    }

    @Override
    public void keyPressed(KeyEvent e) throws Exception {
        super.keyPressed(e);
    }
}
