package fr.efrei.Util;

import java.util.UUID;

public class Helper {

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static String generateId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isValidPrice(double price) {
        return price > 0;
    }

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

