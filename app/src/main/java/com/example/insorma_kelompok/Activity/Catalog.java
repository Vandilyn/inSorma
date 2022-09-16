package com.example.insorma_kelompok.Activity;

import static com.example.insorma_kelompok.Activity.Detail.id;
import static com.example.insorma_kelompok.Activity.Detail.jajan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.insorma_kelompok.API.Furniture;
import com.example.insorma_kelompok.Account.Profile;
import com.example.insorma_kelompok.Belanja.InfoBarang;
import com.example.insorma_kelompok.Belanja.Simpenan;
import com.example.insorma_kelompok.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Vector;

public class Catalog extends AppCompatActivity {
    int p;
    String currentDate;
    TextView Homepage, Profile;
    Vector<Simpenan> temp = new Vector<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        p = jajan.size();

        ListView list = findViewById(R.id.catalogList);
        list.setAdapter(new lists());

        Homepage = findViewById(R.id.HomepageBtn);
        Profile = findViewById(R.id.ProfileBtn);

        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { home();}
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profiles();
            }
        });
        Calendar calendar = Calendar.getInstance();
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
    }

    public class lists extends BaseAdapter {
        @Override
        public int getCount() {
            if (p != 0) {
                return p;
            } else {
                Toast.makeText(Catalog.this, "No Data", Toast.LENGTH_SHORT).show();
                return 0;
            }
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View inflater = getLayoutInflater().inflate(R.layout.catalog_layout, parent, false);
            ImageView gambarbarang = inflater.findViewById(R.id.GambarBarang);
            TextView idbarang = inflater.findViewById(R.id.IDBarang);
            TextView namabarang = inflater.findViewById(R.id.namaBarang);
            TextView jumlahbarang = inflater.findViewById(R.id.jumlahBarang);
            TextView hargabarang = inflater.findViewById(R.id.hargaBarang);
            TextView totalbarang = inflater.findViewById(R.id.totalHarga);
            TextView tanggal = inflater.findViewById(R.id.tanggal);

            Glide.with(getApplicationContext())
                    .load(jajan.get(position).getGambarBarang())
                    .into(gambarbarang);

            namabarang.setText(jajan.get(position).getNamaBarang());
            idbarang.setText(String.valueOf(position+1));
            jumlahbarang.setText("Quantity: " + jajan.get(position).getJumlahBarang());
            hargabarang.setText("Price: " + jajan.get(position).getHargaBarang() + "/each");
            totalbarang.setText("Total Price: " + jajan.get(position).getTotal() + "$");
            tanggal.setText(currentDate);

            return inflater;
        }
    }
    public void home(){
        Intent home = new Intent(Catalog.this, Homepage.class);
        startActivity(home);
    }

    public void profiles(){
        Intent profile = new Intent(Catalog.this, com.example.insorma_kelompok.Account.Profile.class);
        startActivity(profile);
    }
}