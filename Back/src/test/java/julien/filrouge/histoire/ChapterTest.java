package julien.filrouge.histoire;

import julien.filrouge.forme.mesformes.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static julien.filrouge.histoire.DifficultyLevel.EASY;
import static org.junit.jupiter.api.Assertions.*;

class ChapterTest {

    @DisplayName("Test création d'un titre")
    @Test
    void constructeurInitialiseLeTitre() {
        Story story = new Story("Titre", "description", EASY, "image");
        Chapter chapter = new Chapter("Mon Titre", "Texte narratif", 1, story, "Instruction", "image.jpg");
        assertEquals("Mon Titre", chapter.getTitre());
    }

    @DisplayName("Test création chapitre")
    @Test
    void constructeurInitialiseTousLesChamps() {
        Story story = new Story("Titre", "description", EASY, "image");
        Chapter chapter = new Chapter("Mon Titre", "Texte narratif", 1, story, "Instruction", "image.jpg");
        assertEquals("Texte narratif", chapter.getTexteNarratif());
        assertEquals(1, chapter.getOrder());
        assertEquals(story, chapter.getStory());
        assertEquals("Instruction", chapter.getInstruction());
        assertEquals("image.jpg", chapter.getImageModele());
    }

    @Test
    @DisplayName("Test l'ajout d'un chapter à la story")
    void constructeurAjouteChapterALaStory() {

        // ARRANGE
        Story story = new Story("Titre", "description", EASY, "image");

        // ACT
        Chapter chapter = new Chapter("Mon Titre", "Texte narratif", 1, story, "Instruction", "image.jpg");

        // ASSERT
        assertTrue(story.getChapters().contains(chapter));
    }


    @Test
    @DisplayName("Test initialisation des formes dans le constructeur")
    void constructeurInitialise4Shapes() {

        // ARRANGE
        Story story = new Story("Titre", "description", EASY, "image");

        // ACT
        Chapter chapter = new Chapter("Mon Titre", "Texte narratif", 1, story, "Instruction", "image.jpg");

        // ASSERT
        assertEquals(4, chapter.getShapes().size());
    }

    @Test
    @DisplayName("Test le type de forme")
    void constructeurInitialiseLesBonnesShapes() {

        // ARRANGE
        Story story = new Story("Titre", "description", EASY, "image");

        // ACT
        Chapter chapter = new Chapter("Mon Titre", "Texte narratif", 1, story, "Instruction", "image.jpg");

        // ASSERT - assertInstanceOf vérifier que la form est bien de se type
        assertInstanceOf(Rectangle.class, chapter.getShapes().get(0));
        assertInstanceOf(Rond.class, chapter.getShapes().get(1));
        assertInstanceOf(Triangle.class, chapter.getShapes().get(2));
        assertInstanceOf(Carre.class, chapter.getShapes().get(3));
    }

    @Test
    @DisplayName("Test setter/getter")
    void setterModifientLesChamps() {

        // ARRANGE
        Story story = new Story("Titre", "description", EASY, "image");
        Chapter chapter = new Chapter("Mon Titre", "Texte narratif", 1, story, "Instruction", "image.jpg");

        // ACT
        chapter.setTitre("Nouveau Titre");
        chapter.setTexteNarratif("Nouveau texte");
        chapter.setOrder(5);
        chapter.setInstruction("Nouvelle instruction");
        chapter.setImageModele("nouvelle_image.jpg");

        // ASSERT
        assertEquals("Nouveau Titre", chapter.getTitre());
        assertEquals("Nouveau texte", chapter.getTexteNarratif());
        assertEquals(5, chapter.getOrder());
        assertEquals("Nouvelle instruction", chapter.getInstruction());
        assertEquals("nouvelle_image.jpg", chapter.getImageModele());
    }

    @Test
    @DisplayName("Test remplacement de la liste de forme")
    void setShapesRemplaceLaListe() {

        // ARRANGE
        Story story = new Story("Titre", "description", EASY, "image");
        Chapter chapter = new Chapter("Mon Titre", "Texte narratif", 1, story, "Instruction", "image.jpg");

        List<Shape> nouvellesShapes = new ArrayList<>();
        nouvellesShapes.add(new Rectangle( "Black", 5, 5, 10, 20));

        // ACT
        chapter.setShapes(nouvellesShapes); //remplacement de la liste de forme

        // ASSERT
        assertEquals(1, chapter.getShapes().size());
        assertInstanceOf(Rectangle.class, chapter.getShapes().get(0));
    }

    @Test
    @DisplayName("Test périmètre total")
    void afficherPerimetreTotalRetourneLeBonResultat() {

        // ARRANGE
        Story story = new Story("Titre", "description", EASY, "image");
        Chapter chapter = new Chapter("Mon Titre", "Texte narratif", 1, story, "Instruction", "image.jpg");

        // ACT
        String resultat = chapter.afficherPerimetreTotal();

        // ASSERT
        assertEquals("Périmètre total : 498.4955592153876", resultat);
    }

    @Test
    @DisplayName("Test aire total")
    void afficherAireTotalRetourneLaBonneAire() {

        // ARRANGE
        Story story = new Story("Titre", "description", EASY, "image");
        Chapter chapter = new Chapter("Mon Titre", "Texte narratif", 1, story, "Instruction", "image.jpg");

        // ACT
        String resultat = chapter.afficherAireTotal();

        // ASSERT
        assertEquals("Aire totale : 4717.144819933811", resultat);
    }





}
