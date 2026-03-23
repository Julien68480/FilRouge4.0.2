package julien.filrouge.web.dao;

import julien.filrouge.forme.mesformes.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ShapeDaolmpl implements ShapeDao {

    public static List<Shape> shapes = new ArrayList<Shape>();

    static {
        shapes.add(new Rectangle("Rouge", 10, 20, 30, 20));
        shapes.add(new Rond("Blue", 10, 20, 30));
        shapes.add(new Triangle("Green", 10, 20, 30));
        shapes.add(new Carre("Yellow", 10, 20, 30));
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

    @Override
    public Shape update(Shape shape) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getId().equals(shape.getId())) {

                shapes.set(i, shape);

                return shape;
            }
        }
        return null;
    }

    @Override
    public void delete(Long id) {

        shapes.removeIf(shape -> shape.getId().equals(id));
    }
}
