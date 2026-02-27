package julien.filrouge.forme.mesformes;

public class Carre extends Shape {

    private double side;

    public Carre(Long id, String name, String color, double x, double y, double rotation, int side) {

        super(id, name, color, x, y);
        this.side = side;

    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double calculAire(){

        return side * side;

    }

    @Override
    public double calculPerimetre() {
        return 4 * side;
    }
}

