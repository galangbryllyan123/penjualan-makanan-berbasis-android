package com.example.cemilanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    String Email, Password, RePassword, FullName;
    EditText etEmail, etFullName, etPassword, etRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etFullName = (EditText) findViewById(R.id.etFullname);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRePassword = (EditText) findViewById(R.id.etRePassword);

    }

    public void btnSignUp(View view) {
        // TODO Auto-generated method stub
        Email = etEmail.getText().toString();
        FullName = etFullName.getText().toString();
        Password = etPassword.getText().toString();
        RePassword = etRePassword.getText().toString();

        if (Email.length() == 0 & Password.length() == 0
                & RePassword.length() == 0 & FullName.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "All fields are mandatory to fill", Toast.LENGTH_LONG).show();
        } else if (Email.length() == 0) {
            Toast.makeText(getApplicationContext(), "Please input your Email",
                    Toast.LENGTH_LONG).show();
        } else if (FullName.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Please input your Full Name", Toast.LENGTH_LONG).show();
        } else if (Password.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Please input your Password", Toast.LENGTH_LONG).show();
        } else if (RePassword.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Please input your RePassword", Toast.LENGTH_LONG).show();
        } else if (Password.equals(RePassword))  {
            SplashActivity.editor.putString("email", Email);
            SplashActivity.editor.putString("fullname", FullName);
            SplashActivity.editor.putString("password", RePassword);
            SplashActivity.editor.commit();

            Intent sendtoLogin = new Intent(getApplicationContext(),
                    LoginActivity.class);

            Toast.makeText(getApplicationContext(),
                    "You have successfully registered", Toast.LENGTH_LONG)
                    .show();

            startActivity(sendtoLogin);
        } else {
            Toast.makeText(getApplicationContext(), "Confirm Password does not match", Toast.LENGTH_LONG).show();
        }
    }

    public void btnSignIn(View view) {
        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
