package fr.efrei.Util;

import java.util.UUID;

public class Helper {

    // Vérifie si une chaîne est nulle ou vide
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }


    // Génère un identifiant unique sous forme de chaîne
    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    // Vérifie si un prix est valide (supérieur à 0)
    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    // Vérifie si un numéro de table est valide (entre 1 et 10)
    public static boolean isValidTableNumber(int tableNumber) {
        return tableNumber >= 1 && tableNumber <= 10;
    }

    // Vérifie si un créneau horaire est valide (noon ou evening)
    public static boolean isValidTimeSlot(String timeSlot) {
        return "noon".equalsIgnoreCase(timeSlot) || "evening".equalsIgnoreCase(timeSlot);
    }

    // Génère un nom par défaut (utile pour les tests ou valeurs par défaut)
    public static String generateDefaultName(String prefix) {
        return prefix + "_" + System.currentTimeMillis();
    }

    public static boolean isValidPhoneNumber(int phoneNum){
        return String.valueOf(Math.abs(phoneNum)).length() == 10;
    }

    public static boolean isNullOrEmpty(int i) {
        return i == 0;
    }
}

