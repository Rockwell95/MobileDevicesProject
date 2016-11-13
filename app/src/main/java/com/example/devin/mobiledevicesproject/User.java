package com.example.devin.mobiledevicesproject;

public class User {
    private String name;
    private String email;
    private String birthdate;

    /*
     * Creates a new User. Takes three Strings: name, email, and birthdate and
     * returns a new User with those attributes.
     */
    public User (String name, String email, String birthdate) {
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
    }

    public String getName () { return this.name; }
    public String getEmail () { return this.email; }
    public String getBirthdate () { return this.birthdate; }
}
