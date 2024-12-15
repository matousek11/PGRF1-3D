package models;

import objectdata.Point2D;

public class Line {
    private final int xStart;
    private final int yStart;
    private final int xEnd;
    private final int yEnd;
    private final int color;
    private final int thickness;

    public Line(Point2D startPoint, Point2D endPoint, int color, int thickness) {
        this.xStart = (int) startPoint.getX();
        this.yStart = (int) startPoint.getY();
        this.xEnd = (int) endPoint.getX();
        this.yEnd = (int) endPoint.getY();
        this.color = color;
        this.thickness = thickness;
    }

    public int getXStart() {
        return xStart;
    }

    public int getYStart() {
        return yStart;
    }

    public int getXEnd() {
        return xEnd;
    }

    public int getYEnd() {
        return yEnd;
    }

    public int getColor() {
        return color;
    }

    public int getThickness() {
        return thickness;
    }

    public double length () {
        double deltaX = xEnd - xStart;
        double deltaY = yEnd - yStart;

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public double angle() {
        double deltaY = yEnd - yStart;
        double deltaX = xEnd - xStart;

        return Math.atan2(deltaY, deltaX);
    }
}