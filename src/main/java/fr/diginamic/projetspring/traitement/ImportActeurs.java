package fr.diginamic.projetspring.traitement;

import fr.diginamic.projetspring.entities.Acteur;
import fr.diginamic.projetspring.services.ActeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Composant responsable de l'importation des acteurs à partir d'un fichier CSV dans la base de données de l'application.
 */
@Component
public class ImportActeurs {

    @Autowired
    private ActeurService acteurService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("MMMM d yyyy");

    /**
     * Importe les acteurs à partir d'un fichier CSV dans la base de données.
     * Gère les doublons d'identifiants et analyse la date de naissance à partir du fichier CSV.
     */
    public void importActeurs() {
        Set<String> uniqueActeurIds = new HashSet<>();

        Path pathActeurs = Paths.get("src/main/resources/dataset/acteurs.csv");
        try {
            List<String> rowsActeurs = Files.readAllLines(pathActeurs);
            rowsActeurs.remove(0); // Supprime la ligne d'en-tête
            for (String rowActeur : rowsActeurs) {
                String[] elements = rowActeur.split(";");
                String idIMDB = elements[0].trim();
                if (!uniqueActeurIds.contains(idIMDB)) {
                    Acteur acteur = createActeurFromElements(elements);
                    try {
                        acteurService.createActeur(acteur);
                        uniqueActeurIds.add(idIMDB);
                    } catch (DataIntegrityViolationException e) {
                        System.out.println("ID en double : " + idIMDB);
                    }
                } else {
                    System.out.println("ID en double : " + idIMDB);
                }
            }
            System.out.println("Ensemble d'IDs uniques : " + uniqueActeurIds);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Crée un objet Acteur à partir des éléments d'un tableau de chaînes extraits du fichier CSV.
     *
     * @param elements Tableau d'éléments de chaîne contenant les informations sur l'acteur.
     * @return Un objet Acteur rempli avec les données du fichier CSV.
     * @throws ParseException Si une erreur se produit lors de l'analyse de la date de naissance à partir du CSV.
     */
    private Acteur createActeurFromElements(String[] elements) throws ParseException {
        Acteur acteur = new Acteur();
        acteur.setIdIMDB(elements[0].trim());
        acteur.setNom(elements[1]);
        try {
            Date dateNaissance = sdf.parse(elements[2]);
            acteur.setDateNaissance(dateNaissance);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        acteur.setLieuNaissance(elements[3]);
        acteur.setUrlProfile(elements[5]);
        return acteur;
    }
}
