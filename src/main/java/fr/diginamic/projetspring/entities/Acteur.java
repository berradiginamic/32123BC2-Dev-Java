package fr.diginamic.projetspring.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Représente un acteur participant à des films.
 */
@Entity
public class Acteur {

    /** Identifiant unique de l'acteur. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer acteurId;

    /** Identifiant IMDB de l'acteur. */
    @Column(unique = true)
    private String idIMDB;

    /** Nom de l'acteur. */
    private String nom;

    /** Date de naissance de l'acteur. */
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    /** Lieu de naissance de l'acteur. */
    private String lieuNaissance;

    /** URL du profil de l'acteur. */
    private String urlProfile;

    /** Liste des rôles que l'acteur a joués dans des films. */
    @OneToMany(mappedBy = "acteur")
    private List<RoleFilm> rolefilm;

    // Constructeurs

    /**
     * Constructeur par défaut.
     */
    public Acteur() {
    }

    /**
     * Constructeur avec les paramètres nécessaires pour créer un nouvel acteur.
     *
     * @param acteurIdStr Identifiant de l'acteur (converti en entier).
     * @param lieuNaissance Lieu de naissance de l'acteur.
     * @param dateNaissance Date de naissance de l'acteur.
     * @param nom Nom de l'acteur.
     * @param urlProfile URL du profil de l'acteur.
     */
    public Acteur(String acteurIdStr, String lieuNaissance, Date dateNaissance, String nom, String urlProfile) {
        this.acteurId = Integer.parseInt(acteurIdStr);
        this.lieuNaissance = lieuNaissance;
        this.dateNaissance = dateNaissance;
        this.nom = nom;
        this.urlProfile = urlProfile;
    }

    // Getters and setters

    /**
     * Obtient l'identifiant unique de l'acteur dans notre base de données.
     *
     * @return L'identifiant unique de l'acteur dans notre base de données.
     */
    public Integer getActeurId() {
        return acteurId;
    }

    /**
     * Définit l'identifiant unique de l'acteur dans notre base de données.
     *
     * @param acteurId L'identifiant unique de l'acteur dans notre base de données.
     */
    public void setActeurId(Integer acteurId) {
        this.acteurId = acteurId;
    }

    /**
     * Obtient l'identifiant IMDB de l'acteur.
     *
     * @return L'identifiant IMDB de l'acteur.
     */
    public String getIdIMDB() {
        return idIMDB;
    }

    /**
     * Définit l'identifiant IMDB de l'acteur.
     *
     * @param idIMDB L'identifiant IMDB de l'acteur.
     */
    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }

    /**
     * Obtient le nom de l'acteur.
     *
     * @return Le nom de l'acteur.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'acteur.
     *
     * @param nom Le nom de l'acteur.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la date de naissance de l'acteur.
     *
     * @return La date de naissance de l'acteur.
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Définit la date de naissance de l'acteur.
     *
     * @param dateNaissance La date de naissance de l'acteur.
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Obtient le lieu de naissance de l'acteur.
     *
     * @return Le lieu de naissance de l'acteur.
     */
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Définit le lieu de naissance de l'acteur.
     *
     * @param lieuNaissance Le lieu de naissance de l'acteur.
     */
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    /**
     * Obtient l'URL du profil de l'acteur.
     *
     * @return L'URL du profil de l'acteur.
     */
    public String getUrlProfile() {
        return urlProfile;
    }

    /**
     * Définit l'URL du profil de l'acteur.
     *
     * @param urlProfile L'URL du profil de l'acteur.
     */
    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    /**
     * Obtient la liste des rôles que l'acteur a joués dans des films.
     *
     * @return La liste des rôles que l'acteur a joués.
     */
    public List<RoleFilm> getRolefilm() {
        return rolefilm;
    }

    /**
     * Définit la liste des rôles que l'acteur a joués dans des films.
     *
     * @param rolefilm La liste des rôles que l'acteur a joués.
     */
    public void setRolefilm(List<RoleFilm> rolefilm) {
        this.rolefilm = rolefilm;
    }

    /**
     * Renvoie une chaîne représentant l'objet acteur.
     *
     * @return Une chaîne représentant l'objet acteur.
     */
    @Override
    public String toString() {
        return "Acteur{" +
                "acteurId=" + acteurId +
                ", lieuNaissance='" + lieuNaissance + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", nom='" + nom + '\'' +
                ", urlProfile='" + urlProfile + '\'' +
                ", roles=" + rolefilm +
                '}';
    }
}
