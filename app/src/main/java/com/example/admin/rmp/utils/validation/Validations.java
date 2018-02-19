package com.example.admin.rmp.utils.validation;

/**
 * Created by Nikam on 05/02/2018.
 */

public class Validations {

    public static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }
}
