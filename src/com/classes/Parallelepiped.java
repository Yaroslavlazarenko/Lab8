package com.classes;

public class Parallelepiped extends Rectangle {
    private double height;

    public Parallelepiped(double length, double width, double height) {
        super(length, width);
        this.height = height;
    }

    public void setParallelepipedHeight(double height) {
        this.height = height;
    }

    public double getParallelepipedHeight() {
        return this.height;
    }

    public void setParallelepipedWidth(double width) {
        super.width = width;
    }

    public double getParallelepipedWidth() {
        return super.width;
    }

    public void setParallelepipedLength(double length) {
        super.length = length;
    }

    public double getParallelepipedLength() {
        return super.length;
    }

    public double getParallelepipedDiagonal(){
        return Math.sqrt(super.getRectangleDiagonal()*super.getRectangleDiagonal()+this.height*this.height);
    }

    public double getParallelepipedArea() {
        return super.getRectangleArea();
    }

    public double getParallelepipedVolume() {
        return super.getRectangleArea() * this.height;
    }

    @Override
    public String toString() {
        return "height = " + this.height +
                "\nlength = " + super.length +
                "\nwidth = " + super.width +
                "\ndiagonal = " + Untiled.formatNumber(this.getParallelepipedDiagonal()) +
                "\nperimeter = " + Untiled.formatNumber(super.getRectanglePerimeter()) +
                "\narea = " + Untiled.formatNumber(super.getRectangleArea()) +
                "\nvolume = " + Untiled.formatNumber(this.getParallelepipedVolume());
    }
}
