package com.example.cemilanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView cemilanGambar = (ImageView)findViewById(R.id.gambarDetail);
        TextView cemilanJudul = (TextView)findViewById(R.id.judulDetail);
        TextView cemilanHarga = (TextView) findViewById(R.id.hargaDetail);
        TextView cemilanDesk = (TextView) findViewById(R.id.deskripsiDetail);

        Drawable drawable = ContextCompat.getDrawable
                (this,getIntent().getIntExtra(Cemilan.GAMBAR_KEY, 0));

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.GRAY);

        if(drawable!=null) {
            gradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }

        cemilanJudul.setText(getIntent().getStringExtra(Cemilan.JUDUL_KEY));
        cemilanHarga.setText(getIntent().getStringExtra(Cemilan.HARGA_KEY));
        cemilanDesk.setText(getIntent().getStringExtra(Cemilan.DESK_KEY));

        Glide.with(this).load(getIntent().getIntExtra(Cemilan.GAMBAR_KEY,0))
                .placeholder(gradientDrawable).into(cemilanGambar);
    }
}
