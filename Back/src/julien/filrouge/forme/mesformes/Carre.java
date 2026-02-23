package julien.filrouge.forme.mesformes;

import julien.filrouge.forme.Shape;

public class Carre extends Shape {

    int side;

    public Carre(int side){

        this.side = side;
    }

    public double aire(){

        double aire;

        aire = side * side;

        return aire;

    }
}
