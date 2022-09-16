package com.example.insorma_kelompok.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insorma_kelompok.API.Furniture;
import com.example.insorma_kelompok.Account.Profile;
import com.example.insorma_kelompok.Adapter.MyRecycleViewAdapter;
import com.example.insorma_kelompok.R;
import com.example.insorma_kelompok.API.RequestInterface;
import com.example.insorma_kelompok.API.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Homepage extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView catalog,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recyclerView = findViewById(R.id.rvFurnitures);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadJSON();

        catalog = findViewById(R.id.CatalogBtn);
        catalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Catalog();
            }
        });

        profile = findViewById(R.id.ProfileBtn);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profiles();
            }
        });
    }

//https://mocki.io/v1/5f379081-2473-4494-9cc3-9e808772dc54
    public void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bit.ly/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
        Call<Response> call = requestInterface.getFurnitures();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                List<Furniture> fList = response.body().getFurnitures();
                MyRecycleViewAdapter adapter = new MyRecycleViewAdapter(Homepage.this, fList);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void Catalog(){
        Intent catalog = new Intent(Homepage.this, Catalog.class);
        startActivity(catalog);
    }

    public void profiles(){
        Intent profile = new Intent(Homepage.this, Profile.class);
        startActivity(profile);
    }
}