package julien.filrouge.web.controller;

import julien.filrouge.dto.ShapeDto;
import julien.filrouge.forme.mesformes.*;
import julien.filrouge.web.dao.ShapeDao;
import julien.filrouge.web.ShapeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; //permet d'importer toute les annotation Controller et mapping
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class ShapeControler {

    private final ShapeService shapeService;

    public ShapeControler(ShapeService shapeService) { // ✅ plus de ShapeDao
        this.shapeService = shapeService;
    }

    @GetMapping("/shapes")
    public List<Shape> listeShapes(){
        return shapeService.findAll();
    }

    @GetMapping("/shapes/{id}")
    public Shape afficherShape(@PathVariable Long id){
        return shapeService.findById(id);
    }

    @PostMapping("/shapes")
    public ResponseEntity<Void> ajouterShape(@RequestBody ShapeDto dto) {

        Shape saved = shapeService.save(dto);

        if (Objects.isNull(saved)) {
            return ResponseEntity.noContent().build(); // 204 si échec
        }

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()       // part de la requete actuelle
                .path("/{id}")              // dit que ce sera sous /id qu'il sera créer
                .buildAndExpand(saved.getId()) // récupére l'ID du POST
                .toUri();

        return ResponseEntity.created(uri).build(); // on retourne la réponse dans le cas de la création d'un objet 201
    }

    @PutMapping("/shapes/{id}")
    public ResponseEntity<Void> modifierShape(@PathVariable Long id, @RequestBody ShapeDto dto) {
        Shape existing = shapeService.findById(id);

        if (Objects.isNull(existing)) {
            return ResponseEntity.notFound().build(); // si on ne le trouve pas on retourne la réponse
        }

        Shape updated = shapeService.update(dto);

        if (Objects.isNull(updated)) {
            return ResponseEntity.noContent().build(); // si on arrive pas a le mettre à jour on retourne la réponse
        }

        return ResponseEntity.ok().build(); //si il est bien mise à jour on retourne la réponse
    }


    @DeleteMapping("/shapes/{id}")
    public ResponseEntity<Void> supprimerShape(@PathVariable Long id) {
        Shape existing = shapeService.findById(id);

        if (Objects.isNull(existing)) {
            return ResponseEntity.notFound().build();
        }

        shapeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}


