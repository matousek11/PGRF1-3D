package objectdata;

import transforms.Cubic;
import transforms.Mat4;
import transforms.Mat4Identity;
import transforms.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Curve extends Object3D {
    public Curve(Point3D[] controlPoints, int numberOfPoints, Mat4 cubicMat, int color) {
        super(
                getBezierPoints(numberOfPoints, cubicMat, controlPoints),
                getIndexVerticesByParameter(numberOfPoints),
                new Mat4Identity(),
                color
        );
    }

    private static List<Vertex> getBezierPoints(int numberOfPoints, Mat4 cubicMat, Point3D[] controlPoints) {
        Cubic cubic = new Cubic(cubicMat, controlPoints);

        ArrayList<Vertex> calculatedPoints = new ArrayList<>();
        double increment = (double) 1 / numberOfPoints;

        for (double i = 0; i <= 1; i += increment) {
            Point3D point = cubic.compute(i);
            calculatedPoints.add(new Vertex(point.getX(), point.getY(), point.getZ()));
        }

        return calculatedPoints;
    }

    private static List<Integer> getIndexVerticesByParameter(int numberOfPoints) {
        ArrayList<Integer> indexVertices = new ArrayList<>();
        double increment = (double) 1 / numberOfPoints;
        for (double i = 0; i < numberOfPoints; i += increment) {
            indexVertices.add((int) i);
            indexVertices.add((int) i + 1);
        }

        return indexVertices;
    }
}
