package julien.filrouge.histoire;

import java.util.ArrayList;
import java.util.List;

public class Story {

    private Long id;
    private String titre;
    private String description;
    private DifficultyLevel difficultyLevel;
    private String coverImage;
    private List<Chapter> chapters = new ArrayList<>();


}
