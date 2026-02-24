package julien.filrouge.forme.mesformes;

public class Rectangle extends Shape {

    private int length;
    private int width;

    public Rectangle(Long id, String name, String color, double x, double y, double rotation, int length, int width) {
        super(id, name, color, x, y, rotation);
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculAire(){

        return length * width;

    }

    @Override
    public double calculPerimetre() {

        return 2 * ( length + width );

    }
}

