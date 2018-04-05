package com.example.nic.tastykal;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by nic on 3/22/18.
 */

public class recommendation_frag extends Fragment {


    ListView movielistview;
    Button b;
    final runthis2 run1 = new runthis2();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.recommendation_frag_layout,container,false);
        movielistview = (ListView) v.findViewById(R.id.movielist);
        String username="";
        String password;

        ((Button) v.findViewById(R.id.recommend)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



              run1.loadusergenremap();
            }
        });



        if (getArguments() != null && getArguments().getString("username") != null)
        {
             username = getArguments().getString("username");
             password = getArguments().getString("password");
        }

        run1.runthis2(getActivity(),username);

        ArrayList<String> movielist = run1.loadmovienames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,movielist);
        movielistview.setAdapter(adapter);

        movielistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                run1.loadgenrefrommovie(name);
            }
        });

        return v;

    }



}
