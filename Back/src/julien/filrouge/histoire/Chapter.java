package julien.filrouge.histoire;

/**
 * Une story est <b>composé</b> de chapitre
 */

public class  Chapter {

    private Long id;
    private String titre;
    private String texteNarratif;
    private int ordre;
    private Story story;

    public Chapter(String titre, String texteNarratif, int ordre, Story story) {
        this.titre = titre;
        this.texteNarratif = texteNarratif;
        this.ordre = ordre;
        this.story = story;
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
