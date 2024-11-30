package fr.efrei.Views;

import fr.efrei.Domain.Table;
import fr.efrei.Factory.TableFactory;
import fr.efrei.Repository.TableRepository;

import java.util.Scanner;

public class TableView {
    private static final TableRepository repository = TableRepository.getRepository();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. Add Table");
            System.out.println("2. Show All Tables");
            System.out.println("3. Update Table");
            System.out.println("4. Delete Table");
            System.out.println("5. Back to Main Menu");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createTable();
                    break;
                case "2":
                    showAllTables();
                    break;
                case "3":
                    updateTable(scanner);
                    break;
                case "4":
                    deleteTable(scanner);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createTable() {
        Table table = TableFactory.buildTable();
        if (table != null) {
            repository.create(table);
            System.out.println("Table number " + table.getTableNumber() + " created successfully!");
        } else {
            System.out.println("Failed to create table.");
        }
    }

    private static void showAllTables() {
        System.out.println("\n=== Customer List ===");
        for (Table table : repository.getAll()) {
            System.out.println("Table n° " + table.getTableNumber());
        }
    }

    private static void updateTable(Scanner scanner) {
        System.out.print("Enter the number of the Table to Update: ");
        int tableNumber = Integer.parseInt(scanner.nextLine());
        Table existingTable = repository.read(tableNumber);

        if (existingTable != null) {
            System.out.print("Enter the new number for the Table: ");
            int newTableNumber = Integer.parseInt(scanner.nextLine());

            if (repository.read(newTableNumber) != null) {
                System.out.println("A table with this number already exists. Update cancelled.");
                return;
            }

            Table updatedTable = TableFactory.buildTable(newTableNumber);

            repository.delete(tableNumber);
            repository.create(updatedTable);

            System.out.println("Table updated successfully!");
        } else {
            System.out.println("Table not found.");
        }
    }


    private static void deleteTable(Scanner scanner) {
        System.out.print("Enter the number of the Table to Delete: ");
        int tableNumber = Integer.parseInt(scanner.nextLine());

        if (repository.delete(tableNumber)) {
            System.out.println("Table deleted successfully!");
        } else {
            System.out.println("Failed to delete the table.");
        }
    }
}


