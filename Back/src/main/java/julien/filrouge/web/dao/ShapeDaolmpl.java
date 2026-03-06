package julien.filrouge.web.dao;

import julien.filrouge.forme.mesformes.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// c'est ici que l'on communiquera avec la BDD

@Repository
public class ShapeDaolmpl implements ShapeDao {

    public static List<Shape> shapes = new ArrayList<Shape>();

    static {
        shapes.add(new Rectangle(1L, "Red", 10, 20, 30, 20));
        shapes.add(new Rond(2L, "Blue", 10, 20, 30));
        shapes.add(new Triangle(3L, "Green", 10, 20, 30));
        shapes.add(new Carre(4L, "Yellow", 10, 20, 30));
    }

    @Override
    public List<Shape> findAll() {

        return shapes;
    }

    @Override
    public Shape findById(Long id) {

        for (Shape shape : shapes) {
            if (shape.getId().equals(id)) {

                return shape;
            }
        }
            return null;
        }

    @Override
    public Shape save(Shape shape) {
            shapes.add(shape);

        return shape;
    }
}
