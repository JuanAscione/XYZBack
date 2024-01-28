package com.example.xyzhotelddd.utils;

public class Validators {

    public boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        else {
            char[] chars = name.toCharArray();
            for (char c : chars) {
                if(!Character.isLetter(c)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validateEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("(06|07)\\d{8}");
    }

}
