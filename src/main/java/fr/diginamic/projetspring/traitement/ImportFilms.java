package fr.diginamic.projetspring.traitement;

import fr.diginamic.projetspring.entities.Film;
import fr.diginamic.projetspring.entities.Genre;
import fr.diginamic.projetspring.services.FilmService;
import fr.diginamic.projetspring.services.GenreService;
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
 * La classe {@code ImportFilms} permet d'importer les données des films depuis un fichier CSV.
 * <p>
 * Cette classe lit un fichier CSV contenant des informations sur des films,
 * crée des objets {@link Film} à partir de ces informations et les sauvegarde
 * dans une base de données via le service {@link FilmService}.
 * </p>
 * <p>
 * Les données du fichier CSV doivent être formatées avec les colonnes suivantes :
 * <ol>
 * <li>ID IMDB</li>
 * <li>Nom</li>
 * <li>Année de sortie</li>
 * <li>Rating</li>
 * <li>URL du profil</li>
 * <li>Lieu de tournage</li>
 * <li>Genres (séparés par des virgules)</li>
 * <li>Langue</li>
 * <li>Résumé</li>
 * <li>Pays</li>
 * </ol>
 * </p>
 * <p>
 * Les duplicatas d'ID IMDB sont gérés pour éviter les conflits d'intégrité de données.
 * </p>
 */
@Component
public class ImportFilms {

    @Autowired
    private FilmService filmService;

    @Autowired
    private GenreService genreService;

    /**
     * Convertit une chaîne de caractères représentant les genres en un ensemble d'objets {@link Genre}.
     *
     * @param genresString La chaîne de caractères contenant les genres séparés par des virgules.
     * @return Un ensemble d'objets {@link Genre}.
     */
    private Set<Genre> convertGenres(String genresString) {
        Set<Genre> genres = new HashSet<>();
        String[] genreTypes = genresString.split(",");

        for (String genreType : genreTypes) {
            Genre genre = genreService.findOrCreateGenreByType(genreType.trim());
            genres.add(genre);
        }

        return genres;
    }

    /**
     * Importe les films depuis un fichier CSV situé à {@code src/main/resources/dataset/films.csv}.
     * <p>
     * Cette méthode lit les données du fichier, élimine les duplicatas d'ID IMDB et sauvegarde
     * les films uniques dans la base de données.
     * </p>
     */
    public void importFilms() {
        Set<String> uniqueFilmIds = new HashSet<>();

        Path pathFilms = Paths.get("src/main/resources/dataset/films.csv");
        try {
            List<String> rowFilms = Files.readAllLines(pathFilms);
            rowFilms.remove(0); // Supprime l'en-tête du fichier CSV

            for (String rowFilm : rowFilms) {
                System.out.println(rowFilm);
                String[] elements = rowFilm.split(";");
                if (elements.length < 10) {
                    System.out.println("Invalid data: " + rowFilm);
                    continue;
                }
                String idIMDB = elements[0].trim();
                // Vérifier si l'ID IMDB est unique
                if (!uniqueFilmIds.contains(idIMDB)) {
                    Film film = createFilmFromElements(elements);
                    try {
                        // Sauvegarder le film dans la base de données
                        filmService.createFilm(film);
                        // Ajouter l'ID IMDB à l'ensemble des IDs uniques
                        uniqueFilmIds.add(idIMDB);
                    } catch (DataIntegrityViolationException e) {
                        System.out.println("Duplicate ID: " + idIMDB);
                    }
                } else {
                    System.out.println("Duplicate ID: " + idIMDB);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un objet {@link Film} à partir des éléments d'une ligne du fichier CSV.
     * <p>
     * Cette méthode extrait les données d'une ligne du fichier CSV, les formate et les assigne
     * aux propriétés de l'objet {@link Film}.
     * </p>
     *
     * @param elements Les éléments de la ligne CSV, séparés par des points-virgules.
     * @return Un objet {@link Film} avec les propriétés définies à partir des éléments de la ligne CSV.
     */
    private Film createFilmFromElements(String[] elements) {
        Film film = new Film();
        film.setIdIMDB(elements[0].trim());
        film.setNom(elements[1]);

        if (elements.length >= 3) {
            try {
                film.setAnneeSortie(Integer.valueOf(elements[2]));
            } catch (NumberFormatException e) {
                System.out.println("Error converting film data: " + elements);
                e.printStackTrace();
            }
        }

        film.setRating(elements[3]);
        film.setUrlProfile(elements[4]);
        film.setLieuTournage(elements[5]);

        if (elements.length >= 8) {
            String resume = elements[8];
            if (resume.length() > 255) {
                resume = resume.substring(0, 255);
            }
            film.setResume(resume);
        } else {
            film.setResume("");
        }

        // Convertir les genres et les assigner au film
        if (elements.length >= 7) {
            String genresString = elements[6];
            Set<Genre> genres = convertGenres(genresString);
            film.setGenres(genres);
        }

        film.setLangue(elements[7]);
        film.setPays(elements[9]);

        return film;
    }
}
