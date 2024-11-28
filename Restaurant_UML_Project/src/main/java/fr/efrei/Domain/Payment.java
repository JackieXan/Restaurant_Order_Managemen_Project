package fr.efrei.Domain;

public class Payment {
    private final int id;
    private final Order order;
    private final String method;
    private final String status;

    private Payment(Builder builder) {
        this.id = builder.id;
        this.order = builder.order;
        this.method = builder.method;
        this.status = builder.status;
    }

    public static class Builder {
        private final int id;
        private Order order;
        private String method;
        private String status;

        public Builder(int id) {
            this.id = id;
        }

        public Builder order(Order order) {
            this.order = order;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }

    public int getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    public boolean processPayment(int amount) {
        double totalAmount = order.getAmount();
        if (amount >= totalAmount) {
            System.out.println("Payment processed successfully.");
            return true;
        } else {
            System.out.println("Payment failed. Insufficient amount.");
            return false;
        }
    }
}
