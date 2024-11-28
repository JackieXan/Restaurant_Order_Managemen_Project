package fr.efrei.Repository;

import fr.efrei.Domain.Payment;

import java.util.List;

public interface IPaymentRepository extends IRepository<Payment, Integer> {
    List<Payment> getAll();

}
