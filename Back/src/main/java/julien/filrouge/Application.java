package julien.filrouge;

import julien.filrouge.forme.mesformes.*;
import julien.filrouge.histoire.*;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initData(StoryRepository storyRepository) {
        return args -> {
            if (storyRepository.count() == 0) {

                Story story1 = new Story(
                        "Reach the Top",
                        "Une histoire de formes geometriques",
                        DifficultyLevel.EASY
                );
                storyRepository.save(story1);

                Chapter chapter1 = new Chapter(
                        "Chapitre 1", "Texte du chapitre 1", 1, story1, "Instruction 1"
                );
                chapter1.getShapes().add(new Rectangle("Red", 10, 20, 30, 20));
                chapter1.getShapes().add(new Rond("Blue", 10, 20, 30));
                chapter1.getShapes().add(new Triangle("Green", 10, 20, 30));
                chapter1.getShapes().add(new Carre("Yellow", 10, 20, 30));

                Chapter chapter2 = new Chapter(
                        "Chapitre 2", "Texte du chapitre 2", 2, story1, "Instruction 2"
                );
                chapter2.getShapes().add(new Rectangle("Purple", 5, 5, 15, 10));
                chapter2.getShapes().add(new Rond("Orange", 5, 5, 20));
                chapter2.getShapes().add(new Triangle("Pink", 5, 5, 25));
                chapter2.getShapes().add(new Carre("Black", 5, 5, 12));

                Chapter chapter3 = new Chapter(
                        "Chapitre 3", "Texte du chapitre 3", 3, story1, "Instruction 3"
                );

                story1.addChapter(chapter1);
                story1.addChapter(chapter2);
                story1.addChapter(chapter3);
                storyRepository.save(story1);

                Story story2 = new Story(
                        "Le Dragon des Ombres",
                        "Une quête épique dans les montagnes sombres",
                        DifficultyLevel.HARD
                );

                Chapter chapter4 = new Chapter(
                        "Chapitre 1 - L'Appel", "Vous entendez un cri dans la nuit...",
                        1, story2, "Cliquez sur le dragon"
                );
                chapter4.getShapes().add(new Rond("Red", 50, 50, 40));
                chapter4.getShapes().add(new Triangle("Black", 100, 100, 60));

                Chapter chapter5 = new Chapter(
                        "Chapitre 2 - La Montagne", "La brume est épaisse...",
                        2, story2, "Trouvez le chemin"
                );
                chapter5.getShapes().add(new Carre("Gray", 200, 150, 80));
                chapter5.getShapes().add(new Rectangle("Brown", 250, 200, 100, 50));

                story2.addChapter(chapter4);
                story2.addChapter(chapter5);
                storyRepository.save(story2);
            }
        };
    }
}

