package com.example.cemilanku;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    String Email, Password, str_getID, str_getPass;
    EditText etEmail, etPassword;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        str_getID = SplashActivity.sh.getString("email", null);
        str_getPass = SplashActivity.sh.getString("password", null);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

    }

    public void btnSignIn(View view) {
        // TODO Auto-generated method stub

        Email = etEmail.getText().toString();
        Password = etPassword.getText().toString();

        /* make edittext condition for empty, input etc match */

        if (Email.length() == 0 & Password.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Please enter your login Email and Password",
                    Toast.LENGTH_LONG).show();
        } else if (etEmail.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Please enter your Email", Toast.LENGTH_LONG).show();
        } else if (Password.length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Please enter your Password", Toast.LENGTH_LONG).show();
        }

        else if (str_getID.matches("") && str_getPass.matches("")) {
            Toast.makeText(getApplicationContext(),
                    "Details does not belongs to any account",
                    Toast.LENGTH_LONG).show();
        }

        else if (!(Email.matches(str_getID))) {
            Toast.makeText(getApplicationContext(),
                    "Either login/password is incorrect", Toast.LENGTH_LONG)
                    .show();
        }

        else if (!(str_getPass.matches(Password))) {
            Toast.makeText(getApplicationContext(),
                    "Either login/password is incorrect", Toast.LENGTH_LONG)
                    .show();
        }

        else if ((str_getID.matches(Email)) && (str_getPass.matches(Password))) {
            /*
             * dont forget to commit after doing the operation with shared
             * preference
             */
            /* without commit data will not saved to shared preference */
            SplashActivity.editor.putString("loginTest", "true");
            SplashActivity.editor.commit();

            Toast.makeText(getApplicationContext(),
                    "Success Login", Toast.LENGTH_LONG).show();

            Intent sendToLogout = new Intent(this, MainActivity.class);
            startActivity(sendToLogout);
        }
    }
    public void btnSignUp(View view) {
        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }
}

