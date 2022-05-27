package com.classes;

public class Rectangles {
    private final Rectangle[] rectangles;
    private double AverageArea;
    public int index;

    public Rectangles(int amount) {
        rectangles = new Rectangle[amount];
        index = -1;
    }

    public void setRectangle(Rectangle rectangle) {
        if(++index<rectangles.length)
            this.rectangles[index] = rectangle;
    }

    public Rectangle getRectangle(int index) {
        return this.rectangles[index];
    }

    public double getAverageRectanglesArea(){
        for (int i = 0; i < index; i++)
            AverageArea+=this.rectangles[i].getRectangleArea();
        AverageArea/=index;
        return  AverageArea;
    }

    public int getRectanglesMoreAverageArea(){
        int count=0;
        double AverageArea = getAverageRectanglesArea();
        for (int i = 0; i < index; i++)
            if(rectangles[i].getRectangleArea()>AverageArea)
                count++;
        return count;
    }
}
