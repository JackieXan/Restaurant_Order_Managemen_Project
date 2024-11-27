package fr.efrei.Domain;

public class Customer {
    private final int phoneNumber;
    private final String firstName;
    private final String lastName;

    private Customer(Builder builder) {
        this.phoneNumber = builder.phoneNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public static class Builder {
        private final int phoneNumber;
        private String firstName;
        private String lastName;

        public Builder(int phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public int getPhoneNumber() { return phoneNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    @Override
    public String toString() {
        return phoneNumber + " " + lastName + " " + lastName;
    }
}