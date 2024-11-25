package fr.efrei.Factory;

import fr.efrei.Domain.Waiter;
import fr.efrei.Util.Helper;

public class WaiterFactory {

    public static Waiter buildManager(int id, String firstName, String lastName) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            return null;
        }

        return new Waiter.Builder(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static Waiter buildManager(String firstName, String lastName) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            return null;
        }

        int generatedId = Helper.generateId().hashCode();

        return new Waiter.Builder(generatedId)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
