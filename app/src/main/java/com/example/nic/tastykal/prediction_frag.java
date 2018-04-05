package com.example.nic.tastykal;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

public class prediction_frag extends Fragment implements View.OnClickListener {

    String username;
    String password;

    Spinner director, actor1, actor2, actor3, faces, year;
    EditText duration, budget;
    TextView rating;
    Button prediction;

    double X[][];
    double Y[][];
    double Q[][];
    double x[][];

    predictionCALC p;
    moviedatabase m;
    recommenddatabase r;
    runthis run;

    Context c;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.prediction_frag_layout,container,false);
        Button b = (Button) v.findViewById(R.id.predict);
        b.setOnClickListener(this);
        director = (Spinner) v.findViewById(R.id.director);
        actor1 = (Spinner) v.findViewById(R.id.actor1);
        actor2 = (Spinner) v.findViewById(R.id.actor2);
        actor3 = (Spinner) v.findViewById(R.id.actor3);
        faces = (Spinner) v.findViewById(R.id.faces);
        year = (Spinner) v.findViewById(R.id.year);
        duration = (EditText) v.findViewById(R.id.duration);
        budget = (EditText) v.findViewById(R.id.budget);
        rating = (TextView) v.findViewById(R.id.rating);
        prediction = (Button) v.findViewById(R.id.predict);








        //load spinner







        return v;

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        c = getActivity();

        try {
            setup();
        } catch (IOException e) {
            e.printStackTrace();
        }

        run = new runthis();

        List<Movie> userlist = null;
        try {
            userlist = run.runthiss(c);

        } catch (Exception e) {

            e.printStackTrace();
        }




         X = new double[4917][9];
         Y = new double[4917][1];
         Q = new double[9][1];
         x = new double[1][9];

//         m = new moviedatabase(getActivity());
//         r = new recommenddatabase(getActivity());



        Toast.makeText(getActivity(),"reading database",Toast.LENGTH_SHORT).show();
        for( int i=0; i< userlist.size(); i++ )
        {

            X[i][0]=1;
            X[i][1]=userlist.get(i).ACTOR1;
            X[i][2]=userlist.get(i).ACTOR2;
            X[i][3]=userlist.get(i).ACTOR3;
            X[i][4]=userlist.get(i).DIRECTOR;
            X[i][5]=userlist.get(i).YEAR;
            X[i][6]=userlist.get(i).DURATION;
            X[i][7]=userlist.get(i).FACES;
            X[i][8]=userlist.get(i).BUDGET;

            Y[i][0] = userlist.get(i).IMDB;

        }

        p = new predictionCALC(getActivity(), X, Y, Q);




    }
    @Override
    public void onClick(View view) {

        String dname = director.getSelectedItem().toString();
        String ac1 = actor1.getSelectedItem().toString();
        String ac2 = actor2.getSelectedItem().toString();
        String ac3 = actor3.getSelectedItem().toString();

        runthis0 run = new runthis0();
        try {
            run.runthiss0(c);
        } catch (IOException e) {
            e.printStackTrace();
        }


        x[0][0] = 1.0;


        x[0][1] = 10000;//run.getactorlikes(ac1);
        x[0][2] = 2000;//run.getactorlikes(ac2);
        x[0][3] = 200;//run.getactorlikes(ac3);
        x[0][4] = 9000;//run.getdirectorlikes(dname);


        x[0][5]=  2012;//(double)year.getSelectedItem();
        x[0][6] = 200;//Double.parseDouble(duration.getText().toString());
        x[0][7] = 1; //(double)faces.getSelectedItem();
        x[0][8] = 500000000;//Double.parseDouble(budget.getText().toString());

        double imdb = p.predict(x);
        rating.setText(Double.toString(imdb));

        if( imdb < 5.0 )
        {
            rating.setBackgroundColor(Color.RED);
        }
        else if (imdb >= 5 && imdb < 8 )
        {
            rating.setBackgroundColor(Color.YELLOW);
        }
        else{
            rating.setBackgroundColor(Color.GREEN);
        }
    }

    public void setup() throws IOException {


        if (getArguments() != null && getArguments().getString("username") != null)
        {
            username = getArguments().getString("username");
            password = getArguments().getString("password");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && username != "") {
                Log.e("welcome",username );
                Toast.makeText(getContext(), "welcome : "+username,Toast.LENGTH_SHORT).show();
            }
        }

    }





}
