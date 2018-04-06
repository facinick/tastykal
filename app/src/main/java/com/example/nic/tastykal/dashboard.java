package com.example.nic.tastykal;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {

    String username,password;
    Bundle bundle1;
    Fragment frag1;
    Fragment frag2;
    Fragment frag3;
    Fragment active;
    FragmentManager ft;
    boolean doubleBackToExitPressedOnce = false;

    private static final String SELECTED_ITEM = "arg_selected_item";

    private BottomNavigationView mBottomNav;
    private int mSelectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);


        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");


        frag1 = new prediction_frag();
        frag2 = new recommendation_frag();
        frag3 = new addremove_frag();



        bundle1 = new Bundle();
        bundle1.putString("username",username);
        bundle1.putString("password",password);
        active = frag1;
        active.setArguments(bundle1);

        ft = getFragmentManager();


        ft.beginTransaction().add(R.id.container, frag3, frag3.getTag()).commit();

        ft.beginTransaction().add(R.id.container, frag2, frag2.getTag()).commit();
        ft.beginTransaction().add(R.id.container, frag1, frag1.getTag()).commit();


        mBottomNav = (BottomNavigationView) findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        selectFragment(selectedItem);


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        MenuItem homeItem = mBottomNav.getMenu().getItem(0);
        if (mSelectedItem != homeItem.getItemId()) {
            // select home item
            selectFragment(homeItem);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }

    }

    private void selectFragment(MenuItem item) {

        active = frag1;

        ft = getFragmentManager();

        switch (item.getItemId()) {

            case R.id.menu_home:

                ft.beginTransaction().hide(frag2).commit();
                ft.beginTransaction().hide(frag3).commit();
                ft.beginTransaction().show(frag1).commit();
                active = frag1;


                break;
            case R.id.menu_notifications:


                ft.beginTransaction().hide(frag3).commit();
                ft.beginTransaction().hide(frag1).commit();
                ft.beginTransaction().show(frag2).commit();
                active = frag2;

                break;
            case R.id.menu_search:


                ft.beginTransaction().hide(frag2).commit();
                ft.beginTransaction().hide(frag1).commit();
                ft.beginTransaction().show(frag3).commit();
                active = frag3;

                break;
        }

        // update selected item
        mSelectedItem = item.getItemId();

        // uncheck the other items.
        for (int i = 0; i< mBottomNav.getMenu().size(); i++) {
            MenuItem menuItem = mBottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == item.getItemId());
        }

        updateToolbarText(item.getTitle());




    }

    private void updateToolbarText(CharSequence text) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(text);
        }
    }

    private int getColorFromRes(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }
}
