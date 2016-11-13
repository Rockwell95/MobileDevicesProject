package com.example.devin.mobiledevicesproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "";
    private static final String DATABASE_NAME = "";
    private static int DATABASE_VERSION = 1;

    private static final String KEY_USER_ID = "userID";
    private static final String KEY_USER_NAME = "userName";
    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String KEY_USER_BIRTHDATE = "userBirthdate";

    public DBHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * Creates a new database table
     */
    @Override
    public void onCreate (SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                            KEY_USER_ID + " INTEGER PRIMARY KEY " +
                            KEY_USER_NAME + " TEXT NOT NULL UNIQUE " +
                            KEY_USER_EMAIL + " TEXT NOT NULL UNIQUE " +
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
     * Takes a user and attempts to add its attributes to the database. If name or email is
     * not unique, returns false. Otherwise, returns true.
     */
    public boolean addUser (User user) {
        boolean inserted = true; // assume information is unique and valid
        // extract data from user
        String name = user.getName();
        String email = user.getEmail();
        String birthdate = user.getBirthdate();

        // TODO: perform error checking on values

        // TODO: attempt to insert user into database

        return inserted;
    }
}
