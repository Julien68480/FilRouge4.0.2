package julien.filrouge.web;

import julien.filrouge.dto.ChapterDto;
import julien.filrouge.histoire.Chapter;
import julien.filrouge.histoire.Story;
import julien.filrouge.histoire.ChapterRepository;
import julien.filrouge.histoire.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private StoryRepository storyRepository;

    // Ajouter un chapitre à une story existante
    public Chapter save(Long storyId, ChapterDto dto) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story non trouvée"));

        Chapter chapter = new Chapter(
                dto.getTitre(),
                dto.getTextNarratif(),
                dto.getOrder(),
                story,
                dto.getInstructions(),
                dto.getImageModele()
        );

        return chapterRepository.save(chapter);
    }

    // Lister les chapitres d'une story
    public List<ChapterDto> findByStoryId(Long storyId) {
        return chapterRepository.findByStoryId(storyId)
                .stream()
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
                .toList();
    }
}
