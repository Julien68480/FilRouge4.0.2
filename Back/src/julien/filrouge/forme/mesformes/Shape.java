package julien.filrouge.forme.mesformes;

public abstract class Shape {

    private Long id;
    private String name;
    private String color;
    private double x;
    private double y;


    protected Shape(Long id, String name, String color, double x, double y) {

        this.id = id;
        this.name = name;
        this.color = color;
        this.x = x;
        this.y = y;
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
