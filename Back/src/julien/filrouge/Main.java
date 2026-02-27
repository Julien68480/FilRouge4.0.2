package julien.filrouge;


import julien.filrouge.forme.mesformes.*;
import julien.filrouge.histoire.Chapter;
import julien.filrouge.histoire.Story;
import julien.filrouge.puzzle.Puzzle;

import java.util.ArrayList;
import java.util.List;

import static julien.filrouge.histoire.DifficultyLevel.EASY;

public class Main {

    public static void main(String[] args) {

        Story story = new Story("TITRE","description",  EASY, "image");

        Chapter chapter1 = new Chapter("chapitre1","NouveauTexte", 1, story);

        Chapter chapter2 = new Chapter("chapitre2","NouveauTexte", 1, story);

        List<Chapter> chapters = new ArrayList<Chapter>();

        chapters.add(chapter1);
        chapters.add(chapter2);

        story.setChapters(chapters);

        Puzzle puzzle1 = new Puzzle("Instruction", "image", chapter1 );

        puzzle1.calculateTotals();

    }

}
