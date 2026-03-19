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
            Faker f = new Faker();

            Story story2 = new Story(f.book().title(), f.movie().quote(), DifficultyLevel.EASY, "www.google.com");
            System.out.println(story2.getTitre() + "  " +story2.getDescription());

            if (storyRepository.count() == 0) {

                Story story = new Story(
                        "Reach the Top",
                        "Une histoire de formes geometriques",
                        DifficultyLevel.EASY,
                        "cover.png"
                );
                storyRepository.save(story); // sauvegarde la story pour pouvoir "avoir id de story" avant la création de chapitre

                Chapter chapter1 = new Chapter(
                        "Chapitre 1", "Texte du chapitre 1",
                        1, story, "Instruction 1", "image1.jpg"
                );
                chapter1.getShapes().add(new Rectangle("Red", 10, 20, 30, 20));
                chapter1.getShapes().add(new Rond("Blue", 10, 20, 30));
                chapter1.getShapes().add(new Triangle("Green", 10, 20, 30));
                chapter1.getShapes().add(new Carre("Yellow", 10, 20, 30));

                Chapter chapter2 = new Chapter(
                        "Chapitre 2", "Texte du chapitre 2",
                        2, story, "Instruction 2", "image2.jpg"
                );
                chapter2.getShapes().add(new Rectangle("Purple", 5, 5, 15, 10));
                chapter2.getShapes().add(new Rond("Orange", 5, 5, 20));
                chapter2.getShapes().add(new Triangle("Pink", 5, 5, 25));
                chapter2.getShapes().add(new Carre("Black", 5, 5, 12));

                Chapter chapter3 = new Chapter(
                        "Chapitre 3", "Texte du chapitre 3",
                        3, story, "Instruction 3", "image3.jpg"
                );

                // Sauvegarde finale
                storyRepository.save(story);


            }
        };
    }
}
