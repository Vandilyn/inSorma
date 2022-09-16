package com.example.insorma_kelompok.API;

import com.example.insorma_kelompok.API.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestInterface {
    @GET("InSOrmaJSON")
    Call<Response> getFurnitures();
}
