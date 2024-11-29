package fr.efrei.Factory;

import fr.efrei.Domain.Manager;
import fr.efrei.Util.Helper;

public class ManagerFactory {

    public static int currentId = 1;
    public static Manager buildManager(String firstName, String lastName, String username, String password) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            return null;
        }

        int generatedId = getNextId();

        return new Manager.Builder(generatedId)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .password(password)
                .build();
    }
    public static int getNextId(){
        return currentId++;
    }
}
