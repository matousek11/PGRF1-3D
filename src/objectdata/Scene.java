package objectdata;

import enums.AxisEnum;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final List<Object3D> objects;

    public Scene() {
        objects = new ArrayList<>();
    }

    public void addObject(Object3D object) {
        objects.add(object);
    }

    public List<Object3D> getObjects() {
        return objects;
    }

    public void translateAllObjects (float scale, AxisEnum axis) {
        for (int i = 0; i < objects.size(); i++) {
            Object3D object = objects.get(i);
            object = object.translate(
                    axis == AxisEnum.X ? scale : 0,
                    axis == AxisEnum.Y ? scale : 0,
                    axis == AxisEnum.Z ? scale : 0);
            objects.set(i, object);
        }
    }
}
