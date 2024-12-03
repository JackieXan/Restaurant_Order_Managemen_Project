package fr.efrei.Views;

import fr.efrei.Domain.Order;
import fr.efrei.Domain.Payment;
import fr.efrei.Factory.PaymentFactory;
import fr.efrei.Repository.OrderRepository;
import fr.efrei.Repository.PaymentRepository;

import java.util.Scanner;

public class PaymentView {
    private static final PaymentRepository paymentRepo = PaymentRepository.getRepository();
    private static final OrderRepository orderRepo = OrderRepository.getRepository();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Payment Menu ===");
            System.out.println("1. Make a Payment");
            System.out.println("2. Show All Payments");
            System.out.println("3. Back to Main Menu");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    makePayment(scanner);
                    break;
                case "2":
                    showAllPayments();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void makePayment(Scanner scanner) {
        try {
            System.out.println("\n=== Select Order ===");
            if (orderRepo.getAll().isEmpty()) {
                System.out.println("No orders available. Please create an order first.");
                return;
            }

            // Afficher toutes les commandes
            orderRepo.getAll().forEach(order ->
                    System.out.println(order.getId() + " - Table: " + order.getTable().getTableNumber())
            );

            // Demander l'ID de la commande
            System.out.print("Enter Order ID: ");
            String inputOrderId = scanner.nextLine().trim();
            int orderId = -1;

            try {
                orderId = Integer.parseInt(inputOrderId); // Convertir en entier
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Order ID.");
                return;
            }

            Order order = orderRepo.read(orderId);
            if (order == null) {
                System.out.println("Invalid order. Operation cancelled.");
                return;
            }

            // Demander le mode de paiement
            System.out.println("Select Payment Method:");
            System.out.println("1. Credit Card");
            System.out.println("2. Cash");
            System.out.print("Enter your choice: ");
            int inputMethodChoice = Integer.parseInt(scanner.nextLine().trim());
            String method = "";

            if (1==inputMethodChoice) {
                method = "Credit Card";
                System.out.println(method);

            } else if (2==inputMethodChoice) {
                method = "Cash";
                System.out.println(method);

            } else {
                System.out.println("Invalid choice. Please enter 1 for Credit Card or 2 for Cash.");
                return;
            }

            Payment payment = PaymentFactory.buildPayment(order, method);
            if (payment != null) {
                paymentRepo.create(payment);
                System.out.println("Payment successfull!");
                orderRepo.delete(payment.getOrder().getId());
            } else {
                System.out.println("Failed to create payment.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
        }
    }


    private static void showAllPayments() {
        System.out.println("\n=== Payment List ===");
        if (paymentRepo.getAll().isEmpty()) {
            System.out.println("No payments found.");
        } else {
            double totalAmount = 0.0;
            for (Payment payment : paymentRepo.getAll()) {
                try {
                    if (payment.getOrder() == null) {
                        System.out.println("Payment ID: " + payment.getId() + " has no associated order.");
                        continue;
                    }

                    System.out.println("Payment ID: " + payment.getId());
                    System.out.println("Order ID: " + payment.getOrder().getId());
                    System.out.println("Method: " + payment.getMethod());
                    System.out.println("Amount: " + payment.getOrder().getAmount() + "$");

                    totalAmount += payment.getOrder().getAmount();
                    System.out.println("----------------------");
                } catch (Exception e) {
                    System.out.println("Error processing payment ID " + payment.getId() + ": " + e.getMessage());
                }
            }

            // Afficher le total des paiements
            System.out.println("Total amount of payments: " + totalAmount + "$");
        }
    }

    private static void deletePayment(Scanner scanner) {
        try {
            System.out.print("Enter Payment ID to Delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            if (paymentRepo.delete(id)) {
                System.out.println("Payment deleted successfully!");
            } else {
                System.out.println("Failed to delete payment. Payment not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values where required.");
        }
    }
}
