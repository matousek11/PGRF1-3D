package objectdata;

import transforms.Point3D;

public class Vertex extends Point3D {
    private final double w;

    private final int color;

    public Vertex(double x, double y, double z) {
        super(x, y, z);
        this.color = 0xffffff;
        this.w = 1.0;
    }

    public double getX() {
        return super.getX();
    }

    public double getY() {
        return super.getY();
    }

    public double getZ() {
        return super.getZ();
    }

    public double getW() {
        return w;
    }

    public int getColor() {
        return color;
    }

    public Vertex translate(double dx, double dy, double dz) {
        return new Vertex(super.getX() + dx, super.getY() + dy, super.getZ() + dz);
    }

    public Vertex rotateX(double angle) {
        return new Vertex(
                super.getX(),
                Math.cos(angle) * super.getY() - Math.sin(angle) * super.getZ(),
                Math.sin(angle) * super.getY() + Math.cos(angle) * super.getZ()
        );
    }

    public Vertex rotateY(double angle) {
        return new Vertex(
                Math.cos(angle) * super.getX() + Math.sin(angle) * super.getZ(),
                super.getY(),
                -Math.sin(angle) * super.getX() + Math.cos(angle) * super.getZ()
        );
    }

    public Vertex rotateZ(double angle) {
        return new Vertex(
                Math.cos(angle) * super.getX() - Math.sin(angle) * super.getY(),
                Math.sin(angle) * super.getX() + Math.cos(angle) * super.getY(),
                super.getZ()
        );
    }

    public Vertex scale(double scale) {
        return new Vertex(super.getX() * scale, super.getY() * scale, super.getZ() * scale);
    }
}
