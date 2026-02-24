package julien.filrouge;


import julien.filrouge.forme.mesformes.Carre;
import julien.filrouge.forme.mesformes.Rond;
import julien.filrouge.forme.mesformes.Triangle;
import julien.filrouge.forme.mesformes.Rectangle;

public class Main {

    public static void main(String[] args) {

        Rectangle rectangle1 = new Rectangle(1L,"rectangle", "rouge", 1, 3, 0, 10, 20);

        rectangle1.afficher();

    }

}
