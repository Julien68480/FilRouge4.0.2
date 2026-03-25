package julien.filrouge.forme.mesformes;

import jakarta.persistence.*;
import julien.filrouge.dto.ShapeDto;
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
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;


    protected Shape(String color, double x, double y) {


        this.color = color;
        this.x = x;
        this.y = y;
    }

    public ShapeDto toDto() {
        ShapeDto dto = new ShapeDto();
        dto.setId(this.getId());
        dto.setColor(this.getColor());
        dto.setX(this.getX());
        dto.setY(this.getY());
        // ajoute tous tes champs...
        return dto;
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

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
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

