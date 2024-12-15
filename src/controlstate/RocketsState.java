package controlstate;

import enums.AxisEnum;
import objectdata.Axe;
import objectdata.Spaceship;
import objectdata.Starship;
import rasterdata.Raster;
import rasterops.Liner;
import transforms.Vec3D;

import javax.swing.*;
import java.util.Optional;

public class RocketsState extends BaseState {
    private int startTime = 0;

    public RocketsState(Raster raster, JPanel panel, Liner liner) {
        super(raster, panel, liner, new Vec3D(2.5, 10, 6));

        scene.addObject(new Spaceship(Optional.empty(), Optional.empty()));
        scene.addObject(new Starship(Optional.empty(), Optional.empty()));
        scene.addObject(new Axe());
    }

    @Override
    public void animateObjects () {
        scene.rotateAllObjects(0.01F, AxisEnum.Z);
        startTime++;
        if (startTime > 1000) {
            scene.translateAllObjects(moveSpeed * ((float) startTime / 6000), AxisEnum.Z);
        }
        repaintObjects();
    }
}
