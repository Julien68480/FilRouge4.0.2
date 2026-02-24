package julien.filrouge.puzzle;

import julien.filrouge.forme.mesformes.Shape;
import julien.filrouge.histoire.Chapter;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {

    private Long id;
    private String instruction;
    private String imageModele;
    private Chapter chapter;
    private List<Shape> shapes = new ArrayList<>();

}
