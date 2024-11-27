package fr.efrei.Domain;

public class MenuItem {
    private final int id;
    private final String name;
    private final double price;
    private final String description;

    private MenuItem(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
    }

    public static class Builder {
        private final int id;
        private String name;
        private double price;
        private String description;

        public Builder(int id) {
            this.id = id;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + price + " " + description;
    }
}
