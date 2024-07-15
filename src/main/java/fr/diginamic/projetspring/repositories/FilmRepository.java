package fr.diginamic.projetspring.repositories;

import fr.diginamic.projetspring.entities.Film;
import fr.diginamic.projetspring.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * Interface repository pour l'entité Film, utilisant Spring Data JPA.
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {

    /**
     * Recherche tous les films par année de sortie.
     *
     * @param anneeSortie L'année de sortie des films à rechercher.
     * @return La liste des films correspondant à l'année de sortie spécifiée.
     */
    List<Film> findAllByAnneeSortie(Integer anneeSortie);

    /**
     * Recherche tous les films par langue.
     *
     * @param langue La langue des films à rechercher.
     * @return La liste des films correspondant à la langue spécifiée.
     */
    List<Film> findAllByLangue(String langue);

    /**
     * Recherche tous les films par lieu de tournage.
     *
     * @param lieuTournage Le lieu de tournage des films à rechercher.
     * @return La liste des films correspondant au lieu de tournage spécifié.
     */
    List<Film> findAllByLieuTournage(String lieuTournage);

    /**
     * Recherche tous les films par nom.
     *
     * @param nom Le nom des films à rechercher.
     * @return La liste des films correspondant au nom spécifié.
     */
    List<Film> findAllByNom(String nom);

    /**
     * Recherche tous les films par pays.
     *
     * @param pays Le pays des films à rechercher.
     * @return La liste des films correspondant au pays spécifié.
     */
    List<Film> findAllByPays(String pays);

    /**
     * Recherche tous les films par rating.
     *
     * @param rating Le rating des films à rechercher.
     * @return La liste des films correspondant au rating spécifié.
     */
    List<Film> findAllByRating(String rating);

    /**
     * Recherche tous les films par résumé.
     *
     * @param resume Le résumé des films à rechercher.
     * @return La liste des films correspondant au résumé spécifié.
     */
    List<Film> findAllByResume(String resume);

    /**
     * Recherche tous les films par URL de profil.
     *
     * @param urlProfile L'URL de profil des films à rechercher.
     * @return La liste des films correspondant à l'URL de profil spécifiée.
     */
    List<Film> findAllByUrlProfile(String urlProfile);

    /**
     * Recherche tous les films par genre.
     *
     * @param genres Le set de genres des films à rechercher.
     * @return La liste des films correspondant à l'un des genres spécifiés.
     */
    List<Film> findAllByGenresIn(Set<Genre> genres);

    /**
     * Trouve un film par son identifiant IMDB.
     *
     * @param idIMDB L'identifiant IMDB du film à rechercher.
     * @return Le film correspondant à l'identifiant IMDB spécifié, ou null s'il n'existe pas.
     */
    Film findByIdIMDB(String idIMDB);

    List<Film> findByGenres_Type(String genreType);

    /**
     * Requête pour extraire tous les acteurs et leurs personnages dans un film donné.
     *
     * @param filmId L'identifiant du film dont on veut extraire les acteurs et leurs personnages.
     * @return Une liste d'objets contenant le nom de l'acteur et le personnage qu'il joue.
     */
    @Query("SELECT a.nom AS acteur_nom, r.personnage " +
            "FROM Film f " +
            "JOIN RoleFilm r ON f.filmId = r.film.filmId " +
            "JOIN Acteur a ON r.acteur.acteurId = a.acteurId " +
            "WHERE f.filmId = :filmId")
    List<Object[]> findActorsAndCharactersByFilmId(@Param("filmId") Integer filmId);

    /**
     * Requête pour extraire les films sortis entre deux années données.
     *
     * @param startYear Année de début pour la recherche.
     * @param endYear   Année de fin pour la recherche.
     * @return La liste des films sortis entre les années spécifiées.
     */
    @Query("SELECT f FROM Film f WHERE f.anneeSortie BETWEEN :startYear AND :endYear")
    List<Film> findFilmsReleasedBetweenYears(@Param("startYear") int startYear, @Param("endYear") int endYear);

    /**
     * Requête pour extraire les films communs à deux acteurs donnés.
     *
     * @param acteurId1 Identifiant du premier acteur.
     * @param acteurId2 Identifiant du deuxième acteur.
     * @return Une liste d'objets contenant le nom des films communs.
     */
    @Query("SELECT f.nom AS filmNom, f.anneeSortie " +
            "FROM Acteur a1 " +
            "JOIN RoleFilm r1 ON a1.acteurId = r1.acteur.acteurId " +
            "JOIN Film f ON r1.film.filmId = f.filmId " +
            "JOIN RoleFilm r2 ON f.filmId = r2.film.filmId " +
            "JOIN Acteur a2 ON r2.acteur.acteurId = a2.acteurId " +
            "WHERE a1.acteurId = :acteurId1 AND a2.acteurId = :acteurId2")
    List<Object[]> findFilmsByTwoActors(@Param("acteurId1") Integer acteurId1, @Param("acteurId2") Integer acteurId2);

    /**
     * Requête pour extraire tous les films d'un genre donné.
     *
     * @param genreId L'identifiant du genre dont on veut extraire les films.
     * @return Une liste d'objets contenant le nom des films et leur année de sortie.
     */
    @Query("SELECT f.nom AS filmNom, f.anneeSortie " +
            "FROM Genre g " +
            "JOIN g.films f " +
            "WHERE g.genreId = :genreId")
    List<Object[]> findFilmsByGenre(@Param("genreId") Integer genreId);

    /**
     * Requête pour extraire les films sortis entre deux années données et qui ont un acteur donné.
     *
     * @param startYear Année de début pour la recherche.
     * @param endYear   Année de fin pour la recherche.
     * @param acteurId  Identifiant de l'acteur recherché.
     * @return Une liste d'objets contenant le nom des films et leur année de sortie.
     */
    @Query("SELECT DISTINCT f.nom AS film_nom, f.anneeSortie " +
            "FROM Film f " +
            "JOIN RoleFilm r ON f.filmId = r.film.filmId " +
            "JOIN Acteur a ON r.acteur.acteurId = a.acteurId " +
            "WHERE f.anneeSortie BETWEEN :startYear AND :endYear " +
            "AND a.acteurId = :acteurId")
    List<Object[]> findFilmsBetweenYearsAndByActeur(@Param("startYear") Integer startYear,
                                                    @Param("endYear") Integer endYear,
                                                    @Param("acteurId") Integer acteurId);

}
