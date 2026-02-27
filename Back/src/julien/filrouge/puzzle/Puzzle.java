package julien.filrouge.puzzle;

import julien.filrouge.forme.mesformes.*;
import julien.filrouge.histoire.Chapter;


import java.util.ArrayList;
import java.util.List;

public class Puzzle {

    private Long id;
    private String instruction;
    private String imageModele;
    private Chapter chapter;
    private List<Shape> shapes = new ArrayList<>();



    public Puzzle( String instruction, String imageModele, Chapter chapter) {
        this.instruction = instruction;
        this.imageModele = imageModele;
        this.chapter = chapter;

        shapes.add(new Rectangle(1L, "rectangle","Red", 10, 20, 30, 20));
        shapes.add(new Rond(2L, "rond","Blue", 10, 20, 30));
        shapes.add(new Triangle(3L, "triangle","Green", 10, 20, 30));
        shapes.add(new Carre(4L, "carre","Yellow", 10, 20, 30));

    }

    public void calculateTotals() {
        double totalAire = 0;
        double totalPerimetre = 0;

        for (Shape shape : shapes){
            totalAire += shape.calculAire();
            totalPerimetre += shape.calculPerimetre();
        }


        System.out.println("Aire totale : " + totalAire);
        System.out.println("Périmètre total : " + totalPerimetre);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setImageModele(String imageModele) {
        this.imageModele = imageModele;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Long getId() {
        return id;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getImageModele() {
        return imageModele;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public List<Shape> getShapes() {
        return shapes;
    }


}
