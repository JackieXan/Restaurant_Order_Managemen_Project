package fr.efrei.Factory;

import fr.efrei.Domain.MenuItem;
import fr.efrei.Util.Helper;

public class MenuItemFactory {

    public static MenuItem buildMenuItem(int id, String name, double price) {
        if (Helper.isNullOrEmpty(name) || !Helper.isValidPrice(price)) {
            return null; // Vérifie si le nom est vide ou si le prix est invalide
        }

        // Construction de l'objet MenuItem via le Builder
        return new MenuItem.Builder(id)
                .name(name)
                .price(price)
                .build();
    }

    public static MenuItem buildMenuItem(String name, double price) {
        if (Helper.isNullOrEmpty(name) || !Helper.isValidPrice(price)) {
            return null;
        }

        int generatedId = Helper.generateId().hashCode();

        return new MenuItem.Builder(generatedId)
                .name(name)
                .price(price)
                .build();
    }
}