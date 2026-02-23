package julien.filrouge.forme.mesformes;

import julien.filrouge.forme.Shape;

public class Rectangle extends Shape {

    private int length;
    private int width;

    public Rectangle(String name, String color, int length, int width){

        super(name, color);
        this.length = length;
        this.width = width;

    }

    public double aire(){

        double aire;

        aire = length * width;

        return aire;

    }
}

