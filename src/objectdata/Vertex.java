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
}
