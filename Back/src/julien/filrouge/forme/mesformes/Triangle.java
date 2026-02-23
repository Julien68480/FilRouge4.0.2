package julien.filrouge.forme.mesformes;

import julien.filrouge.forme.Shape;

public class Triangle extends Shape {

    int side;

    public Triangle(int side){

        this.side = side;

    }

    public double aire(){

        double aire;

        aire = (Math.sqrt(3) * (side * side)) / 4;

        return aire;

    }
}
