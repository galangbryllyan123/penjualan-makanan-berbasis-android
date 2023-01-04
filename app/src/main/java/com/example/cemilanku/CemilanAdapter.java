package com.example.cemilanku;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CemilanAdapter extends RecyclerView.Adapter<CemilanAdapter.CemilanViewHolder> {
    private GradientDrawable mGradientDrawable;
    private ArrayList<Cemilan> mCemilan;
    private Context mContext;

    CemilanAdapter(Context context, ArrayList<Cemilan> cemilan) {
        this.mCemilan = cemilan;
        this.mContext = context;

        mGradientDrawable = new GradientDrawable();
        mGradientDrawable.setColor(Color.GRAY);

        Drawable drawable = ContextCompat.getDrawable
                (mContext, R.drawable.parfrum);
        if (drawable != null) {
            mGradientDrawable.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }

    @Override
    public CemilanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CemilanViewHolder(mContext, LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false), mGradientDrawable);
    }

    public void onBindViewHolder(CemilanViewHolder holder, int position) {
        Cemilan currentCemilan = mCemilan.get(position);

        holder.bindTo(currentCemilan);
    }

    @Override
    public int getItemCount() {
        return mCemilan.size();
    }

    static class CemilanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mGambar;
        private TextView mJudul;
        private TextView mHarga;
        private Context mContext;
        private Cemilan mCurrentCemilan;
        private GradientDrawable mGradientDrawable;

        CemilanViewHolder(Context context, View itemView, GradientDrawable gradientDrawable) {
            super(itemView);

            mGambar = (ImageView) itemView.findViewById(R.id.gambar);
            mJudul = (TextView) itemView.findViewById(R.id.judul);
            mHarga = (TextView) itemView.findViewById(R.id.harga);

            mContext = context;
            mGradientDrawable = gradientDrawable;

            itemView.setOnClickListener(this);
        }

        void bindTo(Cemilan currentCemilan) {
            mJudul.setText(currentCemilan.getJudul());
            mHarga.setText(currentCemilan.getHarga());

            mCurrentCemilan = currentCemilan;

            Glide.with(mContext).load(currentCemilan.
                    getGambar()).placeholder(mGradientDrawable).into(mGambar);
        }

        @Override
        public void onClick(View view) {
        }
    }
}