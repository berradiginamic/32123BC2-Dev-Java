package fr.diginamic.projetspring.dto;

import fr.diginamic.projetspring.entities.Genre;
import java.util.Set;

/**
 * DTO représentant un film avec ses caractéristiques et les genres associés.
 */
public class FilmDTO {

    private Integer filmId;          // Identifiant du film
    private String nom;              // Nom du film
    private String idIMDB;           // Identifiant IMDB du film
    private Integer anneeSortie;     // Année de sortie du film
    private String rating;           // Note du film
    private String urlProfile;       // URL du profil du film
    private String lieuTournage;     // Lieu de tournage du film
    private String langue;           // Langue du film
    private String resume;           // Résumé du film
    private String pays;             // Pays de production du film
    private Set<Genre> genres;       // Ensemble des genres du film

    /**
     * Constructeur par défaut.
     */
    public FilmDTO() {
    }

    /**
     * Constructeur avec tous les paramètres.
     * @param filmId Identifiant du film.
     * @param nom Nom du film.
     * @param idIMDB Identifiant IMDB du film.
     * @param anneeSortie Année de sortie du film.
     * @param rating Note du film.
     * @param urlProfile URL du profil du film.
     * @param lieuTournage Lieu de tournage du film.
     * @param langue Langue du film.
     * @param resume Résumé du film.
     * @param pays Pays de production du film.
     * @param genres Ensemble des genres du film.
     */
    public FilmDTO(Integer filmId, String nom, String idIMDB, Integer anneeSortie, String rating, String urlProfile,
                   String lieuTournage, String langue, String resume, String pays, Set<Genre> genres) {
        this.filmId = filmId;
        this.nom = nom;
        this.idIMDB = idIMDB;
        this.anneeSortie = anneeSortie;
        this.rating = rating;
        this.urlProfile = urlProfile;
        this.lieuTournage = lieuTournage;
        this.langue = langue;
        this.resume = resume;
        this.pays = pays;
        this.genres = genres;
    }

    // Getters and Setters

    /**
     * Getter pour récupérer l'identifiant du film.
     * @return L'identifiant du film.
     */
    public Integer getFilmId() {
        return filmId;
    }

    /**
     * Setter pour définir l'identifiant du film.
     * @param filmId L'identifiant du film.
     */
    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    /**
     * Getter pour récupérer le nom du film.
     * @return Le nom du film.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter pour définir le nom du film.
     * @param nom Le nom du film.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour récupérer l'identifiant IMDB du film.
     * @return L'identifiant IMDB du film.
     */
    public String getIdIMDB() {
        return idIMDB;
    }

    /**
     * Setter pour définir l'identifiant IMDB du film.
     * @param idIMDB L'identifiant IMDB du film.
     */
    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    /**
     * Getter pour récupérer l'année de sortie du film.
     * @return L'année de sortie du film.
     */
    public Integer getAnneeSortie() {
        return anneeSortie;
    }

    /**
     * Setter pour définir l'année de sortie du film.
     * @param anneeSortie L'année de sortie du film.
     */
    public void setAnneeSortie(Integer anneeSortie) {
        this.anneeSortie = anneeSortie;
    }

    /**
     * Getter pour récupérer la note du film.
     * @return La note du film.
     */
    public String getRating() {
        return rating;
    }

    /**
     * Setter pour définir la note du film.
     * @param rating La note du film.
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Getter pour récupérer l'URL du profil du film.
     * @return L'URL du profil du film.
     */
    public String getUrlProfile() {
        return urlProfile;
    }

    /**
     * Setter pour définir l'URL du profil du film.
     * @param urlProfile L'URL du profil du film.
     */
    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    /**
     * Getter pour récupérer le lieu de tournage du film.
     * @return Le lieu de tournage du film.
     */
    public String getLieuTournage() {
        return lieuTournage;
    }

    /**
     * Setter pour définir le lieu de tournage du film.
     * @param lieuTournage Le lieu de tournage du film.
     */
    public void setLieuTournage(String lieuTournage) {
        this.lieuTournage = lieuTournage;
    }

    /**
     * Getter pour récupérer la langue du film.
     * @return La langue du film.
     */
    public String getLangue() {
        return langue;
    }

    /**
     * Setter pour définir la langue du film.
     * @param langue La langue du film.
     */
    public void setLangue(String langue) {
        this.langue = langue;
    }

    /**
     * Getter pour récupérer le résumé du film.
     * @return Le résumé du film.
     */
    public String getResume() {
        return resume;
    }

    /**
     * Setter pour définir le résumé du film.
     * @param resume Le résumé du film.
     */
    public void setResume(String resume) {
        this.resume = resume;
    }

    /**
     * Getter pour récupérer le pays de production du film.
     * @return Le pays de production du film.
     */
    public String getPays() {
        return pays;
    }

    /**
     * Setter pour définir le pays de production du film.
     * @param pays Le pays de production du film.
     */
    public void setPays(String pays) {
        this.pays = pays;
    }

    /**
     * Getter pour récupérer les genres du film.
     * @return Les genres du film.
     */
    public Set<Genre> getGenres() {
        return genres;
    }

    /**
     * Setter pour définir les genres du film.
     * @param genres Les genres du film.
     */
    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    // Méthode toString

    @Override
    public String toString() {
        return "FilmDTO{" +
                "filmId=" + filmId +
                ", nom='" + nom + '\'' +
                ", idIMDB='" + idIMDB + '\'' +
                ", anneeSortie=" + anneeSortie +
                ", rating='" + rating + '\'' +
                ", urlProfile='" + urlProfile + '\'' +
                ", lieuTournage='" + lieuTournage + '\'' +
                ", langue='" + langue + '\'' +
                ", resume='" + resume + '\'' +
                ", pays='" + pays + '\'' +
                ", genres=" + genres +
                '}';
    }
}
