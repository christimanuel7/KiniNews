package com.example.kininews;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolder(View itemView){
        super(itemView);
        mView=itemView;
    }

    public void setDetails(Context ctx, String judul, String gambar, String headline){
        //Views
        TextView judulBerita=mView.findViewById(R.id.judulBerita);
        ImageView gambarBerita=mView.findViewById(R.id.imgBerita);
        TextView headlineBerita=mView.findViewById(R.id.headlineBerita);

        //SET DATA VIEWS
        judulBerita.setText(judul);
        headlineBerita.setText(headline);
        Picasso.get().load(gambar).into(gambarBerita);
    }
}
