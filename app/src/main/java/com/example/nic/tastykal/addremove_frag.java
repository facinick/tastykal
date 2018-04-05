package com.example.nic.tastykal;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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

        WebView imdb = (WebView) v.findViewById(R.id.imdb);

        imdb.getSettings().setDomStorageEnabled(true);

        imdb.getSettings().setJavaScriptEnabled(true);

        imdb.getSettings().setLoadsImagesAutomatically(true);

        imdb.loadUrl("https://www.themoviedb.org/movie");



     return v;

    }
}



