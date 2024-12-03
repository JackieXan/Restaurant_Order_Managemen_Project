package fr.efrei;

import fr.efrei.Domain.*;
import fr.efrei.Factory.*;
import fr.efrei.Repository.*;
import fr.efrei.Views.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class MainMenu {

    private static final TableRepository tableRepository = TableRepository.getRepository();
    private static final MenuItemRepository menuItemRepo = MenuItemRepository.getRepository();
    private static final WaiterRepository waiterRepo = WaiterRepository.getRepository();
    private static final ManagerRepository managerRepo = ManagerRepository.getRepository();
    private static final OrderRepository orderRepo = OrderRepository.getRepository();  // Repository pour Order


    private static Waiter currentWaiter;
    private static Manager currentManager;
    private static String currentRole;

    public static void main(String[] args) {

        System.out.println("\n" +
                "  _____             _                                    _      ____            _               __  __                                                            _     _____              _              _   \n" +
                " |  __ \\           | |                                  | |    / __ \\          | |             |  \\/  |                                                          | |   |  __ \\            (_)            | |  \n" +
                " | |__) | ___  ___ | |_  __ _  _   _  _ __  __ _  _ __  | |_  | |  | | _ __  __| |  ___  _ __  | \\  / |  __ _  _ __    __ _   __ _   ___  _ __ ___    ___  _ __  | |_  | |__) |_ __  ___   _   ___   ___ | |_ \n" +
                " |  _  / / _ \\/ __|| __|/ _` || | | || '__|/ _` || '_ \\ | __| | |  | || '__|/ _` | / _ \\| '__| | |\\/| | / _` || '_ \\  / _` | / _` | / _ \\| '_ ` _ \\  / _ \\| '_ \\ | __| |  ___/| '__|/ _ \\ | | / _ \\ / __|| __|\n" +
                " | | \\ \\|  __/\\__ \\| |_| (_| || |_| || |  | (_| || | | || |_  | |__| || |  | (_| ||  __/| |    | |  | || (_| || | | || (_| || (_| ||  __/| | | | | ||  __/| | | || |_  | |    | |  | (_) || ||  __/| (__ | |_ \n" +
                " |_|  \\_\\\\___||___/ \\__|\\__,_| \\__,_||_|   \\__,_||_| |_| \\__|  \\____/ |_|   \\__,_| \\___||_|    |_|  |_| \\__,_||_| |_| \\__,_| \\__, | \\___||_| |_| |_| \\___||_| |_| \\__| |_|    |_|   \\___/ | | \\___| \\___| \\__|\n" +
                "                                                                                                                              __/ |                                                      _/ |                 \n" +
                "                                                                                                                             |___/                                                      |__/                  \n");
        initializeData();

        Object user = login();
        while (user == null) {
            System.out.println("Please Reconnect");
            user = login();
        }

        if (currentRole.equals("Waiter")) {
            currentWaiter = (Waiter) user;
        } else if (currentRole.equals("Manager")) {
            currentManager = (Manager) user;
        }

        if (currentManager != null) {
            showManagerMenu();
        } else if (currentWaiter != null) {
            showWaiterMenu();
        }
    }

    private static Object login() {

        System.out.println("Login: ");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (Waiter waiter : waiterRepo.getAll()) {
            if (waiter.getUsername().equals(username) && waiter.getPassword().equals(password)) {
                System.out.println("Login successful. Welcome, Waiter " + waiter.getFirstName() + " " + waiter.getLastName());
                currentRole = "Waiter";
                return waiter;
            }
        }

        for (Manager manager : managerRepo.getAll()) {
            if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
                System.out.println("Login successful. Welcome, Manager " + manager.getFirstName() + " " + manager.getLastName());
                currentRole = "Manager";
                return manager;
            }
        }

        System.out.println("Invalid username or password.");
        return null;
    }

    private static void showManagerMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Table Menu");
            System.out.println("2. Manager Menu");
            System.out.println("3. Menu Item Menu");
            System.out.println("4. Order Menu");
            System.out.println("5. Payment Menu");
            System.out.println("6. Waiter Menu");
            System.out.println("7. Disconnect");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    TableView.menu();
                    break;
                case "2":
                    ManagerView.menu();
                    break;
                case "3":
                    MenuItemView.menu();
                    break;
                case "4":
                    OrderView.menu();
                    break;
                case "5":
                    PaymentView.menu();
                    break;
                case "6":
                    WaiterView.menu();
                    break;
                case "7":
                    disconnect();
                    return;
                case "8":
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showWaiterMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Order Menu");
            System.out.println("2. Payment Menu");
            System.out.println("3. Disconnect");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    OrderView.menu();
                    break;
                case "2":
                    PaymentView.menu();
                    break;
                case "3":
                    disconnect();
                    return;
                case "4":
                    System.out.println("Exiting the program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void disconnect() {
        currentWaiter = null;
        currentManager = null;
        System.out.println("You have been disconnected.");
        main(null);
    }

    private static void initializeData() {
        initializeMenuItems();
        initializeTables();
        initializeWaiters();
        initializeManagers();
        initializeOrders();
    }

    private static void initializeMenuItems() {
        String[] menuNames = {"Pizza Margherita", "Lasagna", "Spaghetti Bolognese", "Risotto", "Fettuccine Alfredo",
                "Bruschetta", "Tiramisu", "Cannoli", "Caprese Salad", "Minestrone",
                "Pasta Primavera", "Gnocchi", "Ravioli", "Polenta", "Osso Buco",
                "Chicken Parmesan", "Eggplant Parmesan", "Veal Piccata", "Lamb Ragu", "Mushroom Risotto"};

        double[] prices = {12.5, 15.0, 13.0, 14.5, 16.0, 8.0, 6.0, 7.5, 9.0, 11.0,
                14.0, 13.5, 17.0, 18.0, 20.0, 16.5, 19.0, 21.0, 22.0, 18.5, 17.5};

        for (int i = 0; i < 20; i++) {
            MenuItem menuItem = MenuItemFactory.buildMenuItem(menuNames[i], prices[i], "Traditional Italian dish");
            menuItemRepo.create(menuItem);
        }
    }
    private static void initializeOrders() {
        Table table1 = tableRepository.getAll().get(0);
        Table table2 = tableRepository.getAll().get(1);
        Table table3 = tableRepository.getAll().get(2);

        MenuItem item1 = menuItemRepo.getAll().get(0);
        MenuItem item2 = menuItemRepo.getAll().get(1);
        MenuItem item3 = menuItemRepo.getAll().get(2);

        Order order1 = OrderFactory.buildOrder(table1, new ArrayList<>(Arrays.asList(item1, item2)));
        orderRepo.create(order1);

        Order order2 = OrderFactory.buildOrder(table2, new ArrayList<>(Arrays.asList(item2, item3)));
        orderRepo.create(order2);

        Order order3 = OrderFactory.buildOrder(table3, new ArrayList<>(Arrays.asList(item1, item3)));
        orderRepo.create(order3);
    }

    private static void initializeTables() {
        for (int i = 0; i < 10; i++) {
            Table table = TableFactory.buildTable();
            tableRepository.create(table);
        }
    }

    private static void initializeWaiters() {
        String[] firstNames = {"Marco", "Giulia", "Alessandro", "Francesca", "Luca"};
        String[] lastNames = {"Rossi", "Bianchi", "Verdi", "Moretti", "Giordano"};
        String[] usernames = {"waiter1", "waiter2", "waiter3", "waiter4", "waiter5"};
        String[] passwords = {"password1", "password2", "password3", "password4", "password5"};

        for (int i = 0; i < 5; i++) {
            Waiter waiter = WaiterFactory.buildWaiter(firstNames[i], lastNames[i], usernames[i], passwords[i]);
            waiterRepo.create(waiter);
        }
    }

    private static void initializeManagers() {
        String[] firstNames = {"Giovanni", "Maria"};
        String[] lastNames = {"Bianchi", "Neri"};
        String[] usernames = {"manager1", "manager2"};
        String[] passwords = {"password1", "password2"};

        for (int i = 0; i < 2; i++) {
            Manager manager = ManagerFactory.buildManager(firstNames[i], lastNames[i], usernames[i], passwords[i]);
            managerRepo.create(manager);
        }
    }
}
