package julien.filrouge.dto;

import java.util.List;

public class ChapterDto {

    private Long id;
    private String titre;
    private String textNarratif;
    private int order;
    private String instructions;
    private String imageModele;
    private List<ShapeDto> shapes;

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getTextNarratif() {
        return textNarratif;
    }

    public int getOrder() {
        return order;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getImageModele() {
        return imageModele;
    }

    public List<ShapeDto> getShapes() {
        return shapes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTextNarratif(String textNarratif) {
        this.textNarratif = textNarratif;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setImageModele(String imageModele) {
        this.imageModele = imageModele;
    }

    public void setShapes(List<ShapeDto> shapes) {
        this.shapes = shapes;
    }
}
