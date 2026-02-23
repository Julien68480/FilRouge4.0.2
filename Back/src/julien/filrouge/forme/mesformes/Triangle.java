package julien.filrouge.forme.mesformes;

import julien.filrouge.forme.Shape;

public class Triangle extends Shape {

    private int side;

    public Triangle(String name, String color, int side){

        super(name, color);
        this.side = side;

    }

    public double aire(){

        double aire;

        aire = (Math.sqrt(3) * (side * side)) / 4;

        return aire;

    }
}
