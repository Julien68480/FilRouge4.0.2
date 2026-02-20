public class Rond extends Shape {

    int radius;

    public double aireRond(int radius){

        this.radius = radius;
        double aire;

        aire = Math.PI * (radius * radius);

        return aire;
    }

}
