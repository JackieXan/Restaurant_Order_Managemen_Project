package fr.efrei.Domain;

import java.util.List;

public class Order {
    private final int id;
    private final Table table;
    private final List<MenuItem> items;

    private Order(Builder builder) {
        this.id = builder.id;
        this.table = builder.table;
        this.items = builder.items;
    }

    public int getId() {
        return id;
    }

    public Table getTable() {
        return table;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getAmount() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }


    public static class Builder {
        private int id;
        private Table table;
        private List<MenuItem> items;

        public Builder(int id) {
            this.id = id;
        }

        public Builder table(Table table) {
            this.table = table;
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
