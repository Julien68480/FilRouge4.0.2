public class Main {

    public static void main(String[] args) {
        // c'est ici que tu instancies tes objets et appelles leurs méthodes
        Triangle triangle = new Triangle();
        Rectangle rectangle = new Rectangle();
        Rond rond = new Rond();
        Carre carre = new Carre();

    }

    public static void aire(Rectangle rectangle) {
        rectangle.aireRectangle(10, 20);
    }


}