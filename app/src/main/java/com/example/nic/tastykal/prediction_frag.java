package com.example.nic.tastykal;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class prediction_frag extends Fragment implements View.OnClickListener {

    String username;
    String password;

    Spinner director, actor1, actor2, actor3, faces, year;
    EditText duration, budget;
    TextView rating;
    Button prediction;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.prediction_frag_layout,container,false);
        Button b = (Button) v.findViewById(R.id.predict);
        b.setOnClickListener(this);
        return v;

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null && getArguments().getString("username") != null)
        {
            username = getArguments().getString("username");
            password = getArguments().getString("password");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && username != "") {
                Log.e("output",username );
                Toast.makeText(getContext(), "welcome : "+username,Toast.LENGTH_SHORT).show();
            }
        }



        director = (Spinner) getActivity().findViewById(R.id.director);
        actor1 = (Spinner) getActivity().findViewById(R.id.actor1);
        actor2 = (Spinner) getActivity().findViewById(R.id.actor2);
        actor3 = (Spinner) getActivity().findViewById(R.id.actor3);
        faces = (Spinner) getActivity().findViewById(R.id.faces);
        year = (Spinner) getActivity().findViewById(R.id.year);

        duration = (EditText) getActivity().findViewById(R.id.duration);
        budget = (EditText) getActivity().findViewById(R.id.budget);

        rating = (TextView) getActivity().findViewById(R.id.rating);

        prediction = (Button) getActivity().findViewById(R.id.predict);





        moviedatabase m = new moviedatabase(getActivity());

        ArrayList<Movie> movilist = (ArrayList<Movie>) m.getallmovie();

        // populate the layout with movielist elements here

        // generate the ml weight matrix
    }



    @Override
    public void onClick(View view) {
        Toast.makeText(getActivity(),"tapped",Toast.LENGTH_SHORT).show();;
    }
}
