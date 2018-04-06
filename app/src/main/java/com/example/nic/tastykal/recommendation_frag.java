package com.example.nic.tastykal;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by nic on 3/22/18.
 */

public class recommendation_frag extends Fragment {


    ListView movielistview;
    Button b;
    final runthis2 run1 = new runthis2();
    EditText editsearch;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.recommendation_frag_layout,container,false);
        movielistview = (ListView) v.findViewById(R.id.movielist);
        String username="";
        String password;
        editsearch = (EditText) v.findViewById(R.id.search);
        movielistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"Added to your list",Toast.LENGTH_SHORT).show();
            }
        });

        ((Button) v.findViewById(R.id.recommend)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



              ArrayList<String> movies = run1.loadusergenremap();



                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Recommendations...");

                ListView myNames = (ListView) dialog.findViewById(R.id.List);
                ArrayAdapter<String> adapter1 = new ArrayAdapter(getActivity() ,android.R.layout.simple_list_item_1, movies);
                myNames.setAdapter(adapter1);
                dialog.show();

            }


            });

        if (getArguments() != null && getArguments().getString("username") != null)
        {
             username = getArguments().getString("username");
             password = getArguments().getString("password");
        }

        run1.runthis2(getActivity(),username);

        ArrayList<String> movielist = run1.loadmovienames();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,movielist);
        movielistview.setAdapter(adapter);

        movielistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                run1.loadgenrefrommovie(name);
            }
        });




        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.getFilter().filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
        return v;




    }



}
