package julien.filrouge.web;

import julien.filrouge.dto.StoryDto;
import julien.filrouge.histoire.ChapterRepository;
import julien.filrouge.histoire.Story;
import julien.filrouge.histoire.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
                        s.getDifficultyLevel()))
                .toList();//crée une liste avec mes story
    }


    public StoryDto findById(Long id) {
        Story s = storyRepository.findById(id)
                .orElse(null);

        if (s == null) return null;

        return new StoryDto(
                s.getId(),
                s.getTitre(), s.getDescription(),
                s.getDifficultyLevel());
    }


    private Story construireStory(StoryDto dto){

        return new Story(dto.getTitre(), dto.getDescription(), dto.getDifficultyLevel());
    }

    public Story save(StoryDto dto) {

        return storyRepository.save(construireStory(dto));

    }

    public StoryDto update(StoryDto dto) {  // Signature OK
        Story story = storyRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Story non trouvée"));

        story.setTitre(dto.getTitre());
        story.setDescription(dto.getDescription());
        story.setDifficultyLevel(dto.getDifficultyLevel());

        Story saved = storyRepository.save(story);

        // ✅ CORRIGÉ : new StoryDto(4 paramètres)
        return new StoryDto(saved.getId(), saved.getTitre(),
                saved.getDescription(), saved.getDifficultyLevel());
    }


    public void delete(Long id){
        storyRepository.deleteById(id);
    }

}
