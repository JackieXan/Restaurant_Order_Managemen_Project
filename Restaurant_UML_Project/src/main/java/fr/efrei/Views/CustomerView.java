package fr.efrei.Views;

import fr.efrei.Domain.Customer;
import fr.efrei.Factory.CustomerFactory;
import fr.efrei.Repository.CustomerRepository;

import java.util.Scanner;

public class CustomerView {
    private static final CustomerRepository repository = CustomerRepository.getRepository();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. Create Customer");
            System.out.println("2. Show All Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back to Main Menu");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createCustomer(scanner);
                    break;
                case "2":
                    showAllCustomers();
                    break;
                case "3":
                    updateCustomer(scanner);
                    break;
                case "4":
                    deleteCustomer(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createCustomer(Scanner scanner) {
        System.out.print("Enter Phone Number: ");
        int phone = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        Customer customer = CustomerFactory.buildCustomer(phone, firstName, lastName);
        if (customer != null) {
            repository.create(customer);
            System.out.println("Customer created successfully!");
        } else {
            System.out.println("Failed to create customer.");
        }
    }

    private static void showAllCustomers() {
        System.out.println("\n=== Customer List ===");
        for (Customer customer : repository.getAll()) {
            System.out.println(customer);
        }
    }

    private static void updateCustomer(Scanner scanner) {
        System.out.print("Enter Phone Number of Customer to Update: ");
        int phone = Integer.parseInt(scanner.nextLine());
        Customer existingCustomer = repository.read(phone);

        if (existingCustomer != null) {
            System.out.print("Enter New First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter New Last Name: ");
            String lastName = scanner.nextLine();

            Customer updatedCustomer = new Customer.Builder(phone)
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();

            repository.update(updatedCustomer);
            System.out.println("Customer updated successfully!");
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void deleteCustomer(Scanner scanner) {
        System.out.print("Enter Phone Number of Customer to Delete: ");
        int phone = Integer.parseInt(scanner.nextLine());

        if (repository.delete(phone)) {
            System.out.println("Customer deleted successfully!");
        } else {
            System.out.println("Failed to delete customer.");
        }
    }
}
