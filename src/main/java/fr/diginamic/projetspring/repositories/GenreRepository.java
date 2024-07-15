package fr.diginamic.projetspring.repositories;

import fr.diginamic.projetspring.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface repository pour l'entité Genre, utilisant Spring Data JPA.
 */
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    // Aucune méthode spécifique déclarée ici, car JpaRepository offre déjà des méthodes génériques pour les opérations CRUD.

    // Ajoutez d'autres méthodes déclaratives en fonction des besoins

    /**
     * Recherche tous les genres par type.
     *
     * @param type Le type des genres à rechercher.
     * @return La liste des genres correspondant au type spécifié.
     */
    List<Genre> findAllByType(String type);

    /**
     * Recherche un genre par son type.
     *
     * @param type Le type du genre à rechercher.
     * @return Le genre correspondant au type spécifié, s'il existe.
     */
    Optional<Genre> findByType(String type);
}
