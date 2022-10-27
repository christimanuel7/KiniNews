package com.example.kininews;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class listBerita {
    String judul,gambar,headline;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
