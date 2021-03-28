package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword ;
    Button logInBtn, signInBtn ;
    DBHelper DB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DB = new DBHelper(this) ;

        username = (EditText) findViewById(R.id.username) ;
        password = (EditText) findViewById(R.id.password) ;
        repassword = (EditText) findViewById(R.id.repassword) ;

        logInBtn = (Button) findViewById(R.id.logInBtn) ;
        signInBtn = (Button) findViewById(R.id.SignInBtn) ;

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString() ;
                String pass = password.getText().toString() ;
                String repass = repassword.getText().toString() ;

                if(user.equals("") || pass.equals("") || repass.equals("")){
                    Toast.makeText(MainActivity.this, "You must enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean checkUser = DB.checkUsername(user) ;
                        if(checkUser == false){
                            Boolean insert = DB.insertData(user, pass) ;
                            if(insert == true){
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), ThirdActivity.class) ;
                                intent.putExtra("userName", user) ;
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "User Already Exists. Please Log In", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Passwords Not Matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class) ;
                startActivity(intent);
            }
        });

    }
}