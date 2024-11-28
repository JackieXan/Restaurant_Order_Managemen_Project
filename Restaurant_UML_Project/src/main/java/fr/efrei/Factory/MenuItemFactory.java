package fr.efrei.Factory;

import fr.efrei.Domain.MenuItem;
import fr.efrei.Util.Helper;

public class MenuItemFactory {
    public static MenuItem buildMenuItem(int id, String name, double price, String description) {
        if (Helper.isNullOrEmpty(name) || !Helper.isValidPrice(price)) {
            return null;
        }


        return new MenuItem.Builder(id)
                .name(name)
                .price(price)
                .description(description)
                .build();
    }
    public static MenuItem buildMenuItem(String name, double price, String description) {
        if (Helper.isNullOrEmpty(name) || !Helper.isValidPrice(price)) {
            return null;
        }

        int generatedId = Helper.generateId().hashCode();

        return new MenuItem.Builder(generatedId)
                .name(name)
                .price(price)
                .description(description)
                .build();
    }
}