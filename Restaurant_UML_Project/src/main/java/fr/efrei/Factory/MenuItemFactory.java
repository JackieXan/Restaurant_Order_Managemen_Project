package fr.efrei.Factory;

import fr.efrei.Domain.MenuItem;
import fr.efrei.Util.Helper;

public class MenuItemFactory {
    private static int currentId = 1;

    public static MenuItem buildMenuItem(String name, double price, String description) {
        if (Helper.isNullOrEmpty(name) || !Helper.isValidPrice(price)) {
            return null;
        }

        int generatedId = getNextId();

        return new MenuItem.Builder(generatedId)
                .name(name)
                .price(price)
                .description(description)
                .build();
    }
    public static int getNextId(){
        return currentId++;
    }
}