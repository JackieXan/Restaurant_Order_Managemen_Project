package fr.efrei.Factory;

import fr.efrei.Domain.Order;
import fr.efrei.Domain.Payment;
import fr.efrei.Util.Helper;

public class PaymentFactory {
    private static int currentId = 1;
    public static Payment buildPayment(Order order, String method) {
        if (order == null || method == null || method.isEmpty()) {
            return null;
        }
        int generatedId = getNextId();

        return new Payment.Builder(generatedId)
                .order(order)
                .method(method)

                .build();
    }
    public static int getNextId(){
        return currentId++;
    }
}
