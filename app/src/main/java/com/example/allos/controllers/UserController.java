package com.example.allos.controllers;

public class UserController {

    public static String validateLoginUser(String username, String password){
        if (username.isEmpty()){
            return "username";
        }if (!isAlphaNumeric(username)) {
            return "alphanumeric";
        }if (password.isEmpty()){
            return "password";
        }
        return "success";
    }

    public static String validateRegisterUser(String name, String username, String email, String password){
        if (name.isEmpty()){
            return "name";
        } if (username.isEmpty()){
            return "username";
        }if (!isAlphaNumeric(username)){
            return "alphanumeric";
        }if (email.isEmpty()){
            return "email";
        } if (password.isEmpty()){
            return "password";
        }
        return "success";
    }

    public static boolean isAlphaNumeric(String str){
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static String validateUpdateProfile(String name, String username, String email) {
        if (name.isEmpty()){
            return "name";
        } if (username.isEmpty()){
            return "username";
        }if (!isAlphaNumeric(username)){
            return "alphanumeric";
        }if (email.isEmpty()) {
            return "email";
        }
        return "success";
    }
}
