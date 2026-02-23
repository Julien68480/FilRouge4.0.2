package julien.filrouge.forme;

public abstract class Shape {

    private String name;
    private String color;


    public Shape(String name, String color) {

        this.name = name;
        this.color = color;

    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public abstract double aire();

    public void afficher() {
        System.out.println("Forme : " + getName());
        System.out.println("Couleur : " + getColor());
        System.out.println("Aire : " + aire()); // ← appelle le bon calcul

    }
}
