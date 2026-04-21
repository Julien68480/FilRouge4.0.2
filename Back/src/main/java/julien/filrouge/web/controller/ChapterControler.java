package julien.filrouge.web.controller;

import julien.filrouge.dto.ChapterDto;
import julien.filrouge.histoire.Chapter;
import julien.filrouge.web.ChapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ChapterControler {

    private final ChapterService chapterService;

    public ChapterControler(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping("/stories/{storyId}/chapters")
    public ResponseEntity<List<ChapterDto>> listerChapitres(@PathVariable Long storyId) {
        return ResponseEntity.ok(chapterService.findByStoryId(storyId));
    }


    @PostMapping("/stories/{storyId}/chapters")
    public ResponseEntity<Void> ajouterChapitre(@PathVariable Long storyId,
                                                @RequestBody ChapterDto dto) {
        Chapter saved = chapterService.save(storyId, dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build(); // 201 ✅
    }

    @GetMapping("/stories/{storyId}/chapters/first")
    public ChapterDto getFirstChapter(@PathVariable Long storyId) {
        return chapterService.findFirstChapter(storyId);
    }

    @GetMapping("/stories/{storyId}/chapters/next")
    public ChapterDto getNextChapter(@PathVariable Long storyId, @RequestParam int order) {
        return chapterService.findNextChapter(storyId, order);
    }

    @GetMapping("/stories/{storyId}/chapters/{id}")
    public ResponseEntity<ChapterDto> afficherChapitre(@PathVariable Long storyId,
                                                       @PathVariable Long id) {
        ChapterDto chapter = chapterService.findById(id);
        if (chapter == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(chapter);
    }

    @PutMapping("/stories/{storyId}/chapters/{id}")
    public ResponseEntity<Void> modifierChapitre(@PathVariable Long storyId,
                                                 @PathVariable Long id,
                                                 @RequestBody ChapterDto dto) {
        chapterService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/stories/{storyId}/chapters/{id}")
    public ResponseEntity<Void> supprimerChapitre(@PathVariable Long storyId,
                                                  @PathVariable Long id) {
        chapterService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
