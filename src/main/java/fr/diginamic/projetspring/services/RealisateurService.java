package fr.diginamic.projetspring.services;

import fr.diginamic.projetspring.entities.Realisateur;
import fr.diginamic.projetspring.repositories.RealisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Service gérant les opérations liées à l'entité Realisateur.
 */
@Service
public class RealisateurService {

    /**
     * Constructeur du service Realisateur.
     *
     */
    @Autowired
    private RealisateurRepository realisateurRepository;


    /**
     * Récupère tous les réalisateurs avec pagination.
     *
     * @param pageable L'objet Pageable pour la pagination.
     * @return Une page de réalisateurs.
     */
    public Page<Realisateur> getAllRealisateurs(Pageable pageable) {
        return realisateurRepository.findAll(pageable);
    }

    /**
     * Récupère un réalisateur par son identifiant.
     *
     * @param idRealisateur L'identifiant du réalisateur.
     * @return Le réalisateur correspondant à l'identifiant, ou un Optional vide s'il n'existe pas.
     */
    public Optional<Realisateur> getRealisateurById(Integer idRealisateur) {
        return realisateurRepository.findById(idRealisateur);
    }


    /**
     * Enregistre un nouveau réalisateur dans la base de données.
     *
     * @param realisateur Le réalisateur à enregistrer.
     * @return Le réalisateur enregistré.
     */
    public Realisateur createRealisateur(Realisateur realisateur) {
        return realisateurRepository.save(realisateur);
    }

    /**
     * Enregistre un nouveau réalisateur dans la base de données.
     *
     * @param realisateur Le réalisateur à enregistrer.
     * @return Le réalisateur enregistré.
     */

    public Realisateur updateRealisateur(Integer idRealisateur, Realisateur realisateur) {
        Optional<Realisateur> existingRealisateur = realisateurRepository.findById(idRealisateur);
        if (existingRealisateur.isPresent()) {
            Realisateur updatedRealisateur = existingRealisateur.get();
            updatedRealisateur.setNom(realisateur.getNom());
            updatedRealisateur.setIdIMDB(realisateur.getIdIMDB());
            updatedRealisateur.setDateNaissance(realisateur.getDateNaissance());
            updatedRealisateur.setLieuNaissance(realisateur.getLieuNaissance());
            updatedRealisateur.setUrlProfile(realisateur.getUrlProfile());
            return realisateurRepository.save(updatedRealisateur);
        }
        return null; // Or handle differently, like throwing an exception
    }

    /**
     * Supprime un réalisateur par son identifiant.
     *
     * @param idRealisateur L'identifiant du réalisateur à supprimer.
     */
    public void deleteRealisateurById(Integer idRealisateur) {
        realisateurRepository.deleteById(idRealisateur);
    }

    // Ajoutez d'autres méthodes en fonction des besoins
    public List<Realisateur> findByNom(String nom) {
        return realisateurRepository.findAllByNom(nom);
    }

    public List<Realisateur> findByLieuNaissance(String lieuNaissance) {
        return realisateurRepository.findAllByLieuNaissance(lieuNaissance);
    }

    public List<Realisateur> findByDateNaissance(Date dateNaissance) {
        return realisateurRepository.findAllByDateNaissance(dateNaissance);
    }

    public List<Realisateur> findByUrlProfile(String urlProfile) {
        return realisateurRepository.findAllByUrlProfile(urlProfile);
    }

    public Realisateur findByIdIMDB(String realisateurIdIMDB) {
        return realisateurRepository.findByIdIMDB(realisateurIdIMDB);
    }

    // Implementation des requetes:
    //  Tache 7: Extraire tous les films d’un réalisateur donné
    public List<Object[]> findFilmsByRealisateurId(Integer idRealisateur) {
        return realisateurRepository.findFilmsByRealisateurId(idRealisateur);
    }
}
