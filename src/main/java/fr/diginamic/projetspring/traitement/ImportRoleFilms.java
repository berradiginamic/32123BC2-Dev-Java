package fr.diginamic.projetspring.traitement;

import fr.diginamic.projetspring.entities.Acteur;
import fr.diginamic.projetspring.entities.Film;
import fr.diginamic.projetspring.entities.RoleFilm;
import fr.diginamic.projetspring.services.ActeurService;
import fr.diginamic.projetspring.services.FilmService;
import fr.diginamic.projetspring.services.RoleFilmService;
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
 * La classe {@code ImportRoleFilms} permet d'importer les rôles des acteurs dans les films depuis un fichier CSV.
 * <p>
 * Cette classe lit un fichier CSV contenant des informations sur les rôles des acteurs dans les films,
 * crée des objets {@link RoleFilm} à partir de ces informations et les sauvegarde
 * dans une base de données via le service {@link RoleFilmService}.
 * </p>
 * <p>
 * Les données du fichier CSV doivent être formatées avec les colonnes suivantes :
 * <ol>
 * <li>ID IMDB du Film</li>
 * <li>ID IMDB de l'Acteur</li>
 * <li>Nom du personnage</li>
 * </ol>
 * </p>
 * <p>
 * Les duplicatas d'associations de rôles sont gérés pour éviter les conflits d'intégrité de données.
 * </p>
 */
@Component
public class ImportRoleFilms {

    @Autowired
    private ActeurService acteurService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private RoleFilmService roleFilmService;

    /**
     * Importe les rôles des acteurs dans les films depuis un fichier CSV situé à {@code src/main/resources/dataset/roles.csv}.
     * <p>
     * Cette méthode lit les données du fichier, élimine les duplicatas d'associations et sauvegarde
     * les rôles uniques dans la base de données.
     * </p>
     */
    public void importRoleFilms() {
        Set<String> uniqueRoleFilmIds = new HashSet<>();

        Path pathRoleFilm = Paths.get("src/main/resources/dataset/roles.csv");
        try {
            List<String> rowRoleFilm = Files.readAllLines(pathRoleFilm);
            rowRoleFilm.remove(0); // Supprime l'en-tête du fichier CSV

            for (String rowRoleFilms : rowRoleFilm) {
                System.out.println(rowRoleFilms);
                String[] elements = rowRoleFilms.split(";");
                if (elements.length >= 3) {
                    String acteurIdIMDB = elements[1].trim();
                    String filmIdIMDB = elements[0].trim();
                    String roleId = acteurIdIMDB + "_" + filmIdIMDB;

                    // Vérifier si l'association acteur-film est unique
                    if (!uniqueRoleFilmIds.contains(roleId)) {
                        Acteur acteur = acteurService.findByIdIMDB(acteurIdIMDB);
                        Film film = filmService.findByIdIMDB(filmIdIMDB);

                        if (acteur != null && film != null) {
                            RoleFilm role = createRoleFilmFromElements(acteur, film, elements);
                            try {
                                roleFilmService.createRoleFilm(role);
                                uniqueRoleFilmIds.add(roleId);
                            } catch (DataIntegrityViolationException e) {
                                System.out.println("Duplicate Role ID: " + roleId);
                            }
                        } else {
                            System.out.println("Invalid Acteur or Film ID");
                        }
                    } else {
                        System.out.println("Duplicate Role ID: " + roleId);
                    }
                } else {
                    System.out.println("Insufficient elements in the CSV row");
                }
            }

            System.out.println("Unique Role IDs Set: " + uniqueRoleFilmIds);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un objet {@link RoleFilm} à partir des éléments d'une ligne du fichier CSV.
     * <p>
     * Cette méthode extrait les données d'une ligne du fichier CSV, les formate et les assigne
     * aux propriétés de l'objet {@link RoleFilm}.
     * </p>
     *
     * @param acteur   L'objet {@link Acteur} correspondant à l'acteur du rôle.
     * @param film     L'objet {@link Film} correspondant au film du rôle.
     * @param elements Les éléments de la ligne CSV, séparés par des points-virgules.
     * @return Un objet {@link RoleFilm} avec les propriétés définies à partir des éléments de la ligne CSV.
     */
    private RoleFilm createRoleFilmFromElements(Acteur acteur, Film film, String[] elements) {
        RoleFilm role = new RoleFilm();
        role.setActeur(acteur);
        role.setFilm(film);
        role.setPersonnage(elements[2]);
        return role;
    }
}
