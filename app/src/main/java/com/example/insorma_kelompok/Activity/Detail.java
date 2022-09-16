package com.example.insorma_kelompok.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.insorma_kelompok.API.Furniture;
import com.example.insorma_kelompok.Belanja.InfoBarang;
import com.example.insorma_kelompok.R;

import java.util.Vector;

public class Detail extends AppCompatActivity{
    public static final String extra ="extra";
    TextView furnitureName, furnitureDescription, furniturePrice;
    ImageView furnitureImage;
    String nama, desc, gambar, harga;
    Furniture furniture;
    Button addtoCart;
    EditText Jumlah;
    int jumlahBarang,totalHarga,hargaBarang;
    public static int id=0;
    public static Vector<InfoBarang> jajan = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        furnitureName = findViewById(R.id.furnitureName);
        furnitureDescription = findViewById(R.id.furnitureOverview);
        furniturePrice = findViewById(R.id.furniturePrice);
        furnitureImage = findViewById(R.id.furnitureImage);
        addtoCart = findViewById(R.id.furnitureBuyBtn);
        Jumlah = findViewById(R.id.furnitureJumlah);

        furniture = getIntent().getParcelableExtra(extra);

        nama = furniture.getProductName();
        desc = furniture.getDescription();
        gambar = furniture.getImage();
        harga = furniture.getPrice();

        furnitureName.setText(nama);
        furnitureDescription.setText(desc);
        furniturePrice.setText("Price: " + harga + "$/each");
        Glide.with(getApplicationContext())
                .load(gambar)
                .into(furnitureImage);

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jumlahBarang = Integer.parseInt(Jumlah.getText().toString());
                hargaBarang = Integer.parseInt(harga);
                totalHarga = jumlahBarang * hargaBarang;
                if(jumlahBarang < 1){
                    Toast.makeText(Detail.this, "Invalid ammount", Toast.LENGTH_SHORT).show();
                }else{
                    InfoBarang ingfo = new InfoBarang(id,nama,jumlahBarang,hargaBarang,totalHarga,gambar);
                    jajan.add(ingfo);

                    Intent intent = new Intent(Detail.this, Catalog.class);
                    intent.putExtra("gambar",gambar);

                    startActivity(intent);
                }
            }
        });
    }
}