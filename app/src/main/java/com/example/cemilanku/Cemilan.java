package com.example.cemilanku;

public class Cemilan {
    private final int gambar;
    private final String judul;
    private final String harga;
    private final String deskripsi;

    static final String GAMBAR_KEY = "Gambar";
    static final String JUDUL_KEY = "Judul";
    static final String HARGA_KEY = "Harga";
    static final String DESK_KEY = "Deskripsi";

    Cemilan(int gambar, String judul, String harga, String deskripsi) {
        this.gambar = gambar;
        this.judul = judul;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    int getGambar() {
        return gambar;
    }

    String getJudul() {
        return judul;
    }

    String getHarga() {
        return harga;
    }

    String getDeskripsi() {
        return deskripsi;
    }
}
