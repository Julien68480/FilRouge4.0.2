package julien.filrouge.web;

import julien.filrouge.dto.ChapterDto;
import julien.filrouge.dto.StoryDto;
import julien.filrouge.histoire.ChapterRepository;
import julien.filrouge.histoire.Story;
import julien.filrouge.histoire.StoryRepository;
import julien.filrouge.web.dao.ChapterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import julien.filrouge.web.dao.StoryDao;

import java.util.List;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private ChapterRepository chapterRepository;


    /*List<StoryDto> result = new ArrayList<>();
        for (Story s : storyDao.findAll()) {
                result.add(new StoryDto(s.getId(), s.getTitre(), s.getDescription(), s.getDifficultyLevel(), s.getCoverImage());
            }
        return result;*/

    public List<StoryDto> findAll() {
        return storyRepository.findAll().stream()
                .map(s -> new StoryDto(
                        s.getId(),
                        s.getTitre(),
                        s.getDescription(),
                        s.getDifficultyLevel(),
                        s.getCoverImage()))
                .toList();//crée une liste avec mes story
    }


    public StoryDto findById(Long id) {
        Story s = storyRepository.findById(id)
                .orElse(null);

        if (s == null) return null;

        return new StoryDto(
                s.getId(),
                s.getTitre(), s.getDescription(),
                s.getDifficultyLevel(),
                s.getCoverImage());
    }


    private Story construireStory(StoryDto dto){

        return new Story(dto.getTitre(), dto.getDescription(), dto.getDifficultyLevel(), dto.getCoverImage());
    }

    public Story save(StoryDto dto) {

        return storyRepository.save(construireStory(dto));

    }

    public Story update(StoryDto dto) {
        Story story = storyRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Story non trouvée"));

        story.setTitre(dto.getTitre());
        story.setDescription(dto.getDescription());
        story.setDifficultyLevel(dto.getDifficultyLevel());
        story.setCoverImage(dto.getCoverImage());

        return storyRepository.save(story);
    }

    public void delete(Long id){

        storyRepository.deleteById(id);

    }

    public ChapterDto findFirstChapter(Long storyId) {
        return chapterRepository.findByStoryId(storyId)
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
                ))
                .findFirst()
                .orElse(null);
    }

    public ChapterDto findNextChapter(Long storyId, int order) {
        return chapterRepository.findByStoryId(storyId)
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
