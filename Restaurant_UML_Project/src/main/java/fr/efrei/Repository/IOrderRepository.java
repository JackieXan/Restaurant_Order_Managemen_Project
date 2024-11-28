package fr.efrei.Repository;

import fr.efrei.Domain.Order;

import java.util.List;

public interface IOrderRepository extends IRepository<Order, Integer> {
    List<Order> getAll();
}
