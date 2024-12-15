package objectdata;

import transforms.Mat4;
import transforms.Mat4Identity;

import java.util.List;
import java.util.Optional;

public class Starship extends Object3D {
    public Starship(Optional<Mat4> modelMat, Optional<Integer> color) {
        super(
                // vertex buffer
                List.of(
                        // tip
                        new Vertex(4, 0, 8.5),

                        // first ring
                        new Vertex(4.05, 0, 8.3), // 1
                        new Vertex(4.035, 0.035, 8.3), // 2
                        new Vertex(4, 0.05, 8.3), // 3
                        new Vertex(3.965, 0.035, 8.3), // 4
                        new Vertex(3.95, 0, 8.3), // 5
                        new Vertex(3.965, -0.035, 8.3), // 6
                        new Vertex(4, -0.05, 8.3), // 7
                        new Vertex(4.035, -0.035, 8.3), // 8

                        // second ring
                        new Vertex(4.15, 0, 8), // 9
                        new Vertex(4.106, 0.106, 8), // 10
                        new Vertex(4, 0.15, 8), // 11
                        new Vertex(3.894, 0.106, 8), // 12
                        new Vertex(3.85, 0, 8), // 13
                        new Vertex(3.894, -0.106, 8), // 14
                        new Vertex(4, -0.15, 8), // 15
                        new Vertex(4.106, -0.106, 8), // 16

                        // third ring
                        new Vertex(4.25, 0, 7.5), // 17
                        new Vertex(4.18, 0.18, 7.5), // 18
                        new Vertex(4, 0.25, 7.5), // 19
                        new Vertex(3.82, 0.18, 7.5), // 20
                        new Vertex(3.75, 0, 7.5), // 21
                        new Vertex(3.82, -0.18, 7.5), // 22
                        new Vertex(4, -0.25, 7.5), // 23
                        new Vertex(4.18, -0.18, 7.5), // 24

                        // top part of fuselage
                        new Vertex(4.5, 0, 6), // 25
                        new Vertex(4.35, 0.35, 6), // 26
                        new Vertex(4, 0.5, 6), // 27
                        new Vertex(3.65, 0.35, 6), // 28
                        new Vertex(3.5, 0, 6), // 29
                        new Vertex(3.65, -0.35, 6), // 30
                        new Vertex(4, -0.5, 6), // 31
                        new Vertex(4.35, -0.35, 6), // 32

                        // middle part of fuselage
                        new Vertex(4.5, 0, 0), // 33
                        new Vertex(4.35, 0.35, 0), // 34
                        new Vertex(4, 0.5, 0), // 35
                        new Vertex(3.65, 0.35, 0), // 36
                        new Vertex(3.5, 0, 0), // 37
                        new Vertex(3.65, -0.35, 0), // 38
                        new Vertex(4, -0.5, 0), // 39
                        new Vertex(4.35, -0.35, 0), // 40

                        // bottom part of fuselage
                        new Vertex(4.5, 0, -3), // 41
                        new Vertex(4.35, 0.35, -3), // 42
                        new Vertex(4, 0.5, -3), // 43
                        new Vertex(3.65, 0.35, -3), // 44
                        new Vertex(3.5, 0, -3), // 45
                        new Vertex(3.65, -0.35, -3), // 46
                        new Vertex(4, -0.5, -3), // 47
                        new Vertex(4.35, -0.35, -3), // 48

                        // front flaps
                        // left flap
                        new Vertex(3.1, 0.2, 5), // 49
                        new Vertex(3.1, 0.5, 4.5), // 50
                        new Vertex(3.1, 0.6, 4), // 51
                        new Vertex(3.1, 0.5, 3.5), // 52
                        new Vertex(3.1, 0.2, 3), // 53
                        // right flap
                        new Vertex(4.9, 0.2, 5), // 54
                        new Vertex(4.9, 0.5, 4.5), // 55
                        new Vertex(4.9, 0.6, 4), // 56
                        new Vertex(4.9, 0.5, 3.5), // 57
                        new Vertex(4.9, 0.2, 3), // 58

                        // back flaps
                        // left flap
                        new Vertex(3.1, 0.2, -2), // 59
                        new Vertex(3.1, 0.5, -2.2), // 60
                        new Vertex(3.1, 0.6, -2.4), // 61
                        new Vertex(3.1, 0.5, -2.6), // 62
                        new Vertex(3.1, 0.2, -2.8), // 63
                        // right flap
                        new Vertex(4.9, 0.2, -2), // 64
                        new Vertex(4.9, 0.5, -2.2), // 65
                        new Vertex(4.9, 0.6, -2.4), // 66
                        new Vertex(4.9, 0.5, -2.6), // 67
                        new Vertex(4.9, 0.2, -2.8), // 68

                        // engine section
                        new Vertex(4.5, 0, -3.5), // 69
                        new Vertex(4.35, 0.35, -3.5), // 70
                        new Vertex(4, 0.5, -3.5), // 71
                        new Vertex(3.65, 0.35, -3.5), // 72
                        new Vertex(3.5, 0, -3.5), // 73
                        new Vertex(3.65, -0.35, -3.5), // 74
                        new Vertex(4, -0.5, -3.5), // 75
                        new Vertex(4.35, -0.35, -3.5), // 76

                        // central engines
                        new Vertex(4, 0.1, -3.7), // 77
                        new Vertex(4, 0.1, -4), // 78
                        new Vertex(4, -0.1, -3.7), // 79
                        new Vertex(4, -0.1, -4), // 80
                        // outer engines
                        new Vertex(4.2, 0.2, -3.7), // 81
                        new Vertex(4.2, 0.2, -4), // 82
                        new Vertex(3.8, 0.2, -3.7), // 83
                        new Vertex(3.8, 0.2, -4), // 84
                        new Vertex(4.2, -0.2, -3.7), // 85
                        new Vertex(4.2, -0.2, -4), // 86
                        new Vertex(3.8, -0.2, -3.7), // 87
                        new Vertex(3.8, -0.2, -4), // 88

                        // service panels
                        new Vertex(4.51, 0, 2), // 89
                        new Vertex(4.51, 0, 1), // 90
                        new Vertex(4.41, 0, 2), // 91
                        new Vertex(4.41, 0, 1), // 92

                        new Vertex(3.49, 0, 2), // 93
                        new Vertex(3.49, 0, 1), // 94
                        new Vertex(3.59, 0, 2), // 95
                        new Vertex(3.59, 0, 1) // 96
                ),
                // index buffer
                List.of(
                        // tip
                        0,1, 0,2, 0,3, 0,4, 0,5, 0,6, 0,7, 0,8,

                        // first ring
                        1,2, 2,3, 3,4, 4,5, 5,6, 6,7, 7,8, 8,1,

                        // connection of first and second ring
                        1,9, 2,10, 3,11, 4,12, 5,13, 6,14, 7,15, 8,16,

                        // second ring
                        9,10, 10,11, 11,12, 12,13, 13,14, 14,15, 15,16, 16,9,

                        // connection of second and third ring
                        9,17, 10,18, 11,19, 12,20, 13,21, 14,22, 15,23, 16,24,

                        // third ring
                        17,18, 18,19, 19,20, 20,21, 21,22, 22,23, 23,24, 24,17,

                        // fuselage
                        17,25, 18,26, 19,27, 20,28, 21,29, 22,30, 23,31, 24,32,
                        25,33, 26,34, 27,35, 28,36, 29,37, 30,38, 31,39, 32,40,
                        33,41, 34,42, 35,43, 36,44, 37,45, 38,46, 39,47, 40,48,

                        // fuselage
                        25,26, 26,27, 27,28, 28,29, 29,30, 30,31, 31,32, 32,25,
                        33,34, 34,35, 35,36, 36,37, 37,38, 38,39, 39,40, 40,33,
                        41,42, 42,43, 43,44, 44,45, 45,46, 46,47, 47,48, 48,41,

                        // front flaps
                        49,50, 50,51, 51,52, 52,53,
                        54,55, 55,56, 56,57, 57,58,

                        // back flaps
                        59,60, 60,61, 61,62, 62,63,
                        64,65, 65,66, 66,67, 67,68,

                        // engine section
                        69,70, 70,71, 71,72, 72,73, 73,74, 74,75, 75,76, 76,69,
                        41,69, 42,70, 43,71, 44,72, 45,73, 46,74, 47,75, 48,76,

                        // engines
                        77,78, 79,80, // central engines
                        81,82, 83,84, 85,86, 87,88, // outer engines

                        // service panels
                        89,90, 91,92,
                        93,94, 95,96,

                        25,33, 29,37,
                        33,41, 37,45
                ),
                modelMat.orElseGet(Mat4Identity::new),
                color.orElse(0xffffff)
        );
    }

    public Starship withColor(int newColor) {
        return new Starship(Optional.of(this.getModelMat()), Optional.of(newColor));
    }
}
