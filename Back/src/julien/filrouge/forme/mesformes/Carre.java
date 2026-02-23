package julien.filrouge.forme.mesformes;

import julien.filrouge.forme.Shape;

public class Carre extends Shape {

    private int side;

    public Carre(String name, String color, int side){

        super(name, color);
        this.side = side;

    }

    public double aire(){

        double aire;

        aire = side * side;

        return aire;

    }
}
