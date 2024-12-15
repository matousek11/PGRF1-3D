package objectdata;

import enums.AxisEnum;

import java.util.ArrayList;
import java.util.Optional;

public class Scene {
    private final ArrayList<Object3D> objects;
    protected int selectedObjectIndex = 0;

    public Scene() {
        objects = new ArrayList<>();
    }

    public void addObject(Object3D object) {
        objects.add(object);
    }

    public ArrayList<Object3D> getObjects() {
        return objects;
    }

    public void selectNextObject() {
        ArrayList<Object3D> objects = getObjects();
        int objectIndex = selectedObjectIndex;
        if (objectIndex != 0) {
            objects.set(objectIndex, objects.get(objectIndex).withColor(0xffffff));
        }

        objectIndex = calculateNextObjectIndex();
        if (objectIndex != 0) {
            Object3D selectedObject = objects.get(objectIndex);
            objects.set(objectIndex, selectedObject.withColor(0xff0000));
        }
    }

    private int calculateNextObjectIndex() {
        ArrayList<Object3D> objects = getObjects();

        while (true) {
            selectedObjectIndex++;

            // maximum reached start again
            if (selectedObjectIndex >= objects.size()) {
                selectedObjectIndex = 0;
                return selectedObjectIndex;
            }

            Object3D newSelectedObject = objects.get(selectedObjectIndex);
            if (newSelectedObject.isCanBeMoved()) {
                return selectedObjectIndex;
            }
        }
    }

    public void translateObject(float scale, AxisEnum axis) {
        if (selectedObjectIndex == 0) {
            translateAllObjects(scale, axis);
            return;
        }

        translateSelectedObject(scale, axis, Optional.empty());
    }

    public void rotateObject(float angle, AxisEnum axis) {
        if (selectedObjectIndex == 0) {
            rotateAllObjects(angle, axis);
            return;
        }

        rotateSelectedObject(angle, axis, Optional.empty());
    }

    public void scaleObject(float scale) {
        if (selectedObjectIndex == 0) {
            scaleAllObjects(scale);
            return;
        }

        scaleSelectedObject(scale, Optional.empty());
    }

    public void changeCurveAccuracy(int numberOfPointsChange) {
        if (selectedObjectIndex == 0) {
            changeAllCurvesAccuracy(numberOfPointsChange);
            return;
        }

        changeCurveAccuracy(numberOfPointsChange, Optional.empty());
    }

    public void toggleRotation() {
        if (selectedObjectIndex == 0) {
            toggleAllObjectsRotation();
            return;
        }

        toggleSelectedObjectRotation(Optional.empty());
    }

    private void translateSelectedObject(float scale, AxisEnum axis, Optional<Integer> objectIndex) {
        Object3D object = objects.get(objectIndex.orElse(selectedObjectIndex));
        if (!object.isCanBeMoved()) {
            return;
        }

        object = object.translate(
                axis == AxisEnum.X ? scale : 0,
                axis == AxisEnum.Y ? scale : 0,
                axis == AxisEnum.Z ? scale : 0
        );
        objects.set(objectIndex.orElse(selectedObjectIndex), object);
    }

    private void rotateSelectedObject(float angle, AxisEnum axis, Optional<Integer> objectIndex) {
        Object3D object = objects.get(objectIndex.orElse(selectedObjectIndex));
        if (!object.isCanBeMoved()) {
            return;
        }

        object = switch (axis) {
            case X -> object.rotate(angle, AxisEnum.X);
            case Y -> object.rotate(angle, AxisEnum.Y);
            case Z -> object.rotate(angle, AxisEnum.Z);
        };
        objects.set(objectIndex.orElse(selectedObjectIndex), object);
    }

    private void scaleSelectedObject(float scale, Optional<Integer> objectIndex) {
        Object3D object = objects.get(objectIndex.orElse(selectedObjectIndex));
        if (!object.isCanBeMoved()) {
            return;
        }

        object = object.scale(scale);
        objects.set(objectIndex.orElse(selectedObjectIndex), object);
    }

    private void changeCurveAccuracy(int numberOfNewPointsChange, Optional<Integer> objectIndex) {
        Object3D object = objects.get(objectIndex.orElse(selectedObjectIndex));
        if (!object.isCanBeMoved() || !(object instanceof Curve curve)) {
            return;
        }

        object = curve.withNumberOfPointsDerivative(numberOfNewPointsChange);
        objects.set(objectIndex.orElse(selectedObjectIndex), object);
    }

    private void toggleSelectedObjectRotation(Optional<Integer> objectIndex) {
        Object3D object = objects.get(objectIndex.orElse(selectedObjectIndex));
        if (!object.isCanBeMoved()) {
            return;
        }

        object.isRotating = !object.isRotating();
        objects.set(objectIndex.orElse(selectedObjectIndex), object);
    }

    public void translateAllObjects(float scale, AxisEnum axis) {
        for (int i = 0; i < objects.size(); i++) {
            translateSelectedObject(scale, axis, Optional.of(i));
        }
    }

    public void rotateAllObjects(float angle, AxisEnum axis) {
        for (int i = 0; i < objects.size(); i++) {
            rotateSelectedObject(angle, axis, Optional.of(i));
        }
    }

    public void rotateAllApprovedObjects(float angle, AxisEnum axis) {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).isRotating()) {
                rotateSelectedObject(angle, axis, Optional.of(i));
            }
        }
    }

    public void scaleAllObjects(float scale) {
        for (int i = 0; i < objects.size(); i++) {
            scaleSelectedObject(scale, Optional.of(i));
        }
    }

    public void changeAllCurvesAccuracy(int numberOfPointsChange) {
        for (int i = 0; i < objects.size(); i++) {
            changeCurveAccuracy(numberOfPointsChange, Optional.of(i));
        }
    }

    public void toggleAllObjectsRotation() {
        for (int i = 0; i < objects.size(); i++) {
            toggleSelectedObjectRotation(Optional.of(i));
        }
    }
}
