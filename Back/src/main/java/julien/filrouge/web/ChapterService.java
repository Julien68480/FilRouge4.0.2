package julien.filrouge.web;

import julien.filrouge.dto.ChapterDto;
import julien.filrouge.forme.mesformes.Shape;
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

    public Chapter save(Long storyId, ChapterDto dto) {
        Story story = storyRepository.findById(storyId)
                .orElseThrow(() -> new RuntimeException("Story non trouvée"));

        Chapter chapter = new Chapter(
                dto.getTitre(),
                dto.getTextNarratif(),
                dto.getOrder(),
                story,
                dto.getInstructions()
        );

        return chapterRepository.save(chapter);
    }

    private ChapterDto toDto(Chapter c) {
        return new ChapterDto(
                c.getId(),
                c.getTitre(),
                c.getTexteNarratif(),
                c.getOrder(),
                c.getInstruction(),
                c.getShapes().stream().map(Shape::toDto).toList(),
                c.getStory().getId(),
                c.afficherAireTotal(),
                c.afficherPerimetreTotal()
        );
    }

    public List<ChapterDto> findByStoryId(Long storyId) {
        return chapterRepository.findByStoryId(storyId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public ChapterDto findFirstChapter(Long storyId) {
        return chapterRepository.findByStoryId(storyId)
                .stream()
                .filter(c -> c.getOrder() == 1)
                .map(this::toDto)
                .findFirst()
                .orElse(null);
    }

    public ChapterDto findNextChapter(Long storyId, int order) {
        return chapterRepository.findByStoryId(storyId)
                .stream()
                .filter(c -> c.getOrder() == order + 1)
                .map(this::toDto)
                .findFirst()
                .orElse(null);
    }

    public ChapterDto findById(Long id) {
        Chapter c = chapterRepository.findById(id).orElse(null);
        if (c == null) return null;

        return toDto(c);
    }

    public Chapter update(Long id, ChapterDto dto) {
        Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chapitre non trouvé"));
        chapter.setTitre(dto.getTitre());
        chapter.setTexteNarratif(dto.getTextNarratif());
        chapter.setOrder(dto.getOrder());
        chapter.setInstruction(dto.getInstructions());
        return chapterRepository.save(chapter);
    }

    public void delete(Long id) {
        chapterRepository.deleteById(id);
    }
}