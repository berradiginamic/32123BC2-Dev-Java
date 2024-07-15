package fr.diginamic.projetspring.services;

import fr.diginamic.projetspring.entities.Acteur;
import fr.diginamic.projetspring.repositories.ActeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations liées à l'entité Acteur.
 */
@Service
public class ActeurService {
    /**
     * Constructeur du service Acteur.
     *
     */
    @Autowired
    private ActeurRepository acteurRepository;

    /**
     * Récupère tous les acteurs.
     *
     * @return Une liste de tous les acteurs.
     */
    public Page<Acteur> getAllActeurs(Pageable pageable) {
        return acteurRepository.findAll(pageable);
    }

    /**
     * Récupère un acteur par son identifiant.
     *
     * @param acteurId L'identifiant de l'acteur.
     * @return L'acteur correspondant à l'identifiant, ou null s'il n'existe pas.
     */
    public Acteur getActeurById(Integer acteurId) {
        return acteurRepository.findById(acteurId).orElse(null);
    }

    /**
     * Crée un nouvel acteur.
     *
     * @param acteur L'acteur à créer.
     * @return L'acteur créé.
     */
    public Acteur createActeur(Acteur acteur) {
        return acteurRepository.save(acteur);
    }

    /**
     * Met à jour un acteur existant.
     *
     * @param acteurId     L'identifiant de l'acteur à mettre à jour.
     * @param acteur Les nouvelles données de l'acteur.
     * @return L'acteur mis à jour, ou null si l'acteur avec l'ID spécifié n'existe pas.
     */
    public Acteur updateActeur(Integer acteurId, Acteur acteur) {
        Optional<Acteur> existingActeur = acteurRepository.findById(acteurId);
        if (existingActeur.isPresent()) {
            Acteur updatedActeur = existingActeur.get();
            updatedActeur.setNom(acteur.getNom());
            updatedActeur.setIdIMDB(acteur.getIdIMDB());
            updatedActeur.setDateNaissance(acteur.getDateNaissance());
            updatedActeur.setLieuNaissance(acteur.getLieuNaissance());
            updatedActeur.setUrlProfile(acteur.getUrlProfile());
            updatedActeur.setActeurId(acteurId);
            return acteurRepository.save(updatedActeur);
        } else {
            throw new IllegalArgumentException("Acteur with ID " + acteurId + " not found");
        }
    }

    /**
     * Supprime un acteur par son identifiant.
     *
     * @param acteurId L'identifiant de l'acteur à supprimer.
     */
    public void deleteActeur(Integer acteurId) {
        acteurRepository.deleteById(acteurId);
    }


    public List<Acteur> findByNom(String nom) {
        return acteurRepository.findAllByNom(nom);
    }

    public Acteur findByIdIMDB(String idIMDB){
        return acteurRepository.findByIdIMDB(idIMDB);
    }

    public List<Acteur> findByLieuNaissance(String lieuNaissance) {
        return acteurRepository.findAllByLieuNaissance(lieuNaissance);
    }

    public List<Acteur> findByDateNaissance(Date dateNaissance) {
        return acteurRepository.findAllByDateNaissance(dateNaissance);
    }

    public List<Acteur> findByUrlProfile(String urlProfile) {
        return acteurRepository.findAllByUrlProfile(urlProfile);
    }

    // Implementations des requetes:
    // Tache 1: Extraire tous les films (nom et années de sortie) d’un acteur donné
    public List<Object[]> findFilmsByActeurId(Integer acteurId) {
        return acteurRepository.findFilmsByActeurId(acteurId);
    }

    // Tache 6:  Extraire les acteurs communs à 2 films donnés
    public List<Object[]> findActeursInFilms(Integer filmId1, Integer filmId2) {
        return acteurRepository.findActeursInFilms(filmId1, filmId2);
    }

}
