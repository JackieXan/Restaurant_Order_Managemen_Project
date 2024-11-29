package fr.efrei.Views;

import fr.efrei.Domain.Manager;
import fr.efrei.Factory.ManagerFactory;
import fr.efrei.Repository.ManagerRepository;

import java.util.Scanner;

public class ManagerView {
    private static final ManagerRepository repository = ManagerRepository.getRepository();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Manager Menu ===");
            System.out.println("1. Create Manager");
            System.out.println("2. Show All Managers");
            System.out.println("3. Update Manager");
            System.out.println("4. Delete Manager");
            System.out.println("5. Back to Main Menu");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createManager(scanner);
                    break;
                case "2":
                    showAllManagers();
                    break;
                case "3":
                    updateManager(scanner);
                    break;
                case "4":
                    deleteManager(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createManager(Scanner scanner) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Manager manager = ManagerFactory.buildManager(firstName, lastName, username, password);
        if (manager != null) {
            repository.create(manager);
            System.out.println("Manager created successfully!");
        } else {
            System.out.println("Failed to create manager.");
        }
    }

    private static void showAllManagers() {
        System.out.println("\n=== Manager List ===");
        for (Manager manager : repository.getAll()) {
            System.out.println(manager);
        }
    }

    private static void updateManager(Scanner scanner) {
        System.out.print("Enter Manager ID to Update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Manager existingManager = repository.read(id);

        if (existingManager != null) {
            System.out.print("Enter New First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter New Last Name: ");
            String lastName = scanner.nextLine();

            Manager updatedManager = new Manager.Builder(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();

            repository.update(updatedManager);
            System.out.println("Manager updated successfully!");
        } else {
            System.out.println("Manager not found.");
        }
    }

    private static void deleteManager(Scanner scanner) {
        System.out.print("Enter Manager ID to Delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (repository.delete(id)) {
            System.out.println("Manager deleted successfully!");
        } else {
            System.out.println("Failed to delete manager.");
        }
    }
}
