public class Rectangle extends Shape {

    int length;
    int width;

    public int aireRectangle(int length, int width) {

        this.length = length;
        this.width = width;

        int aire;
        aire = length * width;

        return aire;

    }
}

