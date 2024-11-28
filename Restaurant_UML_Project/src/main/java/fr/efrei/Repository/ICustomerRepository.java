package fr.efrei.Repository;

import fr.efrei.Domain.Customer;

import java.util.List;

public interface ICustomerRepository extends IRepository<Customer, Integer> {
    List<Customer> getAll();
}
