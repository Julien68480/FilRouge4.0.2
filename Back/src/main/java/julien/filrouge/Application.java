package julien.filrouge;

import julien.filrouge.forme.mesformes.*;
import julien.filrouge.histoire.Chapter;
import julien.filrouge.histoire.Story;

import static julien.filrouge.histoire.DifficultyLevel.EASY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        Story story = new Story("TITRE", "description", EASY, "image");
        Chapter chapter1 = new Chapter("chapitre1", "NouveauTexte", 1, story, "Fait ceci", "IMG");
        Chapter chapter2 = new Chapter("chapitre 2", "Nouveau texte", 2, story, "Fait cela", "IMG");

        for (Shape shape : chapter1.getShapes()) {
            shape.afficher();
        }

        System.out.println(chapter1.afficherAireTotal());
        System.out.println(chapter1.afficherPerimetreTotal());
    }
}
