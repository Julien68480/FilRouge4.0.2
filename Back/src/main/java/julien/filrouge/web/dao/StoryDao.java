package julien.filrouge.web.dao;

import julien.filrouge.histoire.Story;

import java.util.List;

public interface StoryDao {


    List<Story> findAll();


    Story findById(Long id);


    Story save(Story story);


    Story update(Story story);


    void delete(Long id);

}
