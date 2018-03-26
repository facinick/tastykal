package com.example.nic.tastykal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by nic on 3/22/18.
 */

public class addremove_frag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.addremove_frag_layout, container, false);

        ((Button) v.findViewById(R.id.addm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Button1 clicked.", Toast.LENGTH_SHORT).show();
            }
        });

        ((Button) v.findViewById(R.id.removem)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Button2 clicked.", Toast.LENGTH_SHORT).show();
            }
        });
return v;

    }
}



