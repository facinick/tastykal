package com.example.nic.tastykal;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nic on 3/22/18.
 */

public class usersdatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "appusers";

    // Contacts table name
    private static final String TABLENAME = "users";

    // Contacts Table Columns names
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";


    public usersdatabase(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STUDENT_DETAIL_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLENAME + "("
                + USERNAME + " TEXT PRIMARY KEY,"
                + PASSWORD + " TEXT" +")";

        db.execSQL(CREATE_STUDENT_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);

        // Create tables again
        onCreate(db);
    }


    // Adding new Student Information
    void adduser(User newStud) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(USERNAME, newStud.username);
        values.put(PASSWORD, newStud.password);

        // Inserting Row
        db.insert(TABLENAME, null, values);
        db.close(); // Closing database connection
    }



    public boolean deleteuser(String username){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLENAME, USERNAME + "=" + username, null) > 0;

    }


    // Getting All Students
    public List<User> getalluser() {


        List<User> userlist = new ArrayList<User>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLENAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                User stdnt = new User();
                stdnt.username = cursor.getString(0);
                stdnt.password=cursor.getString(1);


                // Adding contact to list
                userlist.add(stdnt);

            } while (cursor.moveToNext());
        }

        // return contact list
        return userlist;
    }
}
