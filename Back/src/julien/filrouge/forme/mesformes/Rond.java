package julien.filrouge.forme.mesformes;

public class Rond extends Shape {

    private int radius;

    public Rond(Long id, String name, String color, double x, double y, double rotation, int radius) {
        super(id, name, color, x, y, rotation);
        this.radius = radius;
    }

    @Override
    public double calculAire() {

        return Math.PI * Math.pow(radius, 2);

    }

    public double calculPerimetre()
    {

        return 2 * Math.PI * radius;

    }


}




