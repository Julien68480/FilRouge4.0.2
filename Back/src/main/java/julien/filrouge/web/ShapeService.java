package julien.filrouge.web;

import julien.filrouge.dto.ShapeDto;
import julien.filrouge.forme.mesformes.*;
import julien.filrouge.web.dao.ShapeDao;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShapeService {

    private final ShapeDao shapeDao;

    public ShapeService(ShapeDao shapeDao) {
        this.shapeDao = shapeDao;
    }

    private Shape construireShape(ShapeDto dto) {
        return switch (dto.getType()){
         //selon le type renvoyé on crée l'objet du bon type

            case "rectangle" -> new Rectangle(dto.getColor(), dto.getX(), dto.getY(), dto.getLength(), dto.getWidth());

            case "rond" -> new Rond(dto.getColor(), dto.getX(), dto.getY(), dto.getRadius());

            case "triangle" -> new Triangle(dto.getColor(), dto.getX(), dto.getY(), dto.getSide());

            case "carre" -> new Carre(dto.getColor(), dto.getX(), dto.getY(), dto.getSide());

            default -> throw new IllegalArgumentException("Type inconnu !");
        };

    }

    public Shape save(ShapeDto dto) {
        //retourne un objet de type Shape on utilise la méthode
        // save pour l'enregistrerc et elle reçoit ShapeDto

        return shapeDao.save(construireShape(dto));
    }

    public Shape update(ShapeDto dto) {

        return shapeDao.update(construireShape(dto));

    }

    public void delete(Long id) {
        shapeDao.delete(id);
    }

    public List<Shape> findAll() {
        return shapeDao.findAll();
    }

    public Shape findById(Long id) {
        return shapeDao.findById(id);
    }


}

