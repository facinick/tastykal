package com.example.nic.tastykal;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nic on 4/5/18.
 */

public class runthis2 {

    Cursor cursor = null;
    DatabaseHelper2 myDbHelper;
    HashMap<String,Integer> genremap = new HashMap();

    Context con;
    String username;

    private static final String ACTOR_NAME_T0 = "ACTOR_NAME_T0";
    private static final String ACTOR_LIKE__T0 = "ACTOR_LIKE__T0";

    private static final String DIRECTOR_NAME__T1 = "DIRECTOR_NAME__T1";
    private static final String DIRECTOR_LIKE__T1 = "DIRECTOR_LIKE__T1";

    private static final String GENRE_T2 = "GENRE_T2";
    private static final String MOVIE_NAME_T2 = "MOVIE_NAME_T2";

    private static final String MOVIE_NAME_T3 = "MOVIE_NAME_T3";
    private static final String USER_T3 = "USER_T3";


    public void runthis2(Context con,String username)  {

        this.con = con;
        this.username = username;

        myDbHelper = new DatabaseHelper2(con);

        try {
            myDbHelper.createDataBase();
        } catch (Exception ioe) {
            Log.e("unable to create" , "database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            Log.e("unable to open" , "database");
        }

    }


    public ArrayList<String> loadmovienames() {


        String[] projection = {
                MOVIE_NAME_T2
        };

        cursor = myDbHelper.query2(true,"MOVIEGENRE", projection, null, null, null, null, null,null);

        ArrayList<String> names = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(cursor.getColumnIndex(MOVIE_NAME_T2)));

            } while (cursor.moveToNext());
        }

        myDbHelper.close();
        return names;

    }

    public void loadgenrefrommovie(String movie)
    {
        String selectQuery = "select * from MOVIEGENRE where MOVIE_NAME_T2 = '"+movie+"'";

        SQLiteDatabase db = myDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String insertQuery = "insert into USERGENRE values ( '"+cursor.getString(   0)+"','"+username+"'  ) ";

                Log.e("randi", cursor.getString(0));

                myDbHelper.getWritableDatabase().execSQL(insertQuery);

            } while (cursor.moveToNext());
        }
        db.close();
    }
    public ArrayList<String> loadusergenremap()
    {

       // Toast.makeText(con, "tapped",Toast.LENGTH_SHORT).show();

        genremap.clear();

        try{

            String sql = "select GENRE_T3 from USERGENRE where USER_T3 = '"+username+"'";

            SQLiteDatabase db = myDbHelper.getWritableDatabase();

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

        ArrayList<String> bestpref = new bestgenre(username,genremap).returnme();

        String xyz="('";
        for (int i = 0; i < bestpref.size(); i++) {
            xyz += bestpref.get(i);
            if (i < bestpref.size() - 1)
                xyz = xyz + "','";
            else
                xyz = xyz + "')";
        }

        Log.e("MAP",xyz);

        String sql1 = "select distinct(MOVIE_NAME_T2) from MOVIEGENRE where GENRE_T2 in "+xyz+" group by MOVIE_NAME_T2 ORDER BY RANDOM() LIMIT 20";


        SQLiteDatabase db = myDbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql1, null);

        ArrayList<String> movies = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                movies.add(cursor.getString(0));

            } while (cursor.moveToNext());
        }

        for( int i=0; i<movies.size(); i++ )
        {
            Log.e("movie",movies.get(i));
        }


        return movies;
        


    }


    public double getactorlikes(String actorname)
    {

        String[] projection = {
                ACTOR_LIKE__T0
        };
        String selection = ACTOR_NAME_T0 + " = ?";
        String[] selectionArgs = {actorname};
        cursor = myDbHelper.query1("ACTOR", projection, selection, selectionArgs, null, null, null);

        double val = 0.0;
        if (cursor.moveToFirst()) {
            do {
                val = cursor.getDouble(cursor.getColumnIndex(ACTOR_LIKE__T0));

            } while (cursor.moveToNext());
        }

        return val;

    }
    public double getdirectorlikes(String directorname) {
        String[] projection = {
                DIRECTOR_LIKE__T1
        };
        String selection = DIRECTOR_NAME__T1 + " = ?";
        String[] selectionArgs = {directorname};
        cursor = myDbHelper.query1("DIRECTOR", projection, selection, selectionArgs, null, null, null);

        double val = 0.0;
        if (cursor.moveToFirst()) {
            do {
                val = cursor.getDouble(cursor.getColumnIndex(DIRECTOR_LIKE__T1));

            } while (cursor.moveToNext());
        }

        return val;

    }

}
