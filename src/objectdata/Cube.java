package objectdata;

import transforms.Mat4Identity;

import java.util.List;

public class Cube extends Object3D {
    public Cube() {
        super(
                // vertex buffer
                List.of(
                        // bottom part of cube
                        new Vertex(-1, -1, -1), // 0
                        new Vertex(1, -1, -1), // 1
                        new Vertex(1, 1, -1), // 2
                        new Vertex(-1, 1, -1), // 3
                        // top part of cube
                        new Vertex(-1, -1, 1), // 4
                        new Vertex(1, -1, 1), // 5
                        new Vertex(1, 1, 1), // 6
                        new Vertex(-1, 1, 1) // 7
                ),
                // index buffer
                List.of(
                        0,1,
                        0,3,
                        0,4,
                        1,2,
                        1,5,
                        3,2,
                        3,7,
                        2,6,
                        7,6,
                        7,4,
                        5,4,
                        5,6
                ),
                // modelovací matice pro transformaci objektu
                new Mat4Identity()
        );
    }
}
