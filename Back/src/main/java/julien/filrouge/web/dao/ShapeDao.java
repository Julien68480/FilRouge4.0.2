package julien.filrouge.web.dao;

import julien.filrouge.forme.mesformes.Shape;

import java.util.List;

public interface ShapeDao {


    List<Shape> findAll();


    Shape findById(Long id);


    Shape save(Shape shape);

        void delete(Long id);

}
