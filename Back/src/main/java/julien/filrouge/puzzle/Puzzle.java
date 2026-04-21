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

        shapes.add(new Rectangle("Red", 10, 20, 30, 20));
        shapes.add(new Rond("Blue", 10, 20, 30));
        shapes.add(new Triangle("Green", 10, 20, 30));
        shapes.add(new Carre("Yellow", 10, 20, 30));

    }

    public void afficherPerimetreTotal() {
        double totalPerimetre = 0;

        for (Shape shape : shapes){

            totalPerimetre += shape.calculPerimetre();
        }

        System.out.println("Périmètre total : " + totalPerimetre);

    }

    public void afficherAireTotal() {

        double totalAire = 0;

        for (Shape shape : shapes){

            totalAire += shape.calculAire();

        }

        System.out.println("Aire totale : " + totalAire);

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
