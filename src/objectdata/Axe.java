package objectdata;

import transforms.Mat4Identity;

import java.util.List;

public class Axe extends Object3D {
    public Axe() {
        super(
                // vertex buffer
                List.of(
                        // Hlavní osa Y
                        new Vertex(6, 0, -4), // začátek osy 0
                        new Vertex(6, 0, 8.5), // konec osy 1

                        // Značky po 10 metrech (každých ~0.7 jednotky)
                        // 0m
                        new Vertex(5.9, 0, -4), // 2
                        new Vertex(6.1, 0, -4), // 3
                        // 10m
                        new Vertex(5.9, 0, -3.3), // 4
                        new Vertex(6.1, 0, -3.3), // 5
                        // 20m
                        new Vertex(5.9, 0, -2.6), // 6
                        new Vertex(6.1, 0, -2.6), // 7
                        // 30m
                        new Vertex(5.9, 0, -1.9), // 8
                        new Vertex(6.1, 0, -1.9), // 9
                        // 40m
                        new Vertex(5.9, 0, -1.2), // 10
                        new Vertex(6.1, 0, -1.2), // 11
                        // 50m
                        new Vertex(5.9, 0, -0.5), // 12
                        new Vertex(6.1, 0, -0.5), // 13
                        // 60m
                        new Vertex(5.9, 0, 0.2), // 14
                        new Vertex(6.1, 0, 0.2), // 15
                        // 70m
                        new Vertex(5.9, 0, 0.9), // 16
                        new Vertex(6.1, 0, 0.9), // 17
                        // 80m
                        new Vertex(5.9, 0, 1.6), // 18
                        new Vertex(6.1, 0, 1.6), // 19
                        // 90m
                        new Vertex(5.9, 0, 2.3), // 20
                        new Vertex(6.1, 0, 2.3), // 21
                        // 100m
                        new Vertex(5.9, 0, 3.0), // 22
                        new Vertex(6.1, 0, 3.0), // 23
                        // 110m
                        new Vertex(5.9, 0, 3.7), // 24
                        new Vertex(6.1, 0, 3.7), // 25
                        // 120m
                        new Vertex(5.9, 0, 4.4), // 26
                        new Vertex(6.1, 0, 4.4) // 27
                ),
                // index buffer
                List.of(
                        // Hlavní osa
                        0,1,

                        // Značky měřítka
                        2,3,  // 0m
                        4,5,  // 10m
                        6,7,  // 20m
                        8,9,  // 30m
                        10,11,  // 40m
                        12,13,  // 50m
                        14,15,  // 60m
                        16,17,  // 70m
                        18,19,  // 80m
                        20,21,  // 90m
                        22,23,  // 100m
                        24,25,  // 110m
                        26,27   // 120m
                ),
                new Mat4Identity(),
                0xffffff,
                false
        );
    }
}
