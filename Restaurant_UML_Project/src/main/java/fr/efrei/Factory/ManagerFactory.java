package fr.efrei.Factory;

import fr.efrei.Domain.Manager;
import fr.efrei.Util.Helper;

public class ManagerFactory {

    public static Manager buildManager(int id, String firstName, String lastName, String username, String password) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            return null;
        }

        return new Manager.Builder(id)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .build();
    }

    public static Manager buildManager(String firstName, String lastName, String username, String password) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            return null;
        }

        int generatedId = Helper.generateId().hashCode();

        return new Manager.Builder(generatedId)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .build();
    }
}
