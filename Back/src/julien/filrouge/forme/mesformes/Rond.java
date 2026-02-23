package julien.filrouge.forme.mesformes;

import julien.filrouge.forme.Shape;

public class Rond extends Shape {

    private int radius;

    public Rond(String name, String color, int radius) {

        super(name, color);
        this.radius = radius;

    }

    public double aire() {

        double aire;

        aire = Math.PI * Math.pow(radius, 2);

        return aire;

    }


}




