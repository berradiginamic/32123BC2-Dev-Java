package fr.diginamic.projetspring.dto;

import java.util.Date;

/**
 * DTO représentant un acteur.
 */
public class ActeurDTO {
    private Integer acteurId;        // Identifiant de l'acteur
    private String idIMDB;           // Identifiant IMDB de l'acteur
    private String nom;              // Nom de l'acteur
    private Date dateNaissance;      // Date de naissance de l'acteur
    private String lieuNaissance;    // Lieu de naissance de l'acteur
    private String urlProfile;       // URL du profil de l'acteur sur un site

    /**
     * Getter pour récupérer l'identifiant de l'acteur.
     * @return L'identifiant de l'acteur.
     */
    public Integer getActeurId() {
        return acteurId;
    }

    /**
     * Setter pour définir l'identifiant de l'acteur.
     * @param acteurId L'identifiant de l'acteur.
     */
    public void setActeurId(Integer acteurId) {
        this.acteurId = acteurId;
    }

    /**
     * Getter pour récupérer l'identifiant IMDB de l'acteur.
     * @return L'identifiant IMDB de l'acteur.
     */
    public String getIdIMDB() {
        return idIMDB;
    }

    /**
     * Setter pour définir l'identifiant IMDB de l'acteur.
     * @param idIMDB L'identifiant IMDB de l'acteur.
     */
    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    /**
     * Getter pour récupérer le nom de l'acteur.
     * @return Le nom de l'acteur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter pour définir le nom de l'acteur.
     * @param nom Le nom de l'acteur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour récupérer la date de naissance de l'acteur.
     * @return La date de naissance de l'acteur.
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Setter pour définir la date de naissance de l'acteur.
     * @param dateNaissance La date de naissance de l'acteur.
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Getter pour récupérer le lieu de naissance de l'acteur.
     * @return Le lieu de naissance de l'acteur.
     */
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Setter pour définir le lieu de naissance de l'acteur.
     * @param lieuNaissance Le lieu de naissance de l'acteur.
     */
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    /**
     * Getter pour récupérer l'URL du profil de l'acteur.
     * @return L'URL du profil de l'acteur.
     */
    public String getUrlProfile() {
        return urlProfile;
    }

    /**
     * Setter pour définir l'URL du profil de l'acteur.
     * @param urlProfile L'URL du profil de l'acteur.
     */
    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }
}
