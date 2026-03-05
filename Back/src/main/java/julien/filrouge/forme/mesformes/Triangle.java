package julien.filrouge.forme.mesformes;

public class Triangle extends Shape {

    private double side;

    public Triangle(Long id, String color, double x, double y, int side) {

        super(id, color, x, y);
        this.side = side;

    }


    public void setSide(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    @Override
    public double calculAire() {

        return (Math.sqrt(3) * (side * side)) / 4; // Math.sqrt(3) c'est racine carré de 3

    }

    @Override
    public double calculPerimetre() {

        return side * 3;

    }

}
