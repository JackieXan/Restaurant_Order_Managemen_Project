package fr.efrei.Domain;

public class Manager extends Waiter {

    private Manager(Builder builder) {
        super(builder);
    }

    public static class Builder extends Waiter.Builder {
        public Builder(int id) {
            super(id);
        }

        @Override
        public Builder firstName(String firstName) {
            super.firstName(firstName);
            return this;
        }

        @Override
        public Builder lastName(String lastName) {
            super.lastName(lastName);
            return this;
        }

        @Override
        public Builder username(String username) {
            super.username(username);
            return this;
        }

        @Override
        public Builder password(String password) {
            super.password(password);
            return this;
        }

        @Override
        public Manager build() {
            return new Manager(this);
        }
    }


    @Override
    public String toString() {
        return getId() + " " + getFirstName() + " " + getLastName() + "\n   ->Unsername: " + getUsername();
    }
}
