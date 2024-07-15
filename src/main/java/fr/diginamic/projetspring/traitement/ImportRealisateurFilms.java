package fr.diginamic.projetspring.traitement;

import fr.diginamic.projetspring.entities.Film;
import fr.diginamic.projetspring.entities.Realisateur;
import fr.diginamic.projetspring.entities.RealisateurFilm;
import fr.diginamic.projetspring.services.FilmService;
import fr.diginamic.projetspring.services.RealisateurService;
import fr.diginamic.projetspring.services.RealisateurFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * La classe {@code ImportRealisateurFilms} permet d'importer les associations de réalisateurs et de films depuis un fichier CSV.
 * <p>
 * Cette classe lit un fichier CSV contenant des informations sur les associations de réalisateurs et de films,
 * crée des objets {@link RealisateurFilm} à partir de ces informations et les sauvegarde
 * dans une base de données via le service {@link RealisateurFilmService}.
 * </p>
 * <p>
 * Les données du fichier CSV doivent être formatées avec les colonnes suivantes :
 * <ol>
 * <li>ID IMDB du Film</li>
 * <li>ID IMDB du Réalisateur</li>
 * </ol>
 * </p>
 * <p>
 * Les duplicatas d'associations de films et de réalisateurs sont gérés pour éviter les conflits d'intégrité de données.
 * </p>
 */
@Component
public class ImportRealisateurFilms {

    @Autowired
    private RealisateurService realisateurService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private RealisateurFilmService realisateurFilmService;

    /**
     * Importe les associations de réalisateurs et de films depuis un fichier CSV situé à {@code src/main/resources/dataset/film_realisateurs.csv}.
     * <p>
     * Cette méthode lit les données du fichier, élimine les duplicatas d'associations et sauvegarde
     * les associations uniques dans la base de données.
     * </p>
     */
    public void importFilmRealisateurs() {
        Set<String> uniqueRealisateurFilmIds = new HashSet<>();

        Path pathRealisateurFilm = Paths.get("src/main/resources/dataset/film_realisateurs.csv");
        try {
            List<String> rowsRealisateurFilm = Files.readAllLines(pathRealisateurFilm);
            rowsRealisateurFilm.remove(0); // Supprime l'en-tête du fichier CSV

            for (String rowRealisateurFilms : rowsRealisateurFilm) {
                System.out.println(rowRealisateurFilms);
                String[] elements = rowRealisateurFilms.split(";");
                String filmIdIMDB = elements[0].trim();
                String realisateurIdIMDB = elements[1].trim();

                String realisateurFilmId = realisateurIdIMDB + "_" + filmIdIMDB;

                // Vérifier si l'association réalisateur-film est unique
                if (!uniqueRealisateurFilmIds.contains(realisateurFilmId)) {
                    Realisateur realisateur = realisateurService.findByIdIMDB(realisateurIdIMDB);
                    Film film = filmService.findByIdIMDB(filmIdIMDB);

                    if (realisateur != null && film != null) {
                        RealisateurFilm realisateurFilm = new RealisateurFilm();
                        realisateurFilm.setRealisateur(realisateur);
                        realisateurFilm.setFilm(film);
                        realisateurFilmService.createRealisateurFilm(realisateurFilm);

                        uniqueRealisateurFilmIds.add(realisateurFilmId);
                    } else {
                        System.out.println("Invalid Realisateur or Film ID");
                    }
                } else {
                    System.out.println("Duplicate RealisateurFilm ID: " + realisateurFilmId);
                }
            }

            System.out.println("Unique RealisateurFilm IDs Set: " + uniqueRealisateurFilmIds);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
