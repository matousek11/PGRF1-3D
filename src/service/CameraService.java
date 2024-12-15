package service;

import enums.DirectionEnum;
import transforms.Camera;
import transforms.Vec3D;

public class CameraService extends transforms.Camera {
    private Camera camera;
    private double moveSpeed = 0.2F;

    public CameraService(Vec3D observerPosition) {
        this.camera = new Camera()
                .withPosition(observerPosition)
                .withAzimuth(azimuthToOrigin(observerPosition))
                .withZenith(zenithToOrigin(observerPosition));
    }

    public Camera getCamera() {
        return camera;
    }

    public void changeCameraView(double dx, double dy) {
        camera = camera.addAzimuth(dx / 100).addZenith(dy / 100);
    }

    public void moveCamera(DirectionEnum observerMoveDirection) {
        if (observerMoveDirection == DirectionEnum.NONE) {
            return;
        }

        camera = switch(observerMoveDirection) {
            case DirectionEnum.FORWARD -> camera.forward(moveSpeed);
            case DirectionEnum.BACKWARD -> camera.backward(moveSpeed);
            case DirectionEnum.LEFT -> camera.left(moveSpeed);
            case DirectionEnum.RIGHT -> camera.right(moveSpeed);
            case DirectionEnum.UP -> camera.up(moveSpeed);
            case DirectionEnum.DOWN -> camera.down(moveSpeed);
            default -> throw new IllegalArgumentException("Should not reach here");
        };
    }

    protected double zenithToOrigin(Vec3D observerPosition) {
        Vec3D viewVector = observerPosition.opposite();
        return -viewVector
                .normalized()
                .map(normalizedViewVector -> Math.acos(normalizedViewVector.dot(new Vec3D(0, 0, 1))) - Math.PI/2)
                .orElse(0.0);
    }

    protected double azimuthToOrigin(Vec3D observerPosition) {
        Vec3D viewVector = observerPosition.opposite();
        return viewVector
                .withZ(0)
                .normalized()
                .map(viewProjection -> {
                    double angle = Math.atan2(viewProjection.getY(), viewProjection.getX());
                    return (angle >= 0) ? angle : 2 * Math.PI + angle;
                }).orElse(0.0);
    }
}
