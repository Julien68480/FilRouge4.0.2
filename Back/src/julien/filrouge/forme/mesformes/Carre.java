package julien.filrouge.forme.mesformes;

public class Carre extends Shape {

    private int side;

    public Carre(Long id, String name, String color, double x, double y, double rotation, int side) {
        super(id, name, color, x, y, rotation);
        this.side = side;
    }

    @Override
    public double calculAire(){

        return side * side;

    }

    @Override
    public double calculPerimetre() {
        return 2 * side * 2;
    }
}

