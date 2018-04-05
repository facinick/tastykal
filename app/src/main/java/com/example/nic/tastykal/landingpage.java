package com.example.nic.tastykal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class landingpage extends Activity {


    EditText username;
    EditText password;
    private Pattern pattern;
    private Matcher matcher;
    usersdatabase handle;
    TextView quote;
    ArrayList<User> list;




    String[] quotes = new String[] {"Frankly, my dear, I don't give a damn. - Gone with the Wind", "I'm gonna make him an offer he can't refuse. - The Godfather","Here's looking at you, kid. - Casablanca","I love the smell of napalm in the morning. - Apocalypse Now", "I am big! It's the pictures that got small. - Sunset Boulevard", "A boy's best friend is his mother. - Psycho", "As God is my witness, I'll never be hungry again. - Gone with the Wind","Get your stinking paws off me, you damned dirty ape. - Planet of the Apes", "I feel the need—the need for speed! - Top Gun", "Life is a banquet, and most poor suckers are starving to death! - Auntie Mame", "There's no crying in baseball! - A League of Their Own", "Bond. James Bond. - Dr no", "Made it, Ma! Top of the world! - White Heat", "Love means never having to say you're sorry. - Love Story","Greed, for lack of a better word, is good. - Wall Street", "Dont be afraid to catch feels, bacchi! - shriyans kapoor", "May the force be with you. - masoom bhansali", "pawan you bastard! - Saloni suchdev", "what you're gonna do with that big fat butt? wiggle wiggle! - jason derulo" , "I'm the king of the world! - TITANIC", "I want you to hit me as hard as you can. - Fight Club", "I am Jacks complete lack of surprise! - Fight Club", "We rob banks. - BONNIE AND CLYDE", "Hasta la vista, baby. - Terminator 2", "Nobody puts Baby in a corner. - Dirty Dancing" };
    Handler handler = new Handler();
    int delay = 10000; //milliseconds
    String usernamevalidationregex = "^[a-z0-9_-]{5,15}$";
    String passwordvalidationregex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=])(?=\\S+$).{5,15}$";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        quote = (TextView) findViewById(R.id.quote);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        handle = new usersdatabase(this);
        final Random r = new Random();
        quote.setText(quotes[r.nextInt(quotes.length)]);

        handler.postDelayed(new Runnable(){
            public void run(){
                quote.setText(quotes[r.nextInt(quotes.length)]);
                handler.postDelayed(this, delay);
            }
        }, delay);


    }

    public void signup(View view)
    {
        String un = username.getText().toString();
        String pw = password.getText().toString();

        if( validate(un) && validate2(pw)) {


            User user = new User(un, pw);


            list = (ArrayList) handle.getalluser();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).username.equals(user.username)) {
                    Toast.makeText(getApplicationContext(), "username exists", Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            handle.adduser(user);
            Toast.makeText(getApplicationContext(), "username regisitered successfully", Toast.LENGTH_SHORT).show();
        }
        else if (( validate(un) == false) && (validate2(pw) == true))
            Toast.makeText(getApplicationContext(), "USERNAME : 5-15 lowercase characters needed. only special characters allowed are '_' and '-' ", Toast.LENGTH_SHORT).show();

        else if(( validate(un) == true) && (validate2(pw) == false))
        {
            Toast.makeText(getApplicationContext(), "PASSWORD : 5-15 alphanumeric characters needed. atleast one special character is must. ", Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(getApplicationContext(), "username and password no not meet security standards.", Toast.LENGTH_SHORT).show();

        }

    }
    public void signin(View view)
    {
        String un = username.getText().toString();
        String pw = password.getText().toString();

        un = "userhu";
        pw = "abc123@";
        User user = new User(un,pw);

        int check = 0;

        list = (ArrayList) handle.getalluser();
        for( int i=0; i<list.size(); i++ )
        {
            if(list.get(i).username.equals(user.username) && list.get(i).password.equals(user.password)  )
            {
                check++;

                Intent myIntent = new Intent(landingpage.this, dashboard.class);
                myIntent.putExtra("username", user.username); //Optional parameters
                myIntent.putExtra("password", user.password);

                landingpage.this.startActivity(myIntent);
                finish();

            }

        }
        if( check == 0 ) {
            Toast.makeText(getApplicationContext(), "invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }


    public void toaster(String message)
    {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.popuptoast,
                (ViewGroup) findViewById(R.id.toast_layout_root));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.android);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    public boolean validate(final String username){
        pattern = Pattern.compile(usernamevalidationregex);
        matcher = pattern.matcher(username);
        return matcher.matches();

    }
    public boolean validate2(final String password){
        pattern = Pattern.compile(passwordvalidationregex);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }



}