package julien.filrouge.web.controller;

import julien.filrouge.histoire.Story;
import julien.filrouge.web.StoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
