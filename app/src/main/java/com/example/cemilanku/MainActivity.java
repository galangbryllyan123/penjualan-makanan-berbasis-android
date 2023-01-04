package com.example.cemilanku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String getFullname ,total2;
    //Member variables
    private RecyclerView mRecyclerView;
    private ArrayList<Cemilan> mCemilan;
    private CemilanAdapter mAdapter;
    private TextView total;

    static final String GAMBAR_KEY = "Gambar";
    static final String JUDUL_KEY = "Judul";
    static final String HARGA_KEY = "Harga";
    static final String DESK_KEY = "Deskripsi";
    static final String TOTAL_KEY = "Total";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFullname = SplashActivity.sh.getString("fullname",null);
        getSupportActionBar().setSubtitle("Haii, "+getFullname);

        total = (TextView) findViewById(R.id.total);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mCemilan = new ArrayList<>();

        mAdapter = new CemilanAdapter(this, mCemilan);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();
    }
    private void initializeData() {
        TypedArray cemilanGambar = getResources().obtainTypedArray(R.array.cemilan_images);
        String[] cemilanJudul = getResources().getStringArray(R.array.cemilan_titles);
        String[] cemilanHarga = getResources().getStringArray(R.array.cemilan_harga);
        String[] cemilanDeskripsi = getResources().getStringArray(R.array.detail_cemilan);

        for (int i = 0; i < cemilanJudul.length; i++) {
            mCemilan.add(new Cemilan(cemilanGambar.getResourceId(i, 0),
                    cemilanJudul[i], cemilanHarga[i], cemilanDeskripsi[i]));
        }
        cemilanGambar.recycle();
    }

    public void hitung(View view) {
        TextView tvHarga = (TextView) view.findViewById(R.id.harga);
        String awal = total.getText().toString();
        String sHarga = tvHarga.getText().toString();
        int a, b, jml;
        a = Integer.parseInt(awal);
        b = Integer.parseInt(sHarga);
        jml = a + b;
        String sJml = Integer.toString(jml);
        total.setText(sJml);
        Toast.makeText(getApplicationContext(), "Success add to cart", Toast.LENGTH_SHORT).show();
    }

    public void detail(View view) {
        int position = 0;
        TextView tvJudul = (TextView) view.findViewById(R.id.judul);
        String sJudul = tvJudul.getText().toString();
        String[] cemilanJudul = getResources().getStringArray(R.array.cemilan_titles);
        for (int i = 0; i < cemilanJudul.length; i++) {
            if (cemilanJudul[i].equals(sJudul)) {
                position = i;
            }
        }
        Cemilan cemilan = mCemilan.get(position);

        Intent detailintent = new Intent(this, DetailActivity.class);
        detailintent.putExtra(GAMBAR_KEY, cemilan.getGambar());
        detailintent.putExtra(JUDUL_KEY, cemilan.getJudul());
        detailintent.putExtra(HARGA_KEY, cemilan.getHarga());
        detailintent.putExtra(DESK_KEY, cemilan.getDeskripsi());
        startActivity(detailintent);
    }


    //inisialisi menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //fungsi untuk aksi menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String phoneNo = "089654445555";
        // Handle app bar item clicks here. The app bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_callcenter:
                if(!TextUtils.isEmpty(phoneNo)) {
                    String dial = "tel:" + phoneNo;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
                }else {
                    Toast.makeText(MainActivity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_sms:
                if(!TextUtils.isEmpty(phoneNo)) {
                    Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNo));
                    startActivity(smsIntent);
                }
                return true;
            case R.id.action_lokasi:
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=-6.982389, 110.409000(Semarang)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                return true;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(false);
                builder.setMessage("Do you want to Logout?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        Toast.makeText(getApplicationContext(), "You have successfully logout",
                                Toast.LENGTH_LONG).show();
                        SplashActivity.editor.remove("loginTest");
                        SplashActivity.editor.commit();
                        Intent sendToLoginandRegistration = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(sendToLoginandRegistration);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            case R.id.action_update:
                Intent update = new Intent(MainActivity.this,UpdateActivity.class);
                startActivity(update);
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void checkout(View view) {
        total2 = total.getText().toString();
        int Total = Integer.parseInt(total2);
        if(Total == 0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Pilih Cemilannya dulu bro :)", Toast.LENGTH_LONG);
            toast.show();
        }else{
            Intent bayar = new Intent(getApplicationContext(), CheckoutActivity.class);
            bayar.putExtra(TOTAL_KEY, total.getText().toString());
            startActivity(bayar);
        }
    }
}


