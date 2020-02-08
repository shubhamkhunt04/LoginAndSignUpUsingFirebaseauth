package com.example.myappl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText user,password;
    Button btnlogin;
    TextView tvregister;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpUIView();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = user.getText().toString();
                String passwords = password.getText().toString();
                firebaseAuth = FirebaseAuth.getInstance();

                if(username.isEmpty())
                {
                    user.setError("Please Enter username");
                }
                else if(passwords.isEmpty())
                {
                    password.setError("Please Enter your password");
                }
                else if(!username.isEmpty() && !passwords.isEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(username, passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, homepage.class));
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "Login Failed !! username and password are wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,register.class));
            }
        });

    }

    private void setUpUIView()
    {
         user = findViewById(R.id.etusername);
         password =  findViewById(R.id.etpassword);
         btnlogin =  findViewById(R.id.tvlogin);
         tvregister = findViewById(R.id.tvregister);
    }
   /* private Boolean validate()
    {
        Boolean result = false;
        String name = user.getText().toString();
        String pass = password.getText().toString();
        if(name.isEmpty() || pass.isEmpty())
        {
            Toast.makeText(this, "Please fill all detailes", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result = true;
        }
        return  result;
    }*/
}

