package fr.efrei.Repository;

import fr.efrei.Domain.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements IPaymentRepository {
    private static PaymentRepository repository = null;
    private final List<Payment> payments;

    private PaymentRepository() {
        this.payments = new ArrayList<>();
    }

    public static PaymentRepository getRepository() {
        if (repository == null) {
            repository = new PaymentRepository();
        }
        return repository;
    }

    @Override
    public Payment create(Payment payment) {
        if (payment != null && !payments.contains(payment)) {
            payments.add(payment);
            return payment;
        }
        return null;
    }

    @Override
    public Payment read(Integer id) {
        for (Payment payment : payments) {
            if (payment.getId() == id) {
                return payment;
            }
        }
        return null;
    }

    @Override
    public Payment update(Payment payment) {
        Payment existingPayment = read(payment.getId());
        if (existingPayment != null) {
            payments.remove(existingPayment);
            payments.add(payment);
            return payment;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Payment payment = read(id);
        if (payment != null) {
            payments.remove(payment);
            return true;
        }
        return false;
    }

    @Override
    public List<Payment> getAll() {
        return new ArrayList<>(payments);
    }
}
