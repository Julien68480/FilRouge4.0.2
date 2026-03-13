package julien.filrouge.web;

import julien.filrouge.dto.StoryDto;
import julien.filrouge.histoire.Story;
import org.springframework.stereotype.Service;
import julien.filrouge.web.dao.StoryDao;

import java.util.List;

@Service
public class StoryService {

    private final StoryDao storyDao;

    public StoryService(StoryDao storyDao) {this.storyDao = storyDao;}


    public List<Story> findAll() { return storyDao.findAll();}

    public Story findById(Long id){
        return storyDao.findById(id);
    }

    private Story construireStory(StoryDto dto){

        return new Story(dto.getId(), dto.getTitre(), dto.getDescription(), dto.getDifficultyLevel(), dto.getCoverImage());
    }

    public Story save(StoryDto dto) {

        return storyDao.save(construireStory(dto));

    }

    public Story update(StoryDto dto) {
        return storyDao.update(construireStory(dto));
    }
}
