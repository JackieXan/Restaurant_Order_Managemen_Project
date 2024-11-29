package fr.efrei.Factory;

import fr.efrei.Domain.Customer;
import fr.efrei.Domain.MenuItem;
import fr.efrei.Domain.Order;
import fr.efrei.Util.Helper;

import java.util.ArrayList;

public class OrderFactory {
    private static int currentId = 1;

    public static Order buildOrder(Customer customer, ArrayList<MenuItem> items) {
        if (customer == null || items == null || items.isEmpty()) {
            System.out.println("Invalid inputs: Customer or items cannot be null or empty.");
            return null;
        }

        int generatedId = getNextId();

        return new Order.Builder(generatedId)
                .customer(customer)
                .items(items)
                .build();
    }

    public static int getNextId(){
        return currentId++;
    }
}
