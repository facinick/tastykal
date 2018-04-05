package com.example.nic.tastykal;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nic on 4/5/18.
 */

public class runthis0 {

    Cursor cursor = null;
    likesloader myDbHelper;

    private static final String ACTOR_NAME_T0 = "ACTOR_NAME_T0";
    private static final String ACTOR_LIKE__T0 = "ACTOR_LIKE__T0";

    private static final String DIRECTOR_NAME__T1 = "DIRECTOR_NAME__T1";
    private static final String DIRECTOR_LIKE__T1 = "DIRECTOR_LIKE__T1";

    public void runthiss0(Context con) throws IOException {

     myDbHelper = new likesloader(con);
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
    public double getactorlikes(String actorname)
    {

        String[] projection = {
                ACTOR_LIKE__T0
        };
        String selection = ACTOR_NAME_T0 + " = ?";
        String[] selectionArgs = {actorname};
        cursor = myDbHelper.query("ACTOR", projection, selection, selectionArgs, null, null, null);

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
        cursor = myDbHelper.query("DIRECTOR", projection, selection, selectionArgs, null, null, null);

        double val = 0.0;
        if (cursor.moveToFirst()) {
            do {
                val = cursor.getDouble(cursor.getColumnIndex(DIRECTOR_LIKE__T1));

            } while (cursor.moveToNext());
        }

        return val;

    }


}
