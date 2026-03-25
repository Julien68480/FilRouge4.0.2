package julien.filrouge.web.controller;

import julien.filrouge.dto.ChapterDto;
import julien.filrouge.dto.StoryDto;
import julien.filrouge.histoire.Story;
import julien.filrouge.web.StoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
public class StoryControler {

    private final StoryService storyService;

    public StoryControler(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/storys")
    public ResponseEntity<List<StoryDto>> listeStory(){
        List<StoryDto> stories = storyService.findAll();

        if (stories.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList()); // créer une liste vide sans allocation mémoire
        }

        return ResponseEntity.ok(stories);
    }


    @GetMapping("/storys/{id}")
    public ResponseEntity<StoryDto> afficherStory(@PathVariable Long id){
        StoryDto story = storyService.findById(id);

        if (story == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(story);
    }


    @PostMapping("/storys")
    public ResponseEntity<Void> ajouterStory(@RequestBody StoryDto dto) {

        Story saved = storyService.save(dto);



        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()    // part de la requete actuelle
                .path("/{id}")              // dit que ce sera sous /id qu'il sera créer
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).build(); // on retourne la réponse (le lien de l'objet) dans le cas de la création d'un objet 201
    }

    @PutMapping("/storys/{id}")
    public ResponseEntity<Void> modifierStory(@PathVariable Long id, @RequestBody StoryDto dto) {
        dto.setId(id);
        StoryDto existing = storyService.findById(id);


        StoryDto updated = storyService.update(dto);


        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/storys/{id}")
    public ResponseEntity<Void> supprimerStory(@PathVariable Long id) {
        StoryDto existing = storyService.findById(id);


        storyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
