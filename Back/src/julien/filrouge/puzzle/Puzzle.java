package julien.filrouge.puzzle;

import julien.filrouge.forme.mesformes.Shape;
import julien.filrouge.histoire.Chapter;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {

    private Long id;
    private String instruction;
    private String imageModele;
    private Chapter chapter;
    private List<Shape> shapes = new ArrayList<>();

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

    public Puzzle(String instruction, String imageModele, Chapter chapter) {
        this.instruction = instruction;
        this.imageModele = imageModele;
        this.chapter = chapter;
    }

}
