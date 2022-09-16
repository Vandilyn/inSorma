package com.example.insorma_kelompok.API;

import com.example.insorma_kelompok.API.Furniture;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("furnitures")
    @Expose
    private List<Furniture> furnitures = null;

    public List<Furniture> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(List<Furniture> furnitures) {
        this.furnitures = furnitures;
    }
}
