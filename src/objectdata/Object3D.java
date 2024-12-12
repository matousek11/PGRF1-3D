package objectdata;

import enums.AxisEnum;
import transforms.Mat4;
import transforms.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Object3D {
    private final List<Vertex> vertexBuffer;
    private final List<Integer> indexBuffer;
    private final Mat4 modelMat;
    protected int color = 0xffffff;
    protected boolean canBeMoved = true;

    public Object3D(List<Vertex> vertexBuffer, List<Integer> indexBuffer, Mat4 modelMat) {
        this.vertexBuffer = vertexBuffer;
        this.indexBuffer = indexBuffer;
        this.modelMat = modelMat;
    }

    public Object3D(List<Vertex> vertexBuffer, List<Integer> indexBuffer, Mat4 modelMat, int color) {
        this.vertexBuffer = vertexBuffer;
        this.indexBuffer = indexBuffer;
        this.modelMat = modelMat;
        this.color = color;
    }

    public Object3D(List<Vertex> vertexBuffer, List<Integer> indexBuffer, Mat4 modelMat, int color, boolean canBeMoved) {
        this.vertexBuffer = vertexBuffer;
        this.indexBuffer = indexBuffer;
        this.modelMat = modelMat;
        this.color = color;
        this.canBeMoved = canBeMoved;
    }

    public List<Vertex> getVertexBuffer() {
        return vertexBuffer;
    }

    public List<Integer> getIndexBuffer() {
        return indexBuffer;
    }

    public Mat4 getModelMat() {
        return modelMat;
    }

    public int getColor() {
        return color;
    }

    public boolean isCanBeMoved() {
        return canBeMoved;
    }

    /**
     * Returns new translated Object3D
     *
     * @param dx
     * @param dy
     * @param dz
     * @return
     */
    public Object3D translate(double dx, double dy, double dz) {
        ArrayList<Vertex> newVertexes = new ArrayList<>();

        // Translate each point in the polygon by dx, dy and dz
        for (Vertex vertex : vertexBuffer) {
            newVertexes.add(vertex.translate(dx, dy, dz));
        }

        return new Object3D(newVertexes, indexBuffer, modelMat, color);
    }

    public Object3D rotate(double angle, AxisEnum rotationAxis) {
        Point3D objectCenter = getObjectCenter();
        ArrayList<Vertex> newVertexes = new ArrayList<>();

        for (Vertex point : vertexBuffer) {
            Vertex translatedToOrigin = new Vertex(
                    point.getX() - objectCenter.getX(),
                    point.getY() - objectCenter.getY(),
                    point.getZ() - objectCenter.getZ()
            );

            Point3D rotated = switch (rotationAxis) {
                case X -> translatedToOrigin.rotateX(angle);
                case Y -> translatedToOrigin.rotateY(angle);
                case Z -> translatedToOrigin.rotateZ(angle);
            };

            newVertexes.add(new Vertex(
                    rotated.getX() + objectCenter.getX(),
                    rotated.getY() + objectCenter.getY(),
                    rotated.getZ() + objectCenter.getZ()
            ));
        }

        return new Object3D(newVertexes, indexBuffer, modelMat, color);
    }

    public Object3D scale(double scale) {
        Point3D objectCenter = getObjectCenter();
        ArrayList<Vertex> newVertexes = new ArrayList<>();

        // Scale each point around the center
        for (Vertex vertex : vertexBuffer) {
            Vertex pointTranslatedToOrigin = new Vertex(
                    vertex.getX() - objectCenter.getX(),
                    vertex.getY() - objectCenter.getY(),
                    vertex.getZ() - objectCenter.getZ()
            );
            Vertex scaledPoint = pointTranslatedToOrigin.scale(scale);
            Vertex scaledVertex = new Vertex(
                    scaledPoint.getX() + objectCenter.getX(),
                    scaledPoint.getY() + objectCenter.getY(),
                    scaledPoint.getZ() + objectCenter.getZ()
            );
            newVertexes.add(scaledVertex);
        }

        return new Object3D(newVertexes, indexBuffer, modelMat, color);
    }

    public Point3D getObjectCenter() {
        double sumX = 0;
        double sumY = 0;
        double sumZ = 0;

        for (Point3D vertex : vertexBuffer) {
            sumX += vertex.getX();
            sumY += vertex.getY();
            sumZ += vertex.getZ();
        }

        // Average the sums to get centre of 3D object
        double centerX = sumX / vertexBuffer.size();
        double centerY = sumY / vertexBuffer.size();
        double centerZ = sumZ / vertexBuffer.size();

        return new Point3D(centerX, centerY, centerZ);
    }
}
