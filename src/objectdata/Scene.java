package objectdata;

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
}
