package objectdata;

import transforms.Cubic;
import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Defines curve for scenes.
 */
public class Curve extends Object3D {
    private final Point3D[] controlPoints;
    private final int numberOfPoints;
    private final Mat4 cubicMat;

    public Curve(Point3D[] controlPoints, int numberOfPoints, Mat4 cubicMat, int color, Optional<Mat4> modelMat) {
        super(
                getBezierPoints(numberOfPoints, cubicMat, controlPoints),
                getIndexVerticesByParameter(numberOfPoints),
                modelMat.orElseGet(Mat4Identity::new),
                color
        );

        this.controlPoints = controlPoints;
        this.numberOfPoints = numberOfPoints;
        this.cubicMat = cubicMat;
    }

    private static List<Vertex> getBezierPoints(int numberOfPoints, Mat4 cubicMat, Point3D[] controlPoints) {
        Cubic cubic = new Cubic(cubicMat, controlPoints);
        ArrayList<Vertex> calculatedPoints = new ArrayList<>();

        for (int i = 0; i <= numberOfPoints; i++) {
            double t = (double) i / numberOfPoints;
            Point3D point = cubic.compute(t);
            calculatedPoints.add(new Vertex(point.getX(), point.getY(), point.getZ()));
        }

        return calculatedPoints;
    }

    private static List<Integer> getIndexVerticesByParameter(int numberOfPoints) {
        ArrayList<Integer> indexVertices = new ArrayList<>();
        for (int i = 0; i < numberOfPoints; i++) {
            indexVertices.add(i);
            indexVertices.add(i + 1);
        }
        return indexVertices;
    }

    @Override
    public Curve withColor(int newColor) {
        return new Curve(this.controlPoints, this.numberOfPoints, this.cubicMat, newColor, Optional.of(this.getModelMat()));
    }

    public Curve withNumberOfPointsDerivative(int numberOfNewPoints) {
        int newNumberOfPoints = this.numberOfPoints + numberOfNewPoints;
        if (1 > newNumberOfPoints) {
            newNumberOfPoints = 1;
        }

        if (25 < newNumberOfPoints) {
            newNumberOfPoints = 25;
        }

        return new Curve(
                this.controlPoints,
                newNumberOfPoints,
                this.cubicMat,
                this.color,
                Optional.of(this.getModelMat())
        );
    }
}
