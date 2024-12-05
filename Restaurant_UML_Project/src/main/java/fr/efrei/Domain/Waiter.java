package fr.efrei.Domain;

public class Waiter {
    private final int id;
    private final String lastName;
    private final String firstName;
    private final String username;
    private final String password;

    protected Waiter(Builder builder) {
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.username = builder.username;
        this.password = builder.password;
    }

    public static class Builder {
        private final int id;
        private String lastName;
        private String firstName;
        private String username;
        private String password;

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

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return getId() + " " + getFirstName() + " " + getLastName() + "\n   ->Unsername: " + getUsername();
    }
}
