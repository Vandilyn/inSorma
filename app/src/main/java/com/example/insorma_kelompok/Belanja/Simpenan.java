package com.example.insorma_kelompok.Belanja;

import android.widget.EditText;
import android.widget.TextView;

public class Simpenan {
    private String gambar;
    private int posisi;

    public Simpenan(String gambar, int posisi) {
        this.gambar = gambar;
        this.posisi = posisi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getPosisi() {
        return posisi;
    }

    public void setPosisi(int posisi) {
        this.posisi = posisi;
    }
}



