package julien.filrouge.web.controller;

import julien.filrouge.dto.ShapeDto;
import julien.filrouge.forme.mesformes.*;
import julien.filrouge.web.ShapeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class ShapeControler {

    private final ShapeService shapeService;

    public ShapeControler(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @PostMapping("/chapters/{chapterId}/shapes")
    public ResponseEntity<Void> ajouterShape(@PathVariable Long chapterId,
                                             @RequestBody ShapeDto dto) {
        Shape saved = shapeService.save(chapterId, dto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build(); // 201
    }

    @PostMapping("/chapters/{chapterId}/shapes/batch")
    public ResponseEntity<Void> ajouterShapes(@PathVariable Long chapterId,
                                              @RequestBody List<ShapeDto> dtos) {
        shapeService.saveAll(chapterId, dtos);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/chapters/{chapterId}/shapes")
    public ResponseEntity<List<ShapeDto>> getShapes(@PathVariable Long chapterId) {
        List<ShapeDto> dtos = shapeService.findByChapterId(chapterId)
                .stream()
                .map(Shape::toDto)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/shapes/{id}")
    public ResponseEntity<Void> supprimerShape(@PathVariable Long id) {
        shapeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/chapters/{chapterId}/shapes/batch")
    public ResponseEntity<Void> mettreAJourShapes(@PathVariable Long chapterId,
                                                  @RequestBody List<ShapeDto> dtos) {
        shapeService.updateAll(chapterId, dtos);
        return ResponseEntity.ok().build();
    }
}



