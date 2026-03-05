package julien.filrouge.forme.mesformes;

import java.util.List;

public abstract class Shape {

    private Long id;
    private String color;
    private double x;
    private double y;


    protected Shape(Long id, String color, double x, double y) {

        this.id = id;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public abstract double calculAire();

    public abstract double calculPerimetre();

    public void afficher() {
        System.out.println("Couleur : " + color);
        System.out.println("Aire : " + calculAire());
        System.out.println("Perimetre : " + calculPerimetre());

    }
}

