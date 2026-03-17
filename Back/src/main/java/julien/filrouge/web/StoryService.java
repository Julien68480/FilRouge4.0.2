package julien.filrouge.web;

import julien.filrouge.dto.ChapterDto;
import julien.filrouge.dto.StoryDto;
import julien.filrouge.histoire.Story;
import julien.filrouge.web.dao.ChapterDao;
import org.springframework.stereotype.Service;
import julien.filrouge.web.dao.StoryDao;

import java.util.List;

@Service
public class StoryService {

    private final StoryDao storyDao;
    private final ChapterDao chapterDao;

    public StoryService(StoryDao storyDao, ChapterDao chapterDao) {
        this.storyDao = storyDao;
        this.chapterDao = chapterDao;
    }

    /*List<StoryDto> result = new ArrayList<>();
        for (Story s : storyDao.findAll()) {
                result.add(new StoryDto(s.getId(), s.getTitre(), s.getDescription(), s.getDifficultyLevel(), s.getCoverImage());
            }
        return result;*/

    public List<StoryDto> findAll() {
        return storyDao.findAll().stream()
                .map(s -> new StoryDto(s.getId(), s.getTitre(), s.getDescription(), s.getDifficultyLevel(), s.getCoverImage()))
                .toList();//crée une liste avec mes story
    }


    public StoryDto findById(Long id) {
        Story s = storyDao.findById(id);

        if (s == null) return null;

        return new StoryDto(s.getId(), s.getTitre(), s.getDescription(), s.getDifficultyLevel(), s.getCoverImage());
    }


    private Story construireStory(StoryDto dto){

        return new Story(dto.getTitre(), dto.getDescription(), dto.getDifficultyLevel(), dto.getCoverImage());
    }

    public Story save(StoryDto dto) {

        return storyDao.save(construireStory(dto));

    }

    public Story update(StoryDto dto) {
        return storyDao.update(construireStory(dto));
    }

    public void delete(Long id){ storyDao.delete(id);}

    public ChapterDto findFirstChapter(Long storyId) {
        return chapterDao.findByStoryId(storyId)
                .stream()
                .filter(c -> c.getOrder() == 1)
                .map(c -> new ChapterDto(
                        c.getId(),
                        c.getTitre(),
                        c.getTexteNarratif(),
                        c.getOrder(),
                        c.getInstruction(),
                        c.getImageModele(),
                        c.getShapes(),
                        storyId
                ))                .findFirst()
                .orElse(null);
    }

    public ChapterDto findNextChapter(Long storyId, int order) {
        return chapterDao.findByStoryId(storyId)
                .stream()
                .filter(c -> c.getOrder() == order + 1)
                .map(c -> new ChapterDto(
                        c.getId(),
                        c.getTitre(),
                        c.getTexteNarratif(),
                        c.getOrder(),
                        c.getInstruction(),
                        c.getImageModele(),
                        c.getShapes(),
                        storyId
                ))                .findFirst()
                .orElse(null);
    }
}
