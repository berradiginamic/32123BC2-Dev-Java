package fr.diginamic.projetspring.traitement;

import fr.diginamic.projetspring.entities.Realisateur;
import fr.diginamic.projetspring.services.RealisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * La classe {@code ImportRealisateurs} permet d'importer les données des réalisateurs depuis un fichier CSV.
 * <p>
 * Cette classe lit un fichier CSV contenant des informations sur des réalisateurs,
 * crée des objets {@link Realisateur} à partir de ces informations et les sauvegarde
 * dans une base de données via le service {@link RealisateurService}.
 * </p>
 * <p>
 * Les données du fichier CSV doivent être formatées avec les colonnes suivantes :
 * <ol>
 * <li>ID IMDB</li>
 * <li>Nom</li>
 * <li>Date de naissance (formatée en 'MMMM d yyyy')</li>
 * <li>Lieu de naissance</li>
 * <li>URL du profil</li>
 * </ol>
 * </p>
 * <p>
 * Les duplicatas d'ID IMDB sont gérés pour éviter les conflits d'intégrité de données.
 * </p>
 */
@Component
public class ImportRealisateurs {

    @Autowired
    private RealisateurService realisateurService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("MMMM d yyyy");

    /**
     * Importe les réalisateurs depuis un fichier CSV situé à {@code src/main/resources/dataset/realisateurs.csv}.
     * <p>
     * Cette méthode lit les données du fichier, élimine les duplicatas d'ID IMDB et sauvegarde
     * les réalisateurs uniques dans la base de données.
     * </p>
     */
    public void importRealisateurs() {
        Set<String> uniqueRealisateurIds = new HashSet<>();

        Path pathRealisateurs = Paths.get("src/main/resources/dataset/realisateurs.csv");
        try {
            List<String> rowsRealisateurs = Files.readAllLines(pathRealisateurs);
            rowsRealisateurs.remove(0); // Supprime l'en-tête du fichier CSV
            for (String rowRealisateur : rowsRealisateurs) {
                System.out.println(rowRealisateur);
                String[] elements = rowRealisateur.split(";");
                String idIMDB = elements[0].trim();
                // Vérifier si l'ID IMDB est unique
                if (!uniqueRealisateurIds.contains(idIMDB)) {
                    Realisateur realisateur = createRealisateurFromElements(elements);
                    try {
                        // Sauvegarder le réalisateur dans la base de données
                        realisateurService.createRealisateur(realisateur);
                        // Ajouter l'ID IMDB à l'ensemble des IDs uniques
                        uniqueRealisateurIds.add(idIMDB);
                    } catch (DataIntegrityViolationException e) {
                        System.out.println("Duplicate ID: " + idIMDB);
                    }
                } else {
                    System.out.println("Duplicate ID: " + idIMDB);
                }
            }
            System.out.println("Unique IDs Set: " + uniqueRealisateurIds);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un objet {@link Realisateur} à partir des éléments d'une ligne du fichier CSV.
     * <p>
     * Cette méthode extrait les données d'une ligne du fichier CSV, les formate et les assigne
     * aux propriétés de l'objet {@link Realisateur}.
     * </p>
     *
     * @param elements Les éléments de la ligne CSV, séparés par des points-virgules.
     * @return Un objet {@link Realisateur} avec les propriétés définies à partir des éléments de la ligne CSV.
     * @throws ParseException Si la date de naissance ne peut pas être parsée.
     */
    private Realisateur createRealisateurFromElements(String[] elements) throws ParseException {
        Realisateur realisateur = new Realisateur();
        realisateur.setIdIMDB(elements[0].trim());
        realisateur.setNom(elements[1]);
        try {
            // Parser la date de naissance à partir du format 'MMMM d yyyy'
            Date dateNaissance = sdf.parse(elements[2]);
            realisateur.setDateNaissance(dateNaissance);
        } catch (ParseException e) {
            e.printStackTrace();
            throw e; // Relancer l'exception après l'avoir enregistrée
        }
        realisateur.setLieuNaissance(elements[3]);
        realisateur.setUrlProfile(elements[4]);
        return realisateur;
    }
}
