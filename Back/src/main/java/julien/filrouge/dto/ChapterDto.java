package julien.filrouge.dto;

import julien.filrouge.forme.mesformes.Shape;

import java.util.List;

public class ChapterDto {

    private Long id;
    private String titre;
    private String textNarratif;
    private int order;
    private String instructions;
    private List<ShapeDto> shapes;
    private Long storyID;

    public ChapterDto(Long id, String titre, String textNarratif, int order, String instructions, List<ShapeDto> shapes, Long storyID) {
        this.id = id;
        this.titre = titre;
        this.textNarratif = textNarratif;
        this.order = order;
        this.instructions = instructions;
        this.shapes = shapes;
        this.storyID = storyID;
    }

    public ChapterDto() {}

    public Long getStoryID() {
        return storyID;
    }

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

    public List<ShapeDto> getShapes() {
        return shapes;
    }

    public void setStoryID(Long storyID) {
        this.storyID = storyID;
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

    public void setShapes(List<ShapeDto> shapes) {
        this.shapes = shapes;
    }
}
