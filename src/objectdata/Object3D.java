package objectdata;

import transforms.Mat4;

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
}
