package com.example.cemilanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    TextView title;

    private TextView tv;
    private ImageView iv;

    public static String str_login_test;

    public static SharedPreferences sh;
    public static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //animasi
        tv = (TextView)findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        tv.startAnimation(myanim);
        iv.startAnimation(myanim);

        //menghilangkan status bar


        //mengambil id yang ada di activity_splash
        title = (TextView)findViewById(R.id.tv);
        //mengambil font yang ada di folder assets
        Typeface customfont = Typeface.createFromAsset(getAssets(),"fonts/AlegreyaSC-Bold.ttf");
        //set type font tersebut
        title.setTypeface(customfont);


        //penyimpanan share preferences
        sh = getSharedPreferences("myprefe", 0);
        editor = sh.edit();
        str_login_test = sh.getString("loginTest", null);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }

        SplashActivity.editor.putFloat("price", 0);
        SplashActivity.editor.commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*
                 * if user login test is true on oncreate then redirect the user
                 * to result page
                 */

                if (str_login_test != null && !str_login_test.toString().trim().equals("")) {
                    Intent send = new Intent(getApplicationContext(),
                            MainActivity.class);
                    startActivity(send);
                }
                /*
                 * if user login test is false on oncreate then redirect the
                 * user to login & registration page
                 */
                else {

                    Intent send = new Intent(getApplicationContext(),
                            LoginActivity.class);
                    startActivity(send);
                }
            }

        }, 3000);

    }
}

