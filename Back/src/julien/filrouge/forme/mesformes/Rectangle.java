package julien.filrouge.forme.mesformes;

import julien.filrouge.forme.Shape;

public class Rectangle extends Shape {

    int length;
    int width;

    public Rectangle(int length, int width){

        this.length = length;
        this.width = width;

    }

    public double aire(){

        double aire;

        aire = length * width;

        return aire;

    }
}

