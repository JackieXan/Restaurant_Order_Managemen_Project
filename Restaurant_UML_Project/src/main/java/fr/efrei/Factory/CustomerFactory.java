package fr.efrei.Factory;

import fr.efrei.Domain.Customer;
import fr.efrei.Util.Helper;

public class CustomerFactory {

    public static Customer buildCustomer(int phoneNumber, String firstName, String lastName) {
        if (Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || Helper.isValidPhoneNumber(phoneNumber)) {
            return null;
        }

        return new Customer.Builder(phoneNumber)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

}