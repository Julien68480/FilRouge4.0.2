package julien.filrouge.dto;


import julien.filrouge.histoire.DifficultyLevel;

import java.util.List;

public class StoryDto {

    private Long id;
    private String titre;
    private String description;
    private DifficultyLevel difficultyLevel;
    private String coverImage;
    private List<ChapterDto> chapters;

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

    public String getCoverImage() {
        return coverImage;
    }

    public List<ChapterDto> getChapters() {
        return chapters;
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

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public void setChapters(List<ChapterDto> chapters) {
        this.chapters = chapters;
    }
}
