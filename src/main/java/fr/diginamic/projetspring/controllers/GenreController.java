package fr.diginamic.projetspring.controllers;

import fr.diginamic.projetspring.entities.Genre;
import fr.diginamic.projetspring.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur REST pour la gestion des genres.
 */
@RestController
@RequestMapping("/genres") // ajouter /api/genres si besoin
public class GenreController {

    /** Service gérant la logique métier des genres. */
    @Autowired
    private GenreService genreService;

    /**
     * Constructeur du contrôleur avec injection du service.
     *
     * @param genreService Service gérant la logique métier des genres.
     */
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    /**
     * Récupère tous les genres.
     *
     * @return Liste de tous les genres.
     */
    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.findAll();
    }

    /**
     * Récupère un genre par son identifiant.
     *
     * @param genreId Identifiant du genre à récupérer.
     * @return Le genre trouvé.
     */
    @GetMapping("/{genreId}")
    public Genre getGenreById(@PathVariable Integer genreId) {
        return genreService.findById(genreId);
    }

    /**
     * Crée un nouveau genre.
     *
     * @param genre Genre à créer.
     * @return Le genre créé.
     */
    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    /**
     * Met à jour un genre existant.
     *
     * @param genreId Identifiant du genre à mettre à jour.
     * @param genre Nouvelles données du genre.
     * @return ResponseEntity avec le genre mis à jour ou statut 404 si non trouvé.
     */
    @PutMapping("/{genreId}")
    public ResponseEntity<Genre> updateGenre(@PathVariable("genreId") Integer genreId, @RequestBody Genre genre) {
        Genre updatedGenre = genreService.updateGenre(genreId, genre);
        if (updatedGenre != null) {
            return ResponseEntity.ok(updatedGenre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Supprime un genre existant.
     *
     * @param genreId Identifiant du genre à supprimer.
     */
    @DeleteMapping("/{genreId}")
    public void deleteGenre(@PathVariable("genreId") Integer genreId) {
        genreService.deleteGenre(genreId);
    }
}
