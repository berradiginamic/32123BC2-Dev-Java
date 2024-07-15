package fr.diginamic.projetspring.controllers;

import fr.diginamic.projetspring.entities.Realisateur;
import fr.diginamic.projetspring.services.RealisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Contrôleur REST pour la gestion des réalisateurs.
 */
@RestController
@RequestMapping("/realisateurs") // ajouter /api/realisateurs si besoins
public class RealisateurController {

    /** Service gérant la logique métier des réalisateurs. */
    @Autowired
    private RealisateurService realisateurService;

    /**
     * Constructeur du contrôleur avec injection du service.
     *
     * @param realisateurService Service gérant la logique métier des réalisateurs.
     */
    public RealisateurController(RealisateurService realisateurService) {
        this.realisateurService = realisateurService;
    }

    /**
     * Endpoint pour obtenir la liste de tous les réalisateurs avec pagination.
     *
     * @param pageable L'objet Pageable pour la pagination.
     * @return Une page de réalisateurs.
     */
    @GetMapping
    public Page<Realisateur> getAllRealisateurs(Pageable pageable) {
        return realisateurService.getAllRealisateurs(pageable);
    }

    /**
     * Endpoint pour obtenir un réalisateur par son identifiant.
     *
     * @param idRealisateur Identifiant du réalisateur à récupérer.
     * @return ResponseEntity contenant le réalisateur trouvé ou statut 404 si non trouvé.
     */
    @GetMapping("/{idRealisateur}")
    public ResponseEntity<Realisateur> getRealisateurById(@PathVariable("idRealisateur") Integer idRealisateur) {
        Optional<Realisateur> realisateur = realisateurService.getRealisateurById(idRealisateur);
        return realisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint pour sauvegarder un nouveau réalisateur.
     *
     * @param realisateur Le réalisateur à sauvegarder.
     * @return Le réalisateur sauvegardé.
     */
    @PostMapping
    public Realisateur createRealisateur(@RequestBody Realisateur realisateur) {
        return realisateurService.createRealisateur(realisateur);
    }

    /**
     * Endpoint pour mettre à jour un réalisateur existant.
     *
     * @param idRealisateur Identifiant du réalisateur à mettre à jour.
     * @param realisateur Nouvelles données du réalisateur.
     * @return ResponseEntity contenant le réalisateur mis à jour ou statut 404 si non trouvé.
     */
    @PutMapping("/{idRealisateur}")
    public ResponseEntity<Realisateur> updateRealisateur(@PathVariable("idRealisateur") Integer idRealisateur, @RequestBody Realisateur realisateur) {
        Realisateur updatedRealisateur = realisateurService.updateRealisateur(idRealisateur, realisateur);
        if (updatedRealisateur != null) {
            return ResponseEntity.ok(updatedRealisateur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint pour supprimer un réalisateur par son identifiant.
     *
     * @param idRealisateur Identifiant du réalisateur à supprimer.
     */
    @DeleteMapping("/{idRealisateur}")
    public void deleteRealisateurById(@PathVariable("idRealisateur") Integer idRealisateur) {
        realisateurService.deleteRealisateurById(idRealisateur);
    }

    /**
     * Endpoint pour obtenir tous les films d'un réalisateur donné.
     *
     * @param idRealisateur Identifiant du réalisateur.
     * @return Liste d'objets contenant les films du réalisateur.
     */
    @GetMapping("/{idRealisateur}/films")
    public List<Object[]> getFilmsByRealisateurId(@PathVariable("idRealisateur") Integer idRealisateur) {
        return realisateurService.findFilmsByRealisateurId(idRealisateur);
    }
}
