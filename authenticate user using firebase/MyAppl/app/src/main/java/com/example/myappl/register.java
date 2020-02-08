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

public class register extends AppCompatActivity {

    EditText user,email,password,cnfpassword;
    Button btnregister;
    TextView tvlogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpUIView();


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emails = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                firebaseAuth = FirebaseAuth.getInstance();
               if(emails.isEmpty())
               {
                   email.setError("Please Enter your Email id");
                   email.requestFocus();
               }
               else if(pass.isEmpty())
               {
                   email.setError("Please Enter your password");
                   email.requestFocus();
               }
               else
               {
                    firebaseAuth.createUserWithEmailAndPassword(emails,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(register.this, "SignUp Failed", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(register.this, "Login succesfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(register.this, homepage.class));
                            }
                        }
                    });
                }
              /*  else
                {
                    Toast.makeText(register.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(register.this,MainActivity.class));
            }
        });

    }
    private void setUpUIView()
    {
        user = findViewById(R.id.etusername);
        email = findViewById(R.id.etemail);
        password =  findViewById(R.id.etpassword);
        cnfpassword = findViewById(R.id.etcnfpassword);
        btnregister = findViewById(R.id.btnregister);
        tvlogin = findViewById(R.id.tvlogin);
    }
}
