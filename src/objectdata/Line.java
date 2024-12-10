package objectdata;

import transforms.Mat4Identity;

import java.util.List;

public class Line extends Object3D {
    public Line(Vertex startPoint, Vertex endPoint) {
        super(
                List.of(
                        startPoint, // 0
                        endPoint // 1
                ),
                List.of(0,1),
                new Mat4Identity()
        );
    }

    public Line(Vertex startPoint, Vertex endPoint, int color) {
        super(
                List.of(
                        startPoint, // 0
                        endPoint // 1
                ),
                List.of(0,1),
                new Mat4Identity(),
                color
        );
    }
}