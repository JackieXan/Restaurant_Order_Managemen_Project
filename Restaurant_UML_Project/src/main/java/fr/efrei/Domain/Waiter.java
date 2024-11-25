package fr.efrei.Domain;

public class Waiter {
    private final int id;
    private final String lastName;
    private final String firstName;

    protected Waiter(Builder builder) {
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
    }

    public static class Builder {
        private final int id;
        private String lastName;
        private String firstName;

        public Builder(int id) {
            this.id = id;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Waiter build() {
            return new Waiter(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Waiter{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
