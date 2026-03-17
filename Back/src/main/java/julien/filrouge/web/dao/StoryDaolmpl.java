package julien.filrouge.web.dao;


import julien.filrouge.forme.mesformes.Shape;
import julien.filrouge.histoire.Chapter;
import julien.filrouge.histoire.DifficultyLevel;
import julien.filrouge.histoire.Story;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StoryDaolmpl implements StoryDao {

    public static List<Story> storys = new ArrayList<Story>();

    static {

        Story s1 = new Story("Mon histoire", "Ma première histoire", DifficultyLevel.EASY, "image première histoire");
        s1.addChapter(new Chapter("Mon premier chapitre", "Il était une fois...", 1, s1, "Première instruction", "premier modèle"));
        s1.addChapter(new Chapter("Mon deuxième chapitre", "La suite de l'aventure...", 2, s1, "Deuxième instruction", "deuxième modèle"));
        storys.add(s1);

        Story s2 = new Story("La grande aventure", "Une épopée fantastique", DifficultyLevel.HARD, "image grande aventure");
        s2.addChapter(new Chapter("Le départ", "Le héros quitte son village...", 1, s2, "Instruction départ", "modèle départ"));
        s2.addChapter(new Chapter("L'épreuve", "Une terrible épreuve se présente...", 2, s2, "Instruction épreuve", "modèle épreuve"));
        s2.addChapter(new Chapter("Le retour", "Victorieux, le héros rentre chez lui...", 3, s2, "Instruction retour", "modèle retour"));
        storys.add(s2);

    }


    @Override
    public List<Story> findAll() {
        return storys;
    }

    @Override
    public Story findById(Long id) {
        for (Story story : storys) {
            if (story.getId().equals(id)) {

                return story;
            }
        }
        return null;
    }

    @Override
    public Story save(Story story) {
        storys.add(story);

        return story;
    }

    @Override
    public Story update(Story story) {
        for (int i = 0; i < storys.size(); i++) {
            if (storys.get(i).getId().equals(story.getId())) {

                storys.set(i, story);

                return story;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {

        storys.removeIf(story -> story.getId().equals(id));

    }
}
