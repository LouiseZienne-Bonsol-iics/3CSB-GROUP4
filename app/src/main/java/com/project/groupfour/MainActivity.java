package com.project.groupfour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void userHome(View v){
        Intent i = new Intent(this, UserHome.class);
        startActivity(i);
    }

    public void displaySignup(View v){
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}