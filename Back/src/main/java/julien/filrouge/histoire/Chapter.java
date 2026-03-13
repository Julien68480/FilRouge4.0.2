package julien.filrouge.histoire;

import com.fasterxml.jackson.annotation.JsonBackReference;
import julien.filrouge.forme.mesformes.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Une story est <b>composé</b> de chapitre
 */

public class  Chapter {

    private Long id;
    private String titre;
    private String texteNarratif;
    private int ordre;
    @JsonBackReference
    private Story story;
    private String instruction;
    private String imageModele;
    private List<Shape> shapes = new ArrayList<>();

    private static Long compteur = 1L;

    public Chapter(String titre, String texteNarratif, int ordre, Story story, String instruction, String imageModele) {

        this.id = compteur++; //permet de ne pas gérer l'ID
        this.titre = titre;
        this.texteNarratif = texteNarratif;
        this.ordre = ordre;
        this.story = story;
        this.instruction = instruction;
        this.imageModele = imageModele;

        story.addChapter(this);


        shapes.add(new Rectangle(1L,"Red", 10, 20, 30, 20));
        shapes.add(new Rond(2L, "Blue", 10, 20, 30));
        shapes.add(new Triangle(3L,"Green", 10, 20, 30));
        shapes.add(new Carre(4L,"Yellow", 10, 20, 30));
    }

    public String afficherPerimetreTotal() {

        double totalPerimetre = 0;

        for (Shape shape : shapes) {

            totalPerimetre += shape.calculPerimetre();

        }
        return "Périmètre total : " + totalPerimetre;

    }

    public String afficherAireTotal() {

        double totalAire = 0;

        for (Shape shape : shapes){

            totalAire += shape.calculAire();

        }
        return "Aire totale : " + totalAire;

    }

    public String getInstruction() {
        return instruction;
    }

    public String getImageModele() {
        return imageModele;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setImageModele(String imageModele) {
        this.imageModele = imageModele;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTexteNarratif(String texteNarratif) {
        this.texteNarratif = texteNarratif;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getTexteNarratif() {
        return texteNarratif;
    }

    public int getOrdre() {
        return ordre;
    }

    public Story getStory() {
        return story;
    }
}
