package fr.efrei.Domain;

import java.util.List;

public class Order {
    private final int id;
    private final Customer customer;
    private final List<MenuItem> items;

    private Order(Builder builder) {
        this.id = builder.id;
        this.customer = builder.customer;
        this.items = builder.items;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getAmount() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer.getFirstName() + " " + customer.getLastName() +
                ", items=" + items +
                '}';
    }

    public static class Builder {
        private int id;
        private Customer customer;
        private List<MenuItem> items;

        public Builder(int id) {
            this.id = id;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder items(List<MenuItem> items) {
            this.items = items;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
