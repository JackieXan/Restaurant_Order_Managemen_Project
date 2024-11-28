package fr.efrei.Factory;

import fr.efrei.Domain.Order;
import fr.efrei.Domain.Payment;

public class PaymentFactory {
    public static Payment buildPayment(int id, Order order, String method, String status) {
        if (order == null || method == null || method.isEmpty() || status == null || status.isEmpty()) {
            return null;
        }
        return new Payment.Builder(id)
                .order(order)
                .method(method)
                .status(status)
                .build();
    }
}
