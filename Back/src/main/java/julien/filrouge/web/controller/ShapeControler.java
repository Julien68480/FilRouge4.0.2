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

    @GetMapping("/chapters/{chapterId}/shapes")
    public ResponseEntity<List<Shape>> getShapes(@PathVariable Long chapterId) {
        return ResponseEntity.ok(shapeService.findByChapterId(chapterId));
    }

    @DeleteMapping("/shapes/{id}")
    public ResponseEntity<Void> supprimerShape(@PathVariable Long id) {
        shapeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}



