package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    EditText username1, password1 ;
    Button logInBtn1 ;
    DBHelper DB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        DB = new DBHelper(this) ;

        username1 = (EditText) findViewById(R.id.username1) ;
        password1 = (EditText) findViewById(R.id.password1) ;
        logInBtn1 = (Button) findViewById(R.id.LogInBtn1) ;

        logInBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username1.getText().toString() ;
                String pass = password1.getText().toString() ;
                //String temp = user ;

                if(user.equals("") || pass.equals("")) {
                    Toast.makeText(SecondActivity.this, "Enter All The Fields", Toast.LENGTH_SHORT).show();
                }
                else{
                        Boolean checkUsersPass = DB.checkUsersPassword(user, pass) ;
                        if(checkUsersPass == true) {
                            Toast.makeText(SecondActivity.this, "Log In Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), ThirdActivity.class) ;
                            intent.putExtra("userName",user) ;
                            startActivity(intent) ;
                        }
                        else{
                            Toast.makeText(SecondActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

        }
    }