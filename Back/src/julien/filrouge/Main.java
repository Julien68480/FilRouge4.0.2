package julien.filrouge;


import julien.filrouge.forme.mesformes.*;
import julien.filrouge.histoire.Chapter;
import julien.filrouge.histoire.Story;

import java.util.ArrayList;
import java.util.List;

import static julien.filrouge.histoire.DifficultyLevel.EASY;

public class Main {

    public static void main(String[] args) {


        Story story = new Story("TITRE","description",  EASY, "image");

        Chapter chapter1 = new Chapter("chapitre1","NouveauTexte", 1, story, "Fait ceci", "IMG");

        Chapter chapter2 = new Chapter("chapitre 2", "Nouveau texte", 2, story, "Fait cela", "IMG");

        for (Shape shape : chapter1.getShapes()) {
            shape.afficher();
        }

        chapter1.afficherAireTotal();
        chapter1.afficherPerimetreTotal();

    }

}
