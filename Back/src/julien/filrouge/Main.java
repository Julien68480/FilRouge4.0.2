package julien.filrouge;


import julien.filrouge.forme.mesformes.Carre;
import julien.filrouge.forme.mesformes.Rond;
import julien.filrouge.forme.mesformes.Triangle;
import julien.filrouge.forme.mesformes.Rectangle;

public class Main {

    public static void main(String[] args) {

        Rond rond1 = new Rond("rond", "rouge", 20);
        Carre carre1 = new Carre("carre", "vert", 20);
        Triangle triangle1 = new Triangle("triangle", "bleu", 20);
        Rectangle rectangle1 = new Rectangle("rectangle", "jaune", 20, 30);

        rond1.afficher();
        carre1.afficher();
        triangle1.afficher();
        rectangle1.afficher();
    }


}
