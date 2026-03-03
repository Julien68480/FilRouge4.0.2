package julien.filrouge.forme.mesformes;

public class Rectangle extends Shape {

    private double length;
    private double width;

    public Rectangle(Long id, String color, double x, double y, int length, int width) {

        super(id, color, x, y);
        this.length = length;
        this.width = width;

    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
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

