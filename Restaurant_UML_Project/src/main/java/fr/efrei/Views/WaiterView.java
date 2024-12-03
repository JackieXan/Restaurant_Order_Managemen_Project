package fr.efrei.Views;

import fr.efrei.Domain.Waiter;
import fr.efrei.Factory.WaiterFactory;
import fr.efrei.Repository.WaiterRepository;

import java.util.Scanner;

public class WaiterView {
    private static final WaiterRepository repository = WaiterRepository.getRepository();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Waiter Menu ===");
            System.out.println("1. Create Waiter");
            System.out.println("2. Show All Waiters");
            System.out.println("3. Update Waiter");
            System.out.println("4. Delete Waiter");
            System.out.println("5. Back to Main Menu");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createWaiter(scanner);
                    break;
                case "2":
                    showAllWaiters();
                    break;
                case "3":
                    updateWaiter(scanner);
                    break;
                case "4":
                    deleteWaiter(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createWaiter(Scanner scanner) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Waiter waiter = WaiterFactory.buildWaiter(firstName, lastName, username, password);
        if (waiter != null) {
            repository.create(waiter);
            System.out.println("Waiter created successfully!");
        } else {
            System.out.println("Failed to create waiter.");
        }
    }

    private static void showAllWaiters() {
        System.out.println("\n=== Waiter List ===");
        for (Waiter waiter : repository.getAll()) {
            System.out.println(waiter);
        }
    }

    private static void updateWaiter(Scanner scanner) {
        System.out.print("Enter Waiter ID to Update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Waiter existingWaiter = repository.read(id);

        if (existingWaiter != null) {
            System.out.print("Enter New First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter New Last Name: ");
            String lastName = scanner.nextLine();

            Waiter updatedWaiter = new Waiter.Builder(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();

            repository.update(updatedWaiter);
            System.out.println("Waiter updated successfully!");
        } else {
            System.out.println("Waiter not found.");
        }
    }

    private static void deleteWaiter(Scanner scanner) {
        System.out.print("Enter Waiter ID to Delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (repository.delete(id)) {
            System.out.println("Waiter deleted successfully!");
        } else {
            System.out.println("Failed to delete waiter.");
        }
    }
}
