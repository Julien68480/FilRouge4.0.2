package julien.filrouge.histoire;

import jakarta.persistence.*;
import julien.filrouge.forme.mesformes.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Une story est <b>composé</b> de chapitre
 */
@Entity
public class  Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String texteNarratif;
    @Column(name = "chapter_order")
    private int order;
    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;
    private String instruction;
    private String imageModele;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "chapter_id")
    private List<Shape> shapes = new ArrayList<>();

    public Chapter() {}

    public Chapter(String titre, String texteNarratif, int order, Story story, String instruction, String imageModele) {

        this.titre = titre;
        this.texteNarratif = texteNarratif;
        this.order = order;
        this.story = story;
        this.instruction = instruction;
        this.imageModele = imageModele;

        story.addChapter(this);

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

    public void setOrder(int order) {
        this.order = order;
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

    public int getOrder() {
        return order;
    }

    public Story getStory() {
        return story;
    }

}
