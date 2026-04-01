package julien.filrouge.histoire;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private DifficultyLevel difficultyLevel;
    @OneToMany(mappedBy = "story", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<>();

    public Story() {}

    public Story(String titre, String description, DifficultyLevel difficultyLevel) {

        this.titre = titre;
        this.description = description;
        this.difficultyLevel = difficultyLevel;

    }

    public void addChapter(Chapter chapter) {

        if (!this.chapters.contains(chapter)) {

            this.chapters.add(chapter);

        }
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

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

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

    public List<Chapter> getChapters() {
        return chapters;
    }
}



