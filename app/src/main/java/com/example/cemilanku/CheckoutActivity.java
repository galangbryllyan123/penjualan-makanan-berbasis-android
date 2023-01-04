package com.example.cemilanku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CheckoutActivity extends AppCompatActivity {

    static final String TOTAL_KEY = "Total";
    String getFullname, bayar1, total1;
    TextView Fullname, tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        final TextView Total = (TextView) findViewById(R.id.total);
        final String total = getIntent().getStringExtra(TOTAL_KEY);
        Total.setText(total);

        final EditText Pembayaran = (EditText) findViewById(R.id.jumlah);
        final TextView Kembali = (TextView) findViewById(R.id.kembali);

        getFullname = SplashActivity.sh.getString("fullname", null);
        Fullname = (TextView) findViewById(R.id.name);
        Fullname.setText(getFullname);

        tanggal = (TextView) findViewById(R.id.time);
        String tanggal_sekarang = getCurrentDate();
        tanggal.setText(tanggal_sekarang);

        Pembayaran.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Pembayaran.getText().toString().length() != 0) {
                    String pembayaran = Pembayaran.getText().toString();
                    int total1 = 0, pembayaran1, kembali1 = 0;

                    if (total != null) {
                        total1 = Integer.parseInt(total);
                        pembayaran1 = Integer.parseInt(pembayaran);
                        kembali1 = pembayaran1 - total1;
                    }
                    if (kembali1 < 0) {
                        Kembali.setText("0");
                    } else {
                        String kembali = Integer.toString(kembali1);
                        Kembali.setText(kembali);
                    }
                } else {
                    Kembali.setText("0");
                }
            }
        });

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Menghapus Status login dan kembali ke LoginActivity Activity
                bayar1 = Pembayaran.getText().toString();
                if (bayar1.length() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Input Jumlah Pembayaran Terlebih dahulu", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    total1 = Total.getText().toString();
                    int byr = Integer.parseInt(bayar1);
                    int tot = Integer.parseInt(total1);
                    if (byr < tot){
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Input Jumlah Pembayaran lebih besar sama dengan Total transaksi", Toast.LENGTH_LONG);
                        toast.show();
                    }else {
                        Intent bayar = new Intent(getApplicationContext(), BayarActivity.class);
                        startActivity(bayar);
                    }
                }
            }
        });
    }

    public String getCurrentDate() {
        final Calendar c = Calendar.getInstance();
        int year, month, day;
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DATE);
        return day + "/" + (month + 1) + "/" + year;
    }
}

