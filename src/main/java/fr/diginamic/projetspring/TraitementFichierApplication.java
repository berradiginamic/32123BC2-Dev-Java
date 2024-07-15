package fr.diginamic.projetspring;

import fr.diginamic.projetspring.traitement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * La classe {@code TraitementFichierApplication} est le point d'entrée de l'application Spring Boot.
 * <p>
 * Cette classe implémente {@link CommandLineRunner} pour exécuter les traitements de fichiers CSV
 * lors du démarrage de l'application. Elle importe les données des acteurs, des films, des réalisateurs,
 * des associations réalisateur-film et des rôles des acteurs dans les films.
 * </p>
 */
@SpringBootApplication
public class TraitementFichierApplication implements CommandLineRunner {

    @Autowired
    private ImportFilms importFilms;

    @Autowired
    private ImportActeurs importActeurs;

    @Autowired
    private ImportRealisateurs importRealisateurs;

    @Autowired
    private ImportRealisateurFilms importRealisateurFilms;

    @Autowired
    private ImportRoleFilms importRoleFilms;

    /**
     * Le point d'entrée principal de l'application.
     * <p>
     * Cette méthode démarre l'application Spring Boot.
     * </p>
     *
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SpringApplication.run(TraitementFichierApplication.class, args);
    }

    /**
     * Méthode appelée après que le contexte d'application soit chargé et démarré.
     * <p>
     * Cette méthode exécute les traitements d'importation des fichiers CSV pour les acteurs,
     * les films, les réalisateurs, les associations réalisateur-film et les rôles des acteurs
     * dans les films.
     * </p>
     *
     * @param args les arguments de la ligne de commande
     * @throws Exception en cas d'erreur lors de l'importation des fichiers
     */
    @Override
    public void run(String... args) throws Exception {
        importActeurs.importActeurs();
        importFilms.importFilms();
        importRealisateurs.importRealisateurs();
        importRealisateurFilms.importFilmRealisateurs();
        importRoleFilms.importRoleFilms();
    }
}
