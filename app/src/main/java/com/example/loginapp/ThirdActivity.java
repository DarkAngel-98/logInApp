package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView textView ;
    String temp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        textView = (TextView) findViewById(R.id.welcome) ;
        Intent intent = getIntent() ;
        Bundle extras = intent.getExtras();
        if(extras != null){
            temp = extras.getString("userName") ;
            textView.setText("Welcome "+ temp+ "." +" We are so happy to see you!");
        }
        //temp = extras.getString("userName") ;
    }
}