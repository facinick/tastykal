package com.example.nic.tastykal;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nic on 4/5/18.
 */

public class runthis {
    Cursor cursor = null;

    private static final String YEAR = "title_year";
    private static final String DIRECTOR = "director_facebook_likes";
    private static final String DURATION = "duration";
    private static final String ACTOR3 = "actor_3_facebook_likes";
    private static final String ACTOR2 = "actor_2_facebook_likes";
    private static final String ACTOR1 = "actor_1_facebook_likes";
    private static final String FACES = "facenumber_in_poster";
    private static final String BUDGET = "budget";

    private static final String IMDB = "imdb_score";

    public List<Movie> runthiss(Context con) throws IOException {

        DatabaseHelper myDbHelper = new DatabaseHelper(con);
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
        List<Movie> userlist = new ArrayList<Movie>();
        cursor = myDbHelper.query("MOVIEMETADATA", null, null, null, null, null, null);
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
        return userlist;
    }


}
