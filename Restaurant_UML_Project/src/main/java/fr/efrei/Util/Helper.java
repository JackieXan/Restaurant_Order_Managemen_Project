package fr.efrei.Util;

import java.util.UUID;

public class Helper {

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }


    public static boolean isValidPrice(double price) {
        return price > 0;
    }

    public static boolean isNullOrEmpty(int i) {
        return i == 0;
    }

}

