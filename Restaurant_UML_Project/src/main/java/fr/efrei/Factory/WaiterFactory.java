package fr.efrei.Factory;

import fr.efrei.Domain.Waiter;
import fr.efrei.Util.Helper;

public class WaiterFactory {

    private static int currentId = 1 ;


    public static Waiter buildWaiter(String firstName, String lastName, String username, String password) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName)) {
            return null;
        }

        int generatedId = getNextId();

        return new Waiter.Builder(generatedId)
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
