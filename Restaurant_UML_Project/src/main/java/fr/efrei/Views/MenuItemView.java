package fr.efrei.Views;

import fr.efrei.Domain.MenuItem;
import fr.efrei.Factory.MenuItemFactory;
import fr.efrei.Repository.MenuItemRepository;

import java.util.Scanner;

public class MenuItemView {
    private static final MenuItemRepository repository = MenuItemRepository.getRepository();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Item Menu ===");
            System.out.println("1. Create Menu Item");
            System.out.println("2. Show All Menu Items");
            System.out.println("3. Update Menu Item");
            System.out.println("4. Delete Menu Item");
            System.out.println("5. Back to Main Menu");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createMenuItem(scanner);
                    break;
                case "2":
                    showAllMenuItems();
                    break;
                case "3":
                    updateMenuItem(scanner);
                    break;
                case "4":
                    deleteMenuItem(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createMenuItem(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        MenuItem menuItem = MenuItemFactory.buildMenuItem(name, price, description);
        if (menuItem != null) {
            repository.create(menuItem);
            System.out.println("Menu item created successfully!");
        } else {
            System.out.println("Failed to create menu item.");
        }
    }

    private static void showAllMenuItems() {
        System.out.println("\n=== Menu Item List ===");
        for (MenuItem menuItem : repository.getAll()) {
            System.out.println(menuItem);
        }
    }

    private static void updateMenuItem(Scanner scanner) {
        System.out.print("Enter Menu Item ID to Update: ");
        int id = Integer.parseInt(scanner.nextLine());
        MenuItem existingItem = repository.read(id);

        if (existingItem != null) {
            System.out.print("Enter New Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter New Price: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter New Description: ");
            String description = scanner.nextLine();

            MenuItem updatedItem = new MenuItem.Builder(id)
                    .name(name)
                    .price(price)
                    .description(description)
                    .build();

            repository.update(updatedItem);
            System.out.println("Menu item updated successfully!");
        } else {
            System.out.println("Menu item not found.");
        }
    }

    private static void deleteMenuItem(Scanner scanner) {
        System.out.print("Enter Menu Item ID to Delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (repository.delete(id)) {
            System.out.println("Menu item deleted successfully!");
        } else {
            System.out.println("Failed to delete menu item.");
        }
    }
}
