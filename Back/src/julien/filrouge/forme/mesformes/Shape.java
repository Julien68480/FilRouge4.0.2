package julien.filrouge.forme.mesformes;

public abstract class Shape {

    private Long id;
    private String name;
    private String color;
    private double X;
    private double Y;


    public Shape(Long id, String name, String color, double x, double y, double rotation) {

        this.id = id;
        this.name = name;
        this.color = color;
        this.X = x;
        this.Y = y;
    }



    public abstract double calculAire();

    public abstract double calculPerimetre();

    public void afficher() {
        System.out.println("Forme : " + name);
        System.out.println("Couleur : " + color);
        System.out.println("Aire : " + calculAire());
        System.out.println("Perimetre : " + calculPerimetre());

    }
}
