package controlstate;

import objectdata.Cube;
import objectdata.Pyramid;
import rasterdata.Raster;
import rasterops.Liner;
import transforms.Vec3D;

import javax.swing.*;
import java.util.Optional;

/**
 * Basic scene with 3D house.
 */
public class HouseState extends BaseState {
    public HouseState(Raster raster, JPanel panel, Liner liner) {
        super(raster, panel, liner, new Vec3D(3.5, 2, 3));

        scene.addObject(new Cube());
        scene.addObject(new Pyramid(Optional.empty(), Optional.empty()));
    }
}
