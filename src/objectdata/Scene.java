package objectdata;

import enums.AxisEnum;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Defines scene where objects are rendered and processed.
 */
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

    /**
     * Selects upcoming object from scene in order to be moved or rotated.
     */
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

    /**
     * Calculates index of next object that can be edited.
     * If last object was selected 0 is returned which means all objects will be edited.
     *
     * @return index of upcoming object which can be edited.
     */
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

    /**
     * Translates all objects in scene if selected object index is 0 and just one object if selected object index is not 0.
     *
     * @param translation defines how much object will be moved.
     * @param axis defines on which axis is object moved.
     */
    public void translateObject(float translation, AxisEnum axis) {
        if (selectedObjectIndex == 0) {
            translateAllObjects(translation, axis);
            return;
        }

        translateSelectedObject(translation, axis, Optional.empty());
    }

    /**
     * Rotates all objects in scene if selected object index is 0 and just one object if selected object index is not 0.
     *
     * @param angle in radians by which object will be rotated. Object is rotated counter-clockwise
     * @param axis defines around which axis will be object rotated
     */
    public void rotateObject(float angle, AxisEnum axis) {
        if (selectedObjectIndex == 0) {
            rotateAllObjects(angle, axis);
            return;
        }

        rotateSelectedObject(angle, axis, Optional.empty());
    }

    /**
     * Scales all objects in scene if selected object index is 0 and just one object if selected object index is not 0.
     *
     * @param scale how much will be object scaled
     */
    public void scaleObject(float scale) {
        if (selectedObjectIndex == 0) {
            scaleAllObjects(scale);
            return;
        }

        scaleSelectedObject(scale, Optional.empty());
    }

    /**
     * Changes curve accuracy of all curves in scene if selected object index is 0
     * and just one curve if selected object index is not 0.
     *
     * @param numberOfPointsChange by how much should current number of points change (derivative)
     */
    public void changeCurveAccuracy(int numberOfPointsChange) {
        if (selectedObjectIndex == 0) {
            changeAllCurvesAccuracy(numberOfPointsChange);
            return;
        }

        changeCurveAccuracy(numberOfPointsChange, Optional.empty());
    }

    /**
     * Toggle automatic rotation of all objects in scene if selected object index is 0 and
     * just one object if selected object index is not 0.
     */
    public void toggleRotation() {
        if (selectedObjectIndex == 0) {
            toggleAllObjectsRotation();
            return;
        }

        toggleSelectedObjectRotation(Optional.empty());
    }

    private void translateSelectedObject(float translation, AxisEnum axis, Optional<Integer> objectIndex) {
        Object3D object = objects.get(objectIndex.orElse(selectedObjectIndex));
        if (!object.isCanBeMoved()) {
            return;
        }

        object = object.translate(
                axis == AxisEnum.X ? translation : 0,
                axis == AxisEnum.Y ? translation : 0,
                axis == AxisEnum.Z ? translation : 0
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

    public void translateAllObjects(float translation, AxisEnum axis) {
        for (int i = 0; i < objects.size(); i++) {
            translateSelectedObject(translation, axis, Optional.of(i));
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
