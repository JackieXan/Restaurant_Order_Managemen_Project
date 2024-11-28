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

    public void addMenuItem(MenuItem menuItem) {
        if (menuItem != null) {
            System.out.println("Menu item \"" + menuItem.getName() + "\" added by manager.");
        } else {
            System.out.println("Cannot add a null menu item.");
        }
    }

    public void removeMenuItem(MenuItem menuItem) {
        if (menuItem != null) {
            System.out.println("Menu item \"" + menuItem.getName() + "\" removed by manager.");
        } else {
            System.out.println("Cannot remove a null menu item.");
        }
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + getId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                '}';
    }
}
