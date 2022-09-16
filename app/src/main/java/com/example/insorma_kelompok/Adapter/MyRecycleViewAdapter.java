package com.example.insorma_kelompok.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.insorma_kelompok.API.Furniture;
import com.example.insorma_kelompok.Activity.Catalog;
import com.example.insorma_kelompok.Activity.Detail;
import com.example.insorma_kelompok.R;

import java.util.List;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

    private Context ctx;
    private List<Furniture> furnitureList;

    public MyRecycleViewAdapter(Context ctx, List<Furniture> furnitureList) {
        this.ctx = ctx;
        this.furnitureList = furnitureList;
    }

    @NonNull
    @Override
    public MyRecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.furniture_item, parent, false);

        MyRecycleViewAdapter.ViewHolder viewHolder = new MyRecycleViewAdapter.ViewHolder(view);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(), Detail.class);
                Furniture furniture = new Furniture();
                furniture.setProductName(furnitureList.get(viewHolder.getAdapterPosition()).getProductName());
                furniture.setPrice(furnitureList.get(viewHolder.getAdapterPosition()).getPrice());
                furniture.setDescription(furnitureList.get(viewHolder.getAdapterPosition()).getDescription());
                furniture.setImage(furnitureList.get(viewHolder.getAdapterPosition()).getImage());
                intent.putExtra(Detail.extra, furniture);
                parent.getContext().startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecycleViewAdapter.ViewHolder holder, int position) {
        holder.furnitureName.setText(furnitureList.get(position).getProductName());
        holder.furnitureOverview.setText(furnitureList.get(position).getDescription());
        holder.furnitureRating.setText("Rating: " + furnitureList.get(position).getRating() + "/5");
        holder.furniturePrice.setText("Price: " + furnitureList.get(position).getPrice() + "$/each");
        Glide.with(ctx).load(furnitureList.get(position).getImage()).into(holder.furnitureImage);
    }

    @Override
    public int getItemCount() {
        return furnitureList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView furnitureImage;
        TextView furnitureName, furnitureOverview, furnitureRating, furniturePrice;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            furnitureName = itemView.findViewById(R.id.FurnitureName);
            furnitureOverview = itemView.findViewById(R.id.FurnitureOverview);
            furniturePrice = itemView.findViewById(R.id.FurniturePrice);
            furnitureRating = itemView.findViewById(R.id.FurnitureRating);
            furnitureImage = itemView.findViewById(R.id.FurnitureImage);
            linearLayout = itemView.findViewById(R.id.detailFurniture);
        }
    }
}
