package julien.filrouge.web.dao;

import julien.filrouge.histoire.Chapter;
import julien.filrouge.histoire.Story;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChapterDaoImpl implements ChapterDao {

    @Override
    public List<Chapter> findByStoryId(Long storyId) {
        return StoryDaolmpl.storys.stream()
                .filter(s -> s.getId().equals(storyId))
                .findFirst()
                .map(Story::getChapters)
                .orElse(new ArrayList<>());
    }
}
