package fr.efrei.Repository;

import fr.efrei.Domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements IOrderRepository {
    private static OrderRepository repository = null;
    private final List<Order> orderList;

    private OrderRepository() {
        this.orderList = new ArrayList<>();
    }

    public static OrderRepository getRepository() {
        if (repository == null) {
            repository = new OrderRepository();
        }
        return repository;
    }

    @Override
    public Order create(Order order) {
        this.orderList.add(order);
        return order;
    }

    @Override
    public Order read(Integer id) {
        for (Order order : this.orderList) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    @Override
    public Order update(Order order) {
        Order existingOrder = read(order.getId());
        if (existingOrder != null) {
            delete(existingOrder.getId());
            this.orderList.add(order);
            return order;
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        Order order = read(id);
        if (order != null) {
            this.orderList.remove(order);
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(this.orderList);
    }
}
