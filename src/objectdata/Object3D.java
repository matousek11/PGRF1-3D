package objectdata;

import transforms.Mat4;
import transforms.Point3D;

import java.util.ArrayList;
import java.util.List;

public class Object3D {
    private final List<Vertex> vertexBuffer;
    private final List<Integer> indexBuffer;
    private final Mat4 modelMat;
    protected int color = 0xffffff;

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
    public Object3D translate(double dx, double dy, double dz) {
        ArrayList<Vertex> newVertexes = new ArrayList<>();

        // Translate each point in the polygon by dx, dy and dz
        for (Vertex vertex : vertexBuffer) {
            newVertexes.add(vertex.translate(dx, dy, dz));
        }

        return new Object3D(newVertexes, indexBuffer, modelMat, color);
    }
}
