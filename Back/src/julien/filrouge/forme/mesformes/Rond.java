package julien.filrouge.forme.mesformes;

import julien.filrouge.forme.Shape;

public class Rond extends Shape {

    int radius;

    public Rond(int radius) {

        this.radius = radius;

    }

    public double aire(){

        double aire;

        aire = Math.PI * Math.pow(radius, 2);

        return aire;

    }
}
