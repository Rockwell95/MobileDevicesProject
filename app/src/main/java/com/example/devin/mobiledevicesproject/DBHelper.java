package com.example.devin.mobiledevicesproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Users";
    private static final String DATABASE_NAME = "Users";
    private static int DATABASE_VERSION = 1;

    // database key information
    private static final String KEY_USER_ID = "userID";
    private static final String KEY_USER_FIRSTNAME = "userFirstName";
    private static final String KEY_USER_LASTNAME = "userLastName";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_USER_PASSWORD = "userPassword"; // TODO: password encryption?
    private static final String KEY_USER_BIRTHDATE = "userBirthdate";

    public final int CODE_SUCCESS = 0;
    public final int CODE_INVALID_EMAIL = 1;
    public final int CODE_EMAIL_TAKEN = 2;
    public final int CODE_INVALID_PASSWORD = 3;
    public final int CODE_INVALID_BIRTHDATE = 4;

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * Creates a new database table
     */
    @Override
    public void onCreate (SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                            KEY_USER_ID + " INTEGER PRIMARY KEY, " +
                            KEY_USER_FIRSTNAME + " TEXT, " +
                            KEY_USER_LASTNAME + " TEXT, " +
                            KEY_USER_EMAIL + " TEXT NOT NULL UNIQUE, " +
                            KEY_USER_PASSWORD + " TEXT NOT NULL, " +
                            KEY_USER_BIRTHDATE + " TEXT NOT NULL " +
                            ")";
        db.execSQL(createTable);
    }

    /*
     * Updates the database table if changes are detected
     */
    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(dropTable);
            onCreate(db);
            DATABASE_VERSION = newVersion;
        }
    }

    /*
    * Returns a new, unique ID by grabbing the largest ID from the database and incrementing it
    * by 1
    */
    public int getNextID() {
        String maxID = "SELECT MAX(" + KEY_USER_ID + ") FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery(maxID, null);
        int id = 0;
        result.moveToFirst();
        id = result.getInt(0);

        result.close();

        return id + 1;
    }

    /*
     * Takes a user and attempts to add its attributes to the database. If name or email is
     * not unique, returns false. Otherwise, returns true.
     */
    public int addUser (User user) {
        // extract data from user
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String password = user.getPassword();
        String birthdate = user.getBirthdate();

        // TODO: perform error checking on values
        RegexHelper rh = new RegexHelper(); // new instance of RegexHelper

        // perform basic input validation
        if (!email.matches(rh.email)) {
            return CODE_INVALID_EMAIL;
        } else if (!password.matches(rh.password)) {
            return CODE_INVALID_PASSWORD;
        } else if (!birthdate.matches(rh.birthdate)) {
            return CODE_INVALID_BIRTHDATE;
        }

        // query database for input email
        String queryEmail = "SELECT " + KEY_USER_EMAIL +
                            " FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor results = db.rawQuery(queryEmail, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            if (results.getString(0).equals(email)) {
                results.close();
                db.close();
                return CODE_EMAIL_TAKEN;
            }
            results.moveToNext();
        }

        results.close();

        // TODO: compare birthdate year against current year somehow

        ContentValues values = new ContentValues();

        // add all values to a new row
        values.put(KEY_USER_ID, getNextID());
        values.put(KEY_USER_FIRSTNAME, firstName);
        values.put(KEY_USER_LASTNAME, lastName);
        values.put(KEY_USER_EMAIL, email);
        values.put(KEY_USER_PASSWORD, password);
        values.put(KEY_USER_BIRTHDATE, birthdate);

        db.insert(TABLE_NAME, null, values); // insert to database

        db.close();
        return CODE_SUCCESS;
    }

    // TODO: attempt to login with supplied information and return user data if it is correct
    public User login (String email, String password) {
        User user = null;

        return user;
    }
}
