package com.example.myapplication.network;



import com.example.myapplication.Model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {



    String BASE_URL = "http://android.tonyrajesh.com/android_sri/task/";

    @GET("new.json")
    Call<Model> getJson();


}
