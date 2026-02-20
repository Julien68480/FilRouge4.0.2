public class Triangle extends Shape{

    double side;

    public double aireTriangle(double side){

        this.side = side;
        double aire;

        aire = (Math.sqrt(3) / 4) * (side * side);
        return aire;

    }

}
