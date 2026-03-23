package julien.filrouge.dto;

import julien.filrouge.forme.mesformes.Shape;

import java.util.List;

public class ChapterDto {

    private Long id;
    private String titre;
    private String textNarratif;
    private int order;
    private String instructions;
    private String imageModele;
    private List<ShapeDto> shapes;
    private Long storyID;

    public ChapterDto(Long id, String titre, String textNarratif, int order, String instructions, String imageModele, List<Shape> shapes, Long storyID) {
        this.id = id;
        this.titre = titre;
        this.textNarratif = textNarratif;
        this.order = order;
        this.instructions = instructions;
        this.imageModele = imageModele;
        this.shapes = shapes.stream()
                .map(s -> {
                    ShapeDto dto = new ShapeDto();
                    dto.setId(s.getId());
                    dto.setColor(s.getColor());
                    dto.setX(s.getX());
                    dto.setY(s.getY());
                    dto.setType(s.getClass().getSimpleName());
                    return dto;
                })
                .toList();
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

    public String getImageModele() {
        return imageModele;
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

    public void setImageModele(String imageModele) {
        this.imageModele = imageModele;
    }

    public void setShapes(List<ShapeDto> shapes) {
        this.shapes = shapes;
    }
}
