package julien.filrouge.forme.mesformes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShapeRepository extends JpaRepository<Shape, Long> {
    List<Shape> findByChapterId(Long chapterId); // ← pour récupérer shapes d'un chapitre
}
