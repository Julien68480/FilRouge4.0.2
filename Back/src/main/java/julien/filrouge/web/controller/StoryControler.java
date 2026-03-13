package julien.filrouge.web.controller;

import julien.filrouge.dto.ShapeDto;
import julien.filrouge.dto.StoryDto;
import julien.filrouge.forme.mesformes.Shape;
import julien.filrouge.histoire.Story;
import julien.filrouge.web.StoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class StoryControler {

    private final StoryService storyService;

    public StoryControler(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/storys")
    public List<Story> listeStory(){ return storyService.findAll();}

    @GetMapping("/storys/{id}")
    public Story afficherStory(@PathVariable Long id){
        return storyService.findById(id);
    }

    @PostMapping("/storys")
    public ResponseEntity<Void> ajouterStory(@RequestBody StoryDto dto) {

        Story saved = storyService.save(dto);

        if (Objects.isNull(saved)) {
            return ResponseEntity.noContent().build(); // 204 si échec
        }

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()       // part de la requete actuelle
                .path("/{id}")              // dit que ce sera sous /id qu'il sera créer
                .buildAndExpand(saved.getId()) // récupére l'ID du POST
                .toUri();

        return ResponseEntity.created(uri).build(); // on retourne la réponse (le lien de l'objet) dans le cas de la création d'un objet 201
    }

    @PutMapping("storys/{id}")
    public ResponseEntity<Void> modifierStory(@PathVariable Long id, @RequestBody StoryDto dto) {
        Story existing = storyService.findById(id);

        if (Objects.isNull(existing)) {
            return ResponseEntity.notFound().build();
        }

        Story updated = storyService.update(dto);

        if (Objects.isNull(updated)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/storys/{id}")
    public ResponseEntity<Void> supprimerStory(@PathVariable Long id) {
        Story existing = storyService.findById(id);

        if (Objects.isNull(existing)) {
            return ResponseEntity.notFound().build();
        }

        storyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
