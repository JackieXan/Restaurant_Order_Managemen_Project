package fr.efrei.Repository;

import fr.efrei.Domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    private static CustomerRepository repository = null;
    private final List<Customer> customerList;

    private CustomerRepository() {
        this.customerList = new ArrayList<>();
    }

    public static CustomerRepository getRepository() {
        if (repository == null) {
            repository = new CustomerRepository();
        }
        return repository;
    }

    @Override
    public Customer create(Customer customer) {
        this.customerList.add(customer);
        return customer;
    }

    @Override
    public Customer read(Integer id) {
        for (Customer customer : this.customerList) {
            if (customer.getPhoneNumber() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public Customer update(Customer customer) {
        Customer existingCustomer = read(customer.getPhoneNumber());
        if (existingCustomer != null) {
            delete(existingCustomer.getPhoneNumber());
            this.customerList.add(customer);
            return customer;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Customer customer = read(id);
        if (customer != null) {
            this.customerList.remove(customer);
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(this.customerList);
    }
}
