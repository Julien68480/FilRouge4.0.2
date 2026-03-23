package julien.filrouge.forme.mesformes;

import jakarta.persistence.*;
import julien.filrouge.histoire.Chapter;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Shape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String color;
    private double x;
    private double y;


    protected Shape(String color, double x, double y) {


        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Shape() {}

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

