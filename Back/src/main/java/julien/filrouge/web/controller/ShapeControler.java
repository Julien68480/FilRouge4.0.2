package julien.filrouge.web.controller;

import julien.filrouge.forme.mesformes.*;
import julien.filrouge.web.dao.ShapeDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ShapeControler {

    private final ShapeDao shapeDao;

    public ShapeControler(ShapeDao shapeDao) {
        this.shapeDao = shapeDao;
    }

    @GetMapping("/shapes")
    public List<Shape> listeShapes(){
        return shapeDao.findAll();
    }

    @GetMapping("/shapes/{id}")
    public Shape afficherShape(@PathVariable Long id){
        return shapeDao.findById(id);
    }


}


