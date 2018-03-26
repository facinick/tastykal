package com.example.nic.tastykal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nic on 3/24/18.
 */

public class moviedatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "moviedb";

    // movie table name
    private static final String TABLENAME = "movie_metadata";

    // movie Table Columns names
    private static final String YEAR = "title_year";
    private static final String DIRECTOR = "director_facebook_likes";
    private static final String DURATION = "duration";
    private static final String ACTOR3 = "actor_3_facebook_likes";
    private static final String ACTOR2 = "actor_2_facebook_likes";
    private static final String ACTOR1 = "actor_1_facebook_likes";
    private static final String FACES = "facenumber_in_poster";
    private static final String BUDGET = "budget";

    private static final String IMDB = "imdb_score";

    public moviedatabase(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STUDENT_DETAIL_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLENAME + "("
                + DIRECTOR + " REAL,"
                + ACTOR3 + " REAL,"
                + ACTOR1 + " REAL,"
                + FACES + " REAL,"
                + BUDGET + " REAL,"
                + YEAR + " REAL,"
                + ACTOR2 + " REAL,"
                + IMDB + " REAL" +")";

        db.execSQL(CREATE_STUDENT_DETAIL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);

        // Create tables again
        onCreate(db);
    }

    void addmovie(Movie newStud) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(YEAR, newStud.YEAR);
        values.put(DIRECTOR, newStud.DIRECTOR);
        values.put(DURATION, newStud.DURATION);
        values.put(ACTOR3, newStud.ACTOR3);
        values.put(ACTOR2, newStud.ACTOR2);
        values.put(ACTOR1, newStud.ACTOR1);
        values.put(FACES, newStud.FACES);
        values.put(BUDGET, newStud.BUDGET);
        values.put(IMDB, newStud.IMDB);

        db.insert(TABLENAME, null, values);
        db.close(); // Closing database connection
    }

    public List<Movie> getallmovie() {

        List<Movie> userlist = new ArrayList<Movie>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLENAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                Movie stdnt = new Movie();
                stdnt.ACTOR1 = cursor.getDouble(cursor.getColumnIndex(ACTOR1));
                stdnt.ACTOR2 = cursor.getDouble(cursor.getColumnIndex(ACTOR2));
                stdnt.ACTOR3=cursor.getDouble(cursor.getColumnIndex(ACTOR3));
                stdnt.DIRECTOR=cursor.getDouble(cursor.getColumnIndex(DIRECTOR));
                stdnt.DURATION=cursor.getDouble(cursor.getColumnIndex(DURATION));
                stdnt.FACES=cursor.getDouble(cursor.getColumnIndex(FACES));
                stdnt.IMDB=cursor.getDouble(cursor.getColumnIndex(IMDB));
                stdnt.BUDGET=cursor.getDouble(cursor.getColumnIndex(BUDGET));
                stdnt.YEAR=cursor.getDouble(cursor.getColumnIndex(YEAR));

                userlist.add(stdnt);

            } while (cursor.moveToNext());
        }

        // return contact list
        return userlist;
    }

    public double getactorlikes(String actorname)
    {

        //get fb likes from table given name selected from spinner
        return 0.0;
    }
    public double getdirectorlikes(String directorname)
    {
        return 0.0;
    }


}
