package objectdata;

import transforms.Mat4;
import transforms.Mat4Identity;

import java.util.List;
import java.util.Optional;

/**
 * 3D pyramid for scenes.
 */
public class Pyramid extends Object3D {
    public Pyramid(Optional<Mat4> modelMat, Optional<Integer> color) {
        super(
                List.of(
                        // bottom part of pyramid
                        new Vertex(-1, -1, 1), // 0
                        new Vertex(1, -1, 1), // 1
                        new Vertex(1, 1, 1), // 2
                        new Vertex(-1, 1, 1), // 3
                        // top part of pyramid
                        new Vertex(0, 0, 3) // 4
                ),
                List.of(
                        0,1,
                        0,3,
                        0,4,
                        2,1,
                        2,3,
                        2,4,
                        1,4,
                        3,4
                ),
                modelMat.orElseGet(Mat4Identity::new),
                color.orElse(0xffffff)
        );
    }

    public Pyramid withColor(int newColor) {
        return new Pyramid(Optional.of(this.getModelMat()), Optional.of(newColor));
    }
}
