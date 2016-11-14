package com.example.devin.mobiledevicesproject;

// TODO: possible hashing of password?
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthdate;

    // TODO: remove if unnecessary
    /*
     * Creates a new User. Takes three Strings: email, password, and birthdate and
     * returns a new User with those attributes.
     */
    public User (String email, String password, String birthdate) {
        this.firstName = "";
        this.lastName = "";
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }

    /*
    * Creates a new User. Takes five Strings: firstName, lastName,  email, password, and birthdate and
    * returns a new User with those attributes.
    */
    public User (String firstName, String lastName, String email, String password, String birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
    }

    public String getFirstName () { return this.firstName; }
    public String getLastName () { return this.lastName; }
    public String getEmail () { return this.email; }
    public String getPassword () { return this.password; }
    public String getBirthdate () { return this.birthdate; }
}
