package com.example.devin.mobiledevicesproject;

public class RegexHelper {
    public final String email = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{}|~-]+@\\w+\\.\\w+$";
    public final String password = "^(?=.*[a-z]+)(?=.*[A-Z])(?=.*\\d).{8,}$";
    public final String birthdateDay = "^[\\d]{1,2}$";
    public final String birthdateMonth = "^[\\d]{1,2}$";
    public final String birthdateYear = "^[\\d]{4}$";
    public final String birthdate = "^[\\d]{1,2}\\/[\\d]{1,2}\\/[\\d]{4}$";
}