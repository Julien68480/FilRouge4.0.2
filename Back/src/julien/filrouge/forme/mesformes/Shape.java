package julien.filrouge.forme.mesformes;

public abstract class Shape {

    private Long id;
    protected String color;
    private double x;
    private double y;

    protected Shape(){
    }

    protected Shape(Long id, String color){

        this.id = id;
        this.color = color;

    }

    protected Shape(Long id, String color, double x, double y) {

        this.id = id;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public abstract double calculAire();

    public abstract double calculPerimetre();

    public void afficher() {
        System.out.println("Couleur : " + color);
        System.out.println("Aire : " + calculAire());
        System.out.println("Perimetre : " + calculPerimetre());

    }
}
