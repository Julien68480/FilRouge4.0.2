package julien.filrouge.web;

import julien.filrouge.dto.ShapeDto;
import julien.filrouge.forme.mesformes.*;
import julien.filrouge.histoire.Chapter;
import julien.filrouge.histoire.ChapterRepository;
import julien.filrouge.forme.mesformes.ShapeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShapeService {

    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    private Shape construireShape(ShapeDto dto) {
        return switch (dto.getType().toLowerCase()) {
            case "rectangle" -> new Rectangle(dto.getColor(), dto.getX(), dto.getY(), dto.getLength(), dto.getWidth());
            case "rond"      -> new Rond(dto.getColor(), dto.getX(), dto.getY(), dto.getRadius());
            case "triangle"  -> new Triangle(dto.getColor(), dto.getX(), dto.getY(), dto.getSide());
            case "carre"     -> new Carre(dto.getColor(), dto.getX(), dto.getY(), dto.getSide());
            default -> throw new IllegalArgumentException("Type inconnu : " + dto.getType());
        };
    }

    public List<Shape> saveAll(Long chapterId, List<ShapeDto> dtos) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapitre non trouvé"));

        List<Shape> shapes = dtos.stream()
                .map(dto -> {
                    Shape shape = construireShape(dto);
                    shape.setChapter(chapter);
                    return shape;
                })
                .toList();

        return shapeRepository.saveAll(shapes);
    }


    public Shape save(Long chapterId, ShapeDto dto) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapitre non trouvé"));

        Shape shape = construireShape(dto);
        shape.setChapter(chapter);
        return shapeRepository.save(shape);
    }

    public void updateAll(Long chapterId, List<ShapeDto> dtos) {
        for (ShapeDto dto : dtos) {
            Shape shape = shapeRepository.findById(dto.getId())
                    .orElseThrow(() -> new RuntimeException("Shape non trouvée : " + dto.getId()));

            shape.setX(dto.getX());
            shape.setY(dto.getY());

            switch (dto.getType().toLowerCase()) {
                case "rectangle" -> {
                    Rectangle rect = (Rectangle) shape;
                    rect.setWidth(dto.getWidth());
                    rect.setLength(dto.getLength());
                }
                case "carre" -> {
                    Carre carre = (Carre) shape;
                    carre.setSide(dto.getSide());
                }
                case "rond" -> {
                    Rond rond = (Rond) shape;
                    rond.setRadius(dto.getRadius());
                }
                case "triangle" -> {
                    Triangle triangle = (Triangle) shape;
                    triangle.setSide(dto.getSide());
                }
                default -> throw new IllegalArgumentException("Type inconnu : " + dto.getType());
            }

            shapeRepository.save(shape);
        }
    }

    public List<Shape> findByChapterId(Long chapterId) {
        return shapeRepository.findByChapterId(chapterId);
    }

    public Shape findById(Long id) {
        return shapeRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        shapeRepository.deleteById(id);
    }
}



