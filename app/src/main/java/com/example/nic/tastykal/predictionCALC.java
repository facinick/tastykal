package com.example.nic.tastykal;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.ejml.simple.SimpleMatrix;

public class predictionCALC {

    Context context;
    SimpleMatrix QQ;
    SimpleMatrix XX;
    SimpleMatrix xx;
    SimpleMatrix YY;
    SimpleMatrix II;

    double imdb;

    public predictionCALC(Context context, double X1[][],double Y1[][],double Q1[][])
    {
        this.context = context;

        QQ = new SimpleMatrix(Q1);
        XX = new SimpleMatrix(X1);
        YY = new SimpleMatrix(Y1);


        QQ = ((((XX.transpose()).mult(XX)).invert()).mult(XX.transpose())).mult(YY);       // weight matrix

        Toast.makeText(context, Integer.toString(XX.numRows()) +" learned", Toast.LENGTH_SHORT ).show();


        for(int i=0; i< XX.numRows(); i++)
        {
            Log.e("X ROW"+String.valueOf(i) +" imdb : "+String.valueOf(YY.get(i,0)),String.valueOf(XX.get(i,0))+" : " +String.valueOf(XX.get(i,1))+" : " +String.valueOf(XX.get(i,2))+" : " +String.valueOf(XX.get(i,3))+" : " +String.valueOf(XX.get(i,4))+" : " +String.valueOf(XX.get(i,5))+" : " +String.valueOf(XX.get(i,6))+" : " +String.valueOf(XX.get(i,7))+" : " +String.valueOf(XX.get(i,8)));
        }
        for(int i=0; i< QQ.numRows(); i++)
        {
            Log.e("QQ ROW"+String.valueOf(i),String.valueOf(QQ.get(i,0)));
        }

    }

    public double predict(double x[][])
    {

        xx = new SimpleMatrix(x);
        II = new SimpleMatrix(1,1);


        II = xx.mult(QQ);       // input * weight matrix
        imdb = II.get(0,0);     // output
        return imdb;
    }


}
