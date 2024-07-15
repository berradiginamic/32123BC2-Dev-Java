1. **Tâche 1:** Extraire tous les films (nom et années de sortie) d’un acteur donné
   - URL: `http://localhost:8080/acteurs/{acteurId}/films`

2. **Tâche 2:** Extraire tous les rôles d’un film donné
   - URL: `http://localhost:8080/films/{filmId}/actors-and-characters`
   - Remarque: `{filmId}` est l'ID du film.

3. **Tâche 3:** Extraire les films sortis entre deux années données
   - URL: `http://localhost:8080/films/released-between-years?startYear=<START>&endYear=<END>`
   - Remplacez `<START>` et `<END>` par les années souhaitées.

4. **Tâche 4:** Extraire les films communs à deux acteurs ou actrices donnés
   - URL: `http://localhost:8080/films/by-two-actors?acteurId1=<IdValeur1>&acteurId2=<IdValeur2>`
   - Remplacez `<IdValeur1>` et `<IdValeur2>` par les ID à comparer.

5. **Tâche 5:** Extraire tous les films d’un genre donné
   - URL: `http://localhost:8080/films/by-genre?genreId=<YOUR_GENRE_ID>`
   - Remplacez `<YOUR_GENRE_ID>` par l'ID du genre.

6. **Tâche 6:** Extraire les acteurs communs à deux films donnés
   - URL: `http://localhost:8080/acteurs/in-films?filmId1=<YOUR_FILM_ID_1>&filmId2=<YOUR_FILM_ID_2>`
   - Remplacez `<YOUR_FILM_ID_1>` et `<YOUR_FILM_ID_2>` par les ID des films.

7. **Tâche 7:** Extraire tous les films d’un réalisateur donné
   - URL: `http://localhost:8080/realisateurs/{idRealisateurs}/films`
   - Remarque: `{idRealisateurs}` est l'ID du réalisateur.

8. **Tâche 8:** Extraire les films sortis entre deux années données qui ont un acteur/actrice donné parmi les acteurs
   - URL: `http://localhost:8080/films/betweenYearsAndByActeur?startYear=2000&endYear=2020&acteurId=123`

9. **Tâche 9:**
