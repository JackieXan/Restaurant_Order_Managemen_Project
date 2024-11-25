package fr.efrei.Factory;

import fr.efrei.Domain.Waiter;
import fr.efrei.Util.Helper;

public class WaiterFactory {

    public static Waiter buildManager(int id, String firstName, String lastName) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            return null; // Vérifie si les noms sont vides
        }

        // Construction de l'objet Manager via le Builder
        return new Waiter.Builder(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static Waiter buildManager(String firstName, String lastName) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            return null; // Vérifie si les noms sont vides
        }

        int generatedId = Helper.generateId().hashCode(); // Génération automatique d'un ID

        return new Waiter.Builder(generatedId)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
