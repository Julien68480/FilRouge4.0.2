package julien.filrouge.dto;


import julien.filrouge.histoire.DifficultyLevel;

import java.util.List;

public class StoryDto {

    private Long id;
    private String titre;
    private String description;
    private DifficultyLevel difficultyLevel;

    public StoryDto(Long id, String titre, String description, DifficultyLevel difficultyLevel) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
    }

    public StoryDto(String titre, String description, DifficultyLevel difficultyLevel) {
        this.titre = titre;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
    }

    public StoryDto() {};

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

}
