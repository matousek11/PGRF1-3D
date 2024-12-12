package objectdata;

import transforms.Mat4Identity;

import java.util.List;

public class Spaceship extends Object3D {
    public Spaceship() {
        super(
                // vertex buffer
                List.of(
                        // Nose cone - upravená špička
                        new Vertex(0, 0, 5), // špička 0
                        // První kruh nosu (8 bodů)
                        new Vertex(0.05, 0, 4.9), // 1
                        new Vertex(0.035, 0.035, 4.9), // 2
                        new Vertex(0, 0.05, 4.9), // 3
                        new Vertex(-0.035, 0.035, 4.9), // 4
                        new Vertex(-0.05, 0, 4.9), // 5
                        new Vertex(-0.035, -0.035, 4.9), // 6
                        new Vertex(0, -0.05, 4.9), // 7
                        new Vertex(0.035, -0.035, 4.9), // 8

                        // Druhý kruh nosu (8 bodů) - postupné rozšiřování
                        new Vertex(0.15, 0, 4.6), // 9
                        new Vertex(0.106, 0.106, 4.6), // 10
                        new Vertex(0, 0.15, 4.6), // 11
                        new Vertex(-0.106, 0.106, 4.6), // 12
                        new Vertex(-0.15, 0, 4.6), // 13
                        new Vertex(-0.106, -0.106, 4.6), // 14
                        new Vertex(0, -0.15, 4.6), // 15
                        new Vertex(0.106, -0.106, 4.6), // 16

                        // Třetí kruh nosu (8 bodů) - přechod do hlavního trupu
                        new Vertex(0.3, 0, 4), // 17
                        new Vertex(0.21, 0.21, 4), // 18
                        new Vertex(0, 0.3, 4), // 19
                        new Vertex(-0.21, 0.21, 4), // 20
                        new Vertex(-0.3, 0, 4), // 21
                        new Vertex(-0.21, -0.21, 4), // 22
                        new Vertex(0, -0.3, 4), // 23
                        new Vertex(0.21, -0.21, 4), // 24

                        // Horní část trupu - 8 bodů
                        new Vertex(0.3, 0, 3), // 25
                        new Vertex(0.21, 0.21, 3), // 26
                        new Vertex(0, 0.3, 3), // 27
                        new Vertex(-0.21, 0.21, 3), // 28
                        new Vertex(-0.3, 0, 3), // 29
                        new Vertex(-0.21, -0.21, 3), // 30
                        new Vertex(0, -0.3, 3), // 31
                        new Vertex(0.21, -0.21, 3), // 32

                        // Střední část trupu - 8 bodů
                        new Vertex(0.3, 0, 0), // 33
                        new Vertex(0.21, 0.21, 0), // 34
                        new Vertex(0, 0.3, 0), // 35
                        new Vertex(-0.21, 0.21, 0), // 36
                        new Vertex(-0.3, 0, 0), // 37
                        new Vertex(-0.21, -0.21, 0), // 38
                        new Vertex(0, -0.3, 0), // 39
                        new Vertex(0.21, -0.21, 0), // 40

                        // Spodní část trupu - 8 bodů
                        new Vertex(0.3, 0, -3), // 41
                        new Vertex(0.21, 0.21, -3), // 42
                        new Vertex(0, 0.3, -3), // 43
                        new Vertex(-0.21, 0.21, -3), // 44
                        new Vertex(-0.3, 0, -3), // 45
                        new Vertex(-0.21, -0.21, -3), // 46
                        new Vertex(0, -0.3, -3), // 47
                        new Vertex(0.21, -0.21, -3), // 48

                        // Grid fins
                        // Přední
                        new Vertex(0, 0.5, 1), // 49
                        new Vertex(0, 0.8, 1), // 50
                        new Vertex(0, 0.5, 0.7), // 51
                        new Vertex(0, 0.8, 0.7), // 52
                        // Pravý
                        new Vertex(0.5, 0, 1), // 53
                        new Vertex(0.8, 0, 1), // 54
                        new Vertex(0.5, 0, 0.7), // 55
                        new Vertex(0.8, 0, 0.7), // 56
                        // Levý
                        new Vertex(-0.5, 0, 1), // 57
                        new Vertex(-0.8, 0, 1), // 58
                        new Vertex(-0.5, 0, 0.7), // 59
                        new Vertex(-0.8, 0, 0.7), // 60

                        // Motorová sekce
                        new Vertex(0.35, 0, -3.5), // 61
                        new Vertex(0.25, 0.25, -3.5), // 62
                        new Vertex(0, 0.35, -3.5), // 63
                        new Vertex(-0.25, 0.25, -3.5), // 64
                        new Vertex(-0.35, 0, -3.5), // 65
                        new Vertex(-0.25, -0.25, -3.5), // 66
                        new Vertex(0, -0.35, -3.5), // 67
                        new Vertex(0.25, -0.25, -3.5), // 68

                        // Motory
                        new Vertex(0, 0, -3.8), // centrální 69
                        new Vertex(0, 0, -4), // 70
                        // Vnější motory
                        new Vertex(0.2, 0, -3.8), // 71
                        new Vertex(0.2, 0, -4), // 72
                        new Vertex(0.14, 0.14, -3.8), // 73
                        new Vertex(0.14, 0.14, -4), // 74
                        new Vertex(0, 0.2, -3.8), // 75
                        new Vertex(0, 0.2, -4), // 76
                        new Vertex(-0.14, 0.14, -3.8), // 77
                        new Vertex(-0.14, 0.14, -4), // 78
                        new Vertex(-0.2, 0, -3.8), // 79
                        new Vertex(-0.2, 0, -4), // 80

                        // Přistávací nohy
                        new Vertex(0.8, 0, -3.9), // 81
                        new Vertex(0, 0.8, -3.9), // 82
                        new Vertex(-0.8, 0, -3.9), // 83
                        new Vertex(0, -0.8, -3.9) // 84
                ),
                // index buffer
                List.of(
                        // Špička - jemnější napojení
                        0,1, 0,2, 0,3, 0,4, 0,5, 0,6, 0,7, 0,8,

                        // První kruh nosu
                        1,2, 2,3, 3,4, 4,5, 5,6, 6,7, 7,8, 8,1,

                        // Propojení prvního a druhého kruhu
                        1,9, 2,10, 3,11, 4,12, 5,13, 6,14, 7,15, 8,16,

                        // Druhý kruh
                        9,10, 10,11, 11,12, 12,13, 13,14, 14,15, 15,16, 16,9,

                        // Propojení druhého a třetího kruhu
                        9,17, 10,18, 11,19, 12,20, 13,21, 14,22, 15,23, 16,24,

                        // Třetí kruh
                        17,18, 18,19, 19,20, 20,21, 21,22, 22,23, 23,24, 24,17,

                        // Hlavní trup - vertikální linky
                        17,25, 18,26, 19,27, 20,28, 21,29, 22,30, 23,31, 24,32,
                        25,33, 26,34, 27,35, 28,36, 29,37, 30,38, 31,39, 32,40,
                        33,41, 34,42, 35,43, 36,44, 37,45, 38,46, 39,47, 40,48,

                        // Horizontální kruhy trupu
                        25,26, 26,27, 27,28, 28,29, 29,30, 30,31, 31,32, 32,25,
                        33,34, 34,35, 35,36, 36,37, 37,38, 38,39, 39,40, 40,33,
                        41,42, 42,43, 43,44, 44,45, 45,46, 46,47, 47,48, 48,41,

                        // Grid fins
                        49,50, 50,52, 52,51, 51,49, // přední
                        53,54, 54,56, 56,55, 55,53, // pravý
                        57,58, 58,60, 60,59, 59,57, // levý

                        // Motorová sekce
                        61,62, 62,63, 63,64, 64,65, 65,66, 66,67, 67,68, 68,61,
                        41,61, 42,62, 43,63, 44,64, 45,65, 46,66, 47,67, 48,68,

                        // Motory
                        69,70, // centrální
                        71,72, 73,74, 75,76, 77,78, 79,80, // vnější

                        // Přistávací nohy
                        41,81, 43,82, 45,83, 47,84
                ),
                new Mat4Identity()
        );
    }
}
