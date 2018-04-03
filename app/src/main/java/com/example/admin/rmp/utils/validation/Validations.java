package com.example.admin.rmp.utils.validation;

/**
 * Created by Nikam on 05/02/2018.
 */

public class Validations {

    public static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

    public static boolean isValidAadharOrUniqueIdNumber(String mobile) {
        String regEx = "^[0-9]{12,16}$";
        return mobile.matches(regEx);
    }

   /* String regexStr = "^[0-9]$";

    String number=entered_number.getText().toString();

        if(entered_number.getText().toString().length()<10 || number.length()>13 || number.matches(regexStr)==false  ) {
        Toast.makeText(MyDialog.this,"Please enter "+"\n"+" valid phone number",Toast.LENGTH_SHORT).show();
        // am_checked=0;
    }*/
}
