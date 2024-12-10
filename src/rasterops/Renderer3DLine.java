package rasterops;

import objectdata.Object3D;
import objectdata.Point2D;
import objectdata.Scene;
import rasterdata.Raster;
import transforms.Mat4;
import transforms.Point3D;
import transforms.Vec2D;
import models.Line;

import java.util.List;

// transformační řetězec - umístění objektu do scény (transformace)
// Transformace modelovací - upraví objekt ze základního tvaru -> posun souřadnic objektu přecházíme do soustavy souřadnic světa
// Pohledová matice - Posun objektů dle kamery (posun ze souřadnic světa do souřadnic pohledu) - kamera v bodu 0
// Transformace zobrazovacího objemu (ortogonální a perspektivní)
// Ořezání do okna (filtrování objektu v našem případě)
// Dehomogenizace

public class Renderer3DLine {
    public void renderScene(Scene scene, Raster raster, Mat4 viewMatrix, Mat4 projectionMatrix, Liner liner) {
        Mat4 viewProjectionMatrix = viewMatrix.mul(projectionMatrix);
        List<Object3D> objects =  scene.getObjects();
        for (int i = 0; i < objects.size(); i++) {
            // 1. get object
            Object3D object = objects.get(i);
            Mat4 objectTransformationMatrix = object.getModelMat().mul(viewProjectionMatrix);

            // 2. transformations
            List<Point3D> transformedVertexes = object
                    .getVertexBuffer()
                    .stream()
                    .map(p -> p.mul(objectTransformationMatrix))
                    .toList();

            // 3. assemble lines from points
            List<Integer> indexBuffer = object.getIndexBuffer();
            for (int k = 1; k < object.getIndexBuffer().size(); k += 2) {
                Point3D start = transformedVertexes.get(indexBuffer.get(k - 1));
                Point3D end = transformedVertexes.get(indexBuffer.get(k));

                // 4. clipping
                if (
                        start.getX() < -start.getW() || start.getX() > start.getW() ||
                        start.getY() < -start.getW() || start.getY() > start.getW() ||
                        start.getZ() < 0 || start.getZ() > start.getW() ||
                        end.getX() < -end.getW() || end.getX() > end.getW() ||
                        end.getY() < -end.getW() || end.getY() > end.getW() ||
                        end.getZ() < 0 || end.getZ() > end.getW()
                ) {
                    continue;
                }

                // 5. dehomogenizace
                start.dehomog().ifPresent(startPoint -> {
                    end.dehomog().ifPresent(endPoint -> {
                        // 6. 2D projection
                        Vec2D first = startPoint.ignoreZ();
                        Vec2D second = endPoint.ignoreZ();

                        // 7. Viewport transformation
                        Vec2D firstInViewport = pointToViewport(raster, first);
                        Vec2D secondInViewport = pointToViewport(raster, second);

                        // 8. Rasterization (liner.draw)
                        Line line = new Line(
                                new Point2D(firstInViewport.getX(), firstInViewport.getY()),
                                new Point2D(secondInViewport.getX(), secondInViewport.getY()),
                                object.getColor(),
                                1
                                );
                        liner.draw(raster, line);
                        System.out.println("is called");
                    });
                });
            }
        }
    }

    private Vec2D pointToViewport(Raster raster, Vec2D point) {
        // camera transformation
        return new Vec2D(
                // Transform x from <-1,1> to <0,width>
                (point.getX() + 1) * raster.getWidth() / 2,
                // Transform y from <-1,1> to <0,height> with y-axis flip
                (-point.getY() + 1) * raster.getHeight() / 2
        );
    }
}
