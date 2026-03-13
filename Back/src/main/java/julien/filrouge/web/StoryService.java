package julien.filrouge.web;

import julien.filrouge.histoire.Story;
import org.springframework.stereotype.Service;
import julien.filrouge.web.dao.StoryDao;

import java.util.List;

@Service
public class StoryService {

    private final StoryDao storyDao;

    public StoryService(StoryDao storyDao) {this.storyDao = storyDao;}


    public List<Story> findAll() { return storyDao.findAll();}
}
