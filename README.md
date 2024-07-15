# Projet Spring Boot - Application de Gestion de Données de Cinéma

## Objectifs
 
Les 3 objectifs de ce projet sont les suivants : 
1. Réaliser un document de conception avec diagramme de classes et Modèle Physique de Données.
2. Mettre en base de données des informations concernant le cinéma.
3. Mettre au point une API REST permettant d’effectuer des recherches dans les données.
 
## Description
 
Les données sont réparties dans 5 fichiers CSV :  
- `films.csv` : liste des films 
- `acteurs.csv` : liste des acteurs 
- `realisateurs.csv` : liste des réalisateurs 
- `roles.csv` : listes des rôles par films 
- `film_realisateurs.csv` : réalisateurs par films   
 
## Tâches
 
### Tâche 1 : Analyse des Fichiers CSV
Analyser les fichiers afin d’en comprendre la structure. Cette analyse est nécessaire pour réaliser la conception des données.
 
### Tâche 2 : Conception
Réaliser un document de conception contenant :
- Un diagramme de classes ou le Modèle Conceptuel de Données (MCD)
- Le modèle physique de données
 
Ce document sera commité dans un répertoire appelé `conception` à la racine du projet.
 
### Tâche 3 : Création du Projet Spring Boot
Créer un projet Spring Boot appelé `projet-spring` avec Spring Initializr en incluant les modules Spring Web et Spring Data JPA.
 
Initialiser un dépôt Git pour ce projet, réaliser un premier commit et pousser le projet sur GitHub.
 
### Tâche 4 : Création des Applications Spring Boot
Le projet contiendra deux applications Spring Boot :
1. `ProjetSpringApplication` : Pour lancer l’API REST.
2. `TraitementFichierApplication` : Pour mettre en base le contenu des fichiers CSV.
 
### Tâche 5 : Mise en Base de Données
Utiliser `TraitementFichierApplication` pour réaliser une application de mise en base de données. Partager les entités, services et DAO (ou Repositories) entre les deux applications.
 
### Tâche 6 : Réalisation de l'API REST
Créer une API REST avec Spring Boot proposant au moins les services suivants :
1. Contrôleurs avec opérations CRUD pour les entités suivantes :
   - Films
   - Acteurs
   - Réalisateurs
   - Genres
   - Rôles
2. Services spécifiques :
   - Extraire tous les films (nom et années de sortie) d’un acteur donné
   - Extraire tous les rôles d’un film donné
   - Extraire les films sortis entre deux années données
   - Extraire les films communs à deux acteurs ou actrices donnés
   - Extraire tous les films d’un genre donné
   - Extraire les acteurs communs à deux films donnés
   - Extraire tous les films d’un réalisateur donné
   - Extraire les films sortis entre deux années données et ayant un acteur/actrice donné parmi les acteurs
   - Extraire les acteurs associés au genre dans lequel ils ont le plus joué
 
Les interactions avec l’utilisateur se font avec POSTMAN pour l’instant (le front sera développé dans un projet ultérieur).
 
## Exigences
 
### Exigence 1 : Qualité de Code
- Documenter le code avec Javadoc.
 
### Exigence 2 : Pas de Duplication des Données en Base
- Pas de doublons en base de données : le traitement ne doit pas recréer des données existantes.
- Les dates de naissance doivent être de type `java.util.Date` dans les classes et `DATE` en base de données.
- Les genres doivent être des entités à part entière.
 
### Exigence 3 : Réutilisation du Code
- Partager un maximum de code entre les deux applications Spring Boot.
 
## Prérequis
 
- Java 8 ou plus récent
- Maven
- Spring Boot
- Base de données (H2, MySQL, PostgreSQL, etc.)
 
## Installation
 
1. Cloner le dépôt Git :
    ```bash
    git clone https://github.com/votre-utilisateur/projet-spring.git
    ```
2. Naviguer dans le répertoire du projet :
    ```bash
    cd projet-spring
    ```
3. Lancer l’application de traitement de fichiers pour importer les données en base :
    ```bash
    mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=import"
    ```
4. Lancer l’API REST :
    ```bash
    mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=api"
    ```
 
## Utilisation
 
Utiliser POSTMAN pour tester les différents services de l’API REST en envoyant des requêtes HTTP aux endpoints définis dans les contrôleurs. Voici quelques exemples de requêtes :
 
### Exemple de requêtes POSTMAN
 
#### Récupérer tous les films
```
GET /films
```
 
#### Ajouter un nouvel acteur
```
POST /acteurs
Body (JSON):
{
  "nom": "Nom de l'acteur",
  "prenom": "Prénom de l'acteur",
  "dateNaissance": "yyyy-mm-dd"
}
```
 
#### Récupérer les films d'un acteur donné
```
GET /acteurs/{id}/films
```
 
#### Récupérer les rôles d'un film donné
```
GET /films/{id}/roles
```

### Collaborateurs
- Berrabah Fatima
- Alfred Christopher
- Cormerais Dorian
- Mougani Christ
