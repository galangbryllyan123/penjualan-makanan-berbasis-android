package com.example.cemilanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    String FullName, Password, RePassword, getFullName, getPass;
    EditText etFullName, etPassword, etRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getFullName = SplashActivity.sh.getString("fullname",null);
        getPass = SplashActivity.sh.getString("password",null);
        etFullName = (EditText) findViewById(R.id.etFullname);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRePassword = (EditText) findViewById(R.id.etRePassword);
        etFullName.setText(getFullName);
        etPassword.setText(getPass);
        etRePassword.setText(getPass);

    }
    public void btnUpdate(View view) {
        FullName = etFullName.getText().toString();
        Password = etPassword.getText().toString();
        RePassword = etRePassword.getText().toString();
        if(Password.length() == 0 && FullName.length() == 0) {
            Toast.makeText(this, "full name and password cannot be empty", Toast.LENGTH_LONG).show();
        }else if(Password.equals(RePassword)) {
            SplashActivity.editor.putString("fullname", FullName);
            SplashActivity.editor.putString("password", Password);
            SplashActivity.editor.commit();
            Intent home = new Intent(getApplicationContext(), MainActivity.class);
            Toast.makeText(this, "Success Update", Toast.LENGTH_LONG).show();
            startActivity(home);
        }else{
            Toast.makeText(this, "Confirm Password does not match", Toast.LENGTH_LONG).show();
        }
    }
}
