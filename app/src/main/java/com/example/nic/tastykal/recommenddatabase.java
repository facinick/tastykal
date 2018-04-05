package com.example.nic.tastykal;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nic on 4/4/18.
 */

public class recommenddatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    String username;
    // Database Name
    private static final String DATABASE_NAME = "recommend";

    ArrayList<String> movienames;
    HashMap<String,Integer> genremap = new HashMap();

    private static final String TABLENAME1 = "USER_GENRE";
    private static final String TABLENAME2 = "MOVIE_GENRE";
    private static final String TABLENAME3 = "ACTORDIRECTOR_LIKES";


    // movie Table Columns names
    private static final String USERNAME = "username";
    private static final String GENRE = "genres";
    private static final String MOVIENAME = "movie_title";
    private static final String ACTORNAME = "actor_name";
    private static final String DIRECTORNAME = "director_name";
    private static final String ACTORLIKE = "actor_facebook_likes";
    private static final String DIRECTORLIKE = "director_facebook_likes";

    public recommenddatabase(Context contex) {
        super(contex, DATABASE_NAME, null, DATABASE_VERSION);
        movienames = new ArrayList<>();

    }

    //creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERGENRE = "CREATE TABLE IF NOT EXISTS " + TABLENAME1 + "("
                + USERNAME + " REAL,"
                + GENRE + " REAL" +")";

        db.execSQL(CREATE_USERGENRE);

        String CREATE_MOVIEGENRE = "CREATE TABLE IF NOT EXISTS " + TABLENAME2 + "("
                + MOVIENAME + " REAL,"
                + GENRE + " REAL" +")";

        db.execSQL(CREATE_MOVIEGENRE);

        String ACTORDIRECTORLIKES = "CREATE TABLE IF NOT EXISTS " + TABLENAME3 + "("
                + ACTORNAME + " VARCHAR(30),"
                + DIRECTORNAME + " VARCHAR(30),"
                + ACTORLIKE + " REAL,"
                + DIRECTORLIKE + " REAL" +")";

        db.execSQL(ACTORDIRECTORLIKES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME3);

        // Create tables again
        onCreate(db);
    }

    public void loadmovienames() {
        String selectQuery = "select distinct movie_title from moviegenre";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                movienames.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }
    }

    public void loadgenrefrommovie(String movie)
    {
        String selectQuery = "select genres from MOVIE_GENRE where movie_title = '"+movie+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String insertQuery = "insert into USER_GENRE values ( '"+username+"' , '"+cursor.getString(0)+"'  ) ";

                db.rawQuery(insertQuery, null);

            } while (cursor.moveToNext());
        }
    }
    public void loadusergenremap()
    {
        genremap.clear();

        try{

            String sql = "select genres from USERGENRE where username = '"+username+"' ";

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(sql, null);

            String gen = "";
            if (cursor.moveToFirst()) {
                do {
                    gen = cursor.getString(0);
                    if( genremap.get(gen) == null )
                    {

                        genremap.put(gen, 1);
                    }
                    else{
                        genremap.put(gen, genremap.get(gen) + 1);
                    }
                } while (cursor.moveToNext());
            }



            for (Map.Entry<String,Integer> entry : genremap.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                Log.e("map", key +"    :    " + Integer.toString(value));
            }


        }
        catch(SQLException se){
            se.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    public double getactorlikes(String actorname)
    {

        //RAW CODE NOT TESTED NEEDS TO BE EDITED BUT IDEA IS CLEAR
        String selectQuery = "SELECT  * FROM " + TABLENAME3+ " WHERE actor_name = '"+actorname+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        double val=0.0;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                val = cursor.getDouble(cursor.getColumnIndex(ACTORLIKE));

            } while (cursor.moveToNext());
        }

        // return contact list
        return val;

    }
    public double getdirectorlikes(String directorname)
    {
        String selectQuery = "SELECT  * FROM " + TABLENAME3+ " WHERE director_facebook_likes = '"+directorname+"'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        double val=0.0;

        if (cursor.moveToFirst()) {
            do {

                val = cursor.getDouble(cursor.getColumnIndex(DIRECTORLIKE));

            } while (cursor.moveToNext());
        }
        return val;
    }




}
