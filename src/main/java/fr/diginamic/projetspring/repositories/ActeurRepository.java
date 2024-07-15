package fr.diginamic.projetspring.repositories;

import fr.diginamic.projetspring.entities.Acteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Interface repository pour l'entité Acteur, utilisant Spring Data JPA.
 */
public interface ActeurRepository extends JpaRepository<Acteur, Integer> {

    /**
     * Recherche tous les acteurs par nom.
     *
     * @param nom Le nom des acteurs à rechercher.
     * @return La liste des acteurs correspondant au nom spécifié.
     */
    List<Acteur> findAllByNom(String nom);

    /**
     * Recherche tous les acteurs par lieu de naissance.
     *
     * @param lieuNaissance Le lieu de naissance des acteurs à rechercher.
     * @return La liste des acteurs correspondant au lieu de naissance spécifié.
     */
    List<Acteur> findAllByLieuNaissance(String lieuNaissance);

    /**
     * Recherche tous les acteurs par date de naissance.
     *
     * @param dateNaissance La date de naissance des acteurs à rechercher.
     * @return La liste des acteurs correspondant à la date de naissance spécifiée.
     */
    List<Acteur> findAllByDateNaissance(Date dateNaissance);

    /**
     * Recherche tous les acteurs par URL de profil.
     *
     * @param urlProfile L'URL de profil des acteurs à rechercher.
     * @return La liste des acteurs correspondant à l'URL de profil spécifiée.
     */
    List<Acteur> findAllByUrlProfile(String urlProfile);

    /**
     * Trouve un acteur par son identifiant IMDB.
     *
     * @param idIMDB L'identifiant IMDB de l'acteur à rechercher.
     * @return L'acteur correspondant à l'identifiant IMDB spécifié, ou null s'il n'existe pas.
     */
    Acteur findByIdIMDB(String idIMDB);

    /**
     * Requête pour extraire tous les films (nom et années de sortie) d'un acteur donné.
     *
     * @param acteurId L'identifiant de l'acteur dont on veut extraire les films.
     * @return Une liste d'objets contenant le nom et l'année de sortie des films.
     */
    @Query("SELECT f.nom AS film_nom, f.anneeSortie " +
            "FROM Acteur a " +
            "JOIN RoleFilm r ON a.acteurId = r.acteur.acteurId " +
            "JOIN Film f ON r.film.filmId = f.filmId " +
            "WHERE a.acteurId = :acteurId")
    List<Object[]> findFilmsByActeurId(@Param("acteurId") Integer acteurId);

    /**
     * Requête pour extraire les acteurs communs à 2 films donnés.
     *
     * @param filmId1 Identifiant du premier film.
     * @param filmId2 Identifiant du deuxième film.
     * @return Une liste d'objets contenant le nom des acteurs communs.
     */
    @Query("SELECT a.nom AS acteurNom " +
            "FROM Film f1 " +
            "JOIN RoleFilm r1 ON f1.filmId = r1.film.filmId " +
            "JOIN Acteur a ON r1.acteur.acteurId = a.acteurId " +
            "JOIN RoleFilm r2 ON a.acteurId = r2.acteur.acteurId " +
            "JOIN Film f2 ON r2.film.filmId = f2.filmId " +
            "WHERE f1.filmId = :filmId1 AND f2.filmId = :filmId2")
    List<Object[]> findActeursInFilms(@Param("filmId1") Integer filmId1,
                                      @Param("filmId2") Integer filmId2);

}
