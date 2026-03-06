package julien.filrouge.forme.mesformes;

public class Rond extends Shape {

    private double radius;

    public Rond(Long id, String color, double x, double y, double radius) {

        super(id, color, x, y);
        this.radius = radius;

    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public double calculAire() {

        return Math.PI * Math.pow(radius, 2);

    }

    @Override
    public double calculPerimetre()
    {

        return 2 * Math.PI * radius;

    }


}




