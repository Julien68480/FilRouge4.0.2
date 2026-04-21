package julien.filrouge.web.dao;

import julien.filrouge.histoire.Chapter;
import java.util.List;

public interface ChapterDao {

    List<Chapter> findByStoryId(Long storyId);

}
