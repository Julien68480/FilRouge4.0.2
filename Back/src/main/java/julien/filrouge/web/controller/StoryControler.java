package julien.filrouge.web.controller;

import julien.filrouge.histoire.Story;
import julien.filrouge.web.StoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoryControler {

    private final StoryService storyService;

    public StoryControler(StoryService storyService) {
        this.storyService = storyService;
    }

    @GetMapping("/storys")
    public List<Story> listeStory(){ return storyService.findAll();}

}
