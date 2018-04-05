package com.example.nic.tastykal;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by nic on 4/5/18.
 */

public class bestgenre {
    String xyz = "('";
    String gen[] = new String[3];
    ArrayList<String> best = new ArrayList<>();
    HashMap genremap;
    String username;


    public bestgenre(String username, HashMap genremap) {
        this.username=username;
        this.genremap=genremap;
    }

    public ArrayList<String> returnme()
    {
        int min = -1;
        int f = 0;
        Set<String> keys = genremap.keySet();
        for (String key : keys) {
            if (min < (Integer) genremap.get(key)) {
                gen[0] = key;
                min = (Integer) genremap.get(key);
            }
        }
        f = min;
        for (String key : keys) {
            if (f == (Integer) genremap.get(key)) {
                best.add(key);
            }
        }
        min = -1;
        for (String key : keys) {
            if (min < (Integer) genremap.get(key) && (Integer) genremap.get(key) != f) {
                gen[1] = key;
                min = (Integer) genremap.get(key);
            }
        }
        int g = min;
        for (String key : keys) {
            if (g == (Integer) genremap.get(key)) {
                best.add(key);
            }
        }
        min = -1;
        int h = 0;
        for (String key : keys) {
            if (min < (Integer) genremap.get(key) && (Integer) genremap.get(key) != g && (Integer) genremap.get(key) != f) {
                gen[2] = key;
                min = (Integer) genremap.get(key);
            }
        }
        h = min;
        for (String key : keys) {
            if (h == (Integer) genremap.get(key)) {
                best.add(key);
            }
        }




        return best;
    }
}