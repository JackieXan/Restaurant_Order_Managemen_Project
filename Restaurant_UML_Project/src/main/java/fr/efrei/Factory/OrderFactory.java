package fr.efrei.Factory;

import fr.efrei.Domain.Customer;
import fr.efrei.Domain.MenuItem;
import fr.efrei.Domain.Order;
import fr.efrei.Util.Helper;

import java.util.ArrayList;

public class OrderFactory {

    /**
     * Creates an Order with the specified ID, Customer, and list of MenuItems.
     *
     * @param id      the unique ID of the order
     * @param customer the customer placing the order
     * @param items   the list of menu items in the order
     * @return the created Order instance, or null if inputs are invalid
     */
    public static Order buildOrder(int id, Customer customer, ArrayList<MenuItem> items) {
        if (customer == null || items == null || items.isEmpty()) {
            System.out.println("Invalid inputs: Customer or items cannot be null or empty.");
            return null; // An order must have a customer and at least one item
        }

        return new Order.Builder(id)
                .customer(customer)
                .items(items)
                .build();
    }

    /**
     * Creates an Order with an auto-generated ID, Customer, and list of MenuItems.
     *
     * @param customer the customer placing the order
     * @param items   the list of menu items in the order
     * @return the created Order instance, or null if inputs are invalid
     */
    public static Order buildOrder(Customer customer, ArrayList<MenuItem> items) {
        if (customer == null || items == null || items.isEmpty()) {
            System.out.println("Invalid inputs: Customer or items cannot be null or empty.");
            return null; // An order must have a customer and at least one item
        }

        int generatedId = Helper.generateId().hashCode(); // Generate a unique ID for the order

        return new Order.Builder(generatedId)
                .customer(customer)
                .items(items)
                .build();
    }
}
