package com.example.nic.tastykal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by nic on 3/22/18.
 */

public class recommendation_frag extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.recommendation_frag_layout,container,false);

        Button b = (Button) v.findViewById(R.id.recommend);
        b.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View view) {

    }
}
