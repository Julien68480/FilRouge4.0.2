package julien.filrouge.forme.mesformes;

public class Triangle extends Shape {

    private int side;

    public Triangle(Long id, String name, String color, double x, double y, double rotation, int side) {
        super(id, name, color, x, y, rotation);
        this.side = side;
    }

    @Override
    public double calculAire(){

        return (Math.sqrt(3) * (side * side)) / 4;

    }

    @Override
    public double calculPerimetre() {

        return side * 3;

    }
}
