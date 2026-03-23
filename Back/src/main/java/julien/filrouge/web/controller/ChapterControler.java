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

    // GET /storys/1/chapters
    @GetMapping("/storys/{storyId}/chapters")
    public ResponseEntity<List<ChapterDto>> listerChapitres(@PathVariable Long storyId) {
        return ResponseEntity.ok(chapterService.findByStoryId(storyId));
    }

    // POST /storys/1/chapters
    @PostMapping("/storys/{storyId}/chapters")
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
}
