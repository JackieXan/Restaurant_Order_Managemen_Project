package fr.efrei.Factory;

import fr.efrei.Domain.Order;
import fr.efrei.Domain.Payment;
import fr.efrei.Util.Helper;

public class PaymentFactory {
    private static int currentId = 1;
    public static Payment buildPayment(Order order, String method, String status) {
        if (order == null || method == null || method.isEmpty() || status == null || status.isEmpty()) {
            return null;
        }
        int generatedId = getNextId();

        return new Payment.Builder(generatedId)
                .order(order)
                .method(method)
                .status(status)
                .build();
    }
    public static int getNextId(){
        return currentId++;
    }
}
