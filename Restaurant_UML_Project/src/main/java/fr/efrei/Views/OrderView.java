package fr.efrei.Views;

import fr.efrei.Domain.Table;
import fr.efrei.Domain.MenuItem;
import fr.efrei.Domain.Order;
import fr.efrei.Factory.OrderFactory;
import fr.efrei.Repository.MenuItemRepository;
import fr.efrei.Repository.OrderRepository;
import fr.efrei.Repository.TableRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private static final OrderRepository orderRepo = OrderRepository.getRepository();
    private static final TableRepository tableRepo = TableRepository.getRepository();
    private static final MenuItemRepository menuItemRepo = MenuItemRepository.getRepository();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Order Menu ===");
            System.out.println("1. Create Order");
            System.out.println("2. Show All Orders");
            System.out.println("3. Update Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Add Items to Existing Order");
            System.out.println("6. Remove Items from Existing Order");
            System.out.println("7. Back to Main Menu");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createOrder(scanner);
                    break;
                case "2":
                    showAllOrders();
                    break;
                case "3":
                    updateOrder(scanner);
                    break;
                case "4":
                    deleteOrder(scanner);
                    break;
                case "5":
                    addItemOrder(scanner);
                    break;
                case "6":
                    removeItemOrder(scanner);
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createOrder(Scanner scanner) {
        try {
            System.out.println("\n=== Tables Available ===");
            List<Table> tables = tableRepo.getAll();
            List<Order> orders = orderRepo.getAll();

            List<Table> availableTables = tables.stream()
                    .filter(table -> orders.stream()
                            .noneMatch(order -> order.getTable().getTableNumber() == table.getTableNumber()))
                    .toList();

            if (availableTables.isEmpty()) {
                System.out.println("No tables available. All tables are occupied.");
                return;
            }

            availableTables.forEach(table ->
                    System.out.println("Table n°" + table.getTableNumber())
            );

            System.out.print("Enter a Table Number: ");
            int tableNumber = Integer.parseInt(scanner.nextLine());
            Table table = availableTables.stream()
                    .filter(t -> t.getTableNumber() == tableNumber)
                    .findFirst()
                    .orElse(null);

            if (table == null) {
                System.out.println("Invalid table or table is not available. Operation cancelled.");
                return;
            }

            List<MenuItem> items = new ArrayList<>();
            System.out.println("\n=== Select Menu Items (Enter IDs, separated by commas) ===");
            List<MenuItem> menuItems = menuItemRepo.getAll();
            if (menuItems.isEmpty()) {
                System.out.println("No menu items available. Please add menu items first.");
                return;
            }
            menuItems.forEach(menuItem ->
                    System.out.println(menuItem.getId() + " - " + menuItem.getName())
            );
            System.out.print("Enter Menu Item IDs: ");
            String[] itemIds = scanner.nextLine().split(",");

            for (String itemIdStr : itemIds) {
                try {
                    int itemId = Integer.parseInt(itemIdStr.trim());
                    MenuItem menuItem = menuItemRepo.read(itemId);
                    if (menuItem != null) {
                        items.add(menuItem);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid menu item ID: " + itemIdStr.trim());
                }
            }

            if (items.isEmpty()) {
                System.out.println("No valid items selected. Operation cancelled.");
                return;
            }

            Order order = OrderFactory.buildOrder(table, new ArrayList<>(items));
            if (order != null) {
                orderRepo.create(order);
                System.out.println("Order created successfully!");
            } else {
                System.out.println("Failed to create order.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
        }
    }

    private static void showAllOrders() {
        System.out.println("\n=== Order List ===");
        List<Order> orders = orderRepo.getAll();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            orders.forEach(order -> {
                System.out.println("Order ID: " + order.getId());
                System.out.println("Table n°: " + order.getTable().getTableNumber());
                System.out.println("Items:");
                order.getItems().forEach(item -> System.out.println(" - " + item.getName() + " ($" + item.getPrice() + ")"));
                System.out.println("Total Amount: $" + order.getAmount());
                System.out.println("----------------------");
            });
        }
    }

    private static void updateOrder(Scanner scanner) {
        try {
            System.out.print("Enter Order ID to Update: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            Order existingOrder = orderRepo.read(orderId);

            if (existingOrder == null) {
                System.out.println("Order not found.");
                return;
            }

            List<MenuItem> newItems = new ArrayList<>();
            System.out.println("\n=== Select New Menu Items (Enter IDs, separated by commas) ===");
            List<MenuItem> menuItems = menuItemRepo.getAll();
            if (menuItems.isEmpty()) {
                System.out.println("No menu items available. Please add menu items first.");
                return;
            }
            menuItems.forEach(menuItem ->
                    System.out.println(menuItem.getId() + " - " + menuItem.getName())
            );
            System.out.print("Enter Menu Item IDs: ");
            String[] itemIds = scanner.nextLine().split(",");

            for (String itemIdStr : itemIds) {
                try {
                    int itemId = Integer.parseInt(itemIdStr.trim());
                    MenuItem menuItem = menuItemRepo.read(itemId);
                    if (menuItem != null) {
                        newItems.add(menuItem);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid menu item ID: " + itemIdStr.trim());
                }
            }

            if (newItems.isEmpty()) {
                System.out.println("No valid items selected. Operation cancelled.");
                return;
            }

            Order updatedOrder = new Order.Builder(orderId)
                    .table(existingOrder.getTable())
                    .items(newItems)
                    .build();

            orderRepo.update(updatedOrder);
            System.out.println("Order updated successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
        }
    }

    private static void deleteOrder(Scanner scanner) {
        try {
            System.out.print("Enter Order ID to Delete: ");
            int orderId = Integer.parseInt(scanner.nextLine());

            if (orderRepo.delete(orderId)) {
                System.out.println("Order deleted successfully!");
            } else {
                System.out.println("Failed to delete order. Order not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
        }
    }

    private static void addItemOrder(Scanner scanner) {
        try {
            System.out.println("\n=== Add Items to Existing Order ===");

            List<Order> orders = orderRepo.getAll();
            if (orders.isEmpty()) {
                System.out.println("No orders available. Please create an order first.");
                return;
            }

            orders.forEach(order -> {
                System.out.println("Order ID: " + order.getId() + ", Table n°: " + order.getTable().getTableNumber());
            });

            System.out.print("Enter Order ID: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            Order existingOrder = orderRepo.read(orderId);

            if (existingOrder == null) {
                System.out.println("Order not found. Operation cancelled.");
                return;
            }

            System.out.println("\n=== Select New Menu Items (Enter IDs, separated by commas) ===");
            List<MenuItem> menuItems = menuItemRepo.getAll();
            if (menuItems.isEmpty()) {
                System.out.println("No menu items available. Please add menu items first.");
                return;
            }
            menuItems.forEach(menuItem -> {
                System.out.println(menuItem.getId() + " - " + menuItem.getName());
            });

            System.out.print("Enter Menu Item IDs: ");
            String[] itemIds = scanner.nextLine().split(",");
            List<MenuItem> newItems = new ArrayList<>();

            for (String itemIdStr : itemIds) {
                try {
                    int itemId = Integer.parseInt(itemIdStr.trim());
                    MenuItem menuItem = menuItemRepo.read(itemId);
                    if (menuItem != null) {
                        newItems.add(menuItem);
                    } else {
                        System.out.println("Menu item with ID " + itemId + " not found.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid menu item ID: " + itemIdStr.trim());
                }
            }

            if (newItems.isEmpty()) {
                System.out.println("No valid items selected. Operation cancelled.");
                return;
            }

            List<MenuItem> updatedItems = new ArrayList<>(existingOrder.getItems());
            updatedItems.addAll(newItems);

            Order updatedOrder = new Order.Builder(existingOrder.getId())
                    .table(existingOrder.getTable())
                    .items(updatedItems)
                    .build();

            orderRepo.update(updatedOrder);
            System.out.println("Items added to order successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
        }
    }

    private static void removeItemOrder(Scanner scanner) {
        try {
            System.out.println("\n=== Remove Items from Existing Order ===");

            List<Order> orders = orderRepo.getAll();
            if (orders.isEmpty()) {
                System.out.println("No orders available. Please create an order first.");
                return;
            }

            orders.forEach(order -> {
                System.out.println("Order ID: " + order.getId() + ", Table n°: " + order.getTable().getTableNumber());
            });

            System.out.print("Enter Order ID: ");
            int orderId = Integer.parseInt(scanner.nextLine());
            Order existingOrder = orderRepo.read(orderId);

            if (existingOrder == null) {
                System.out.println("Order not found. Operation cancelled.");
                return;
            }

            List<MenuItem> currentItems = existingOrder.getItems();
            if (currentItems.isEmpty()) {
                System.out.println("No items in this order. Operation cancelled.");
                return;
            }

            System.out.println("\n=== Items in the Order ===");
            currentItems.forEach(item -> {
                System.out.println(item.getId() + " - " + item.getName() + " ($" + item.getPrice() + ")");
            });

            System.out.print("Enter Menu Item IDs to remove (separated by commas): ");
            String[] itemIds = scanner.nextLine().split(",");
            List<MenuItem> updatedItems = new ArrayList<>(currentItems);

            for (String itemIdStr : itemIds) {
                try {
                    int itemId = Integer.parseInt(itemIdStr.trim());
                    MenuItem itemToRemove = menuItemRepo.read(itemId);
                    if (itemToRemove != null && updatedItems.contains(itemToRemove)) {
                        updatedItems.remove(itemToRemove);
                    } else {
                        System.out.println("Item with ID " + itemId + " is not in the order.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid menu item ID: " + itemIdStr.trim());
                }
            }

            Order updatedOrder = new Order.Builder(existingOrder.getId())
                    .table(existingOrder.getTable())
                    .items(updatedItems)
                    .build();

            orderRepo.update(updatedOrder);
            System.out.println("Items removed from order successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
        }
    }


}
