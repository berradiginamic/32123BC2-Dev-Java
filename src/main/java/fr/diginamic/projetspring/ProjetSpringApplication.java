package fr.diginamic.projetspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * La classe {@code ProjetSpringApplication} est le point d'entrée de l'application Spring Boot.
 * <p>
 * Cette classe démarre l'application Spring Boot en exécutant la méthode {@link SpringApplication#run(Class, String...)}.
 * </p>
 */
@SpringBootApplication
public class ProjetSpringApplication {

    /**
     * Le point d'entrée principal de l'application.
     * <p>
     * Cette méthode démarre l'application Spring Boot.
     * </p>
     *
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SpringApplication.run(ProjetSpringApplication.class, args);
    }
}
