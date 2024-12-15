package objectdata;

import transforms.Mat4;
import transforms.Mat4Identity;

import java.util.List;

public class Line extends Object3D {
    public Line(Vertex startPoint, Vertex endPoint, int color, boolean canBeMoved) {
        super(
                List.of(
                        startPoint, // 0
                        endPoint // 1
                ),
                List.of(0,1),
                new Mat4Identity(),
                color,
                canBeMoved
        );
    }

    public Line(List<Vertex> vertexBuffer, int color, boolean canBeMoved, Mat4 modelMat) {
        super(vertexBuffer, List.of(0,1), modelMat, color, canBeMoved);
    }

    @Override
    public Object3D withColor(int newColor) {
        return new Line(this.getVertexBuffer(), newColor, this.canBeMoved, this.getModelMat());
    }
}
