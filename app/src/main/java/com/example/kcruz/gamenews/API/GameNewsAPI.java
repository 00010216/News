package com.example.kcruz.gamenews.API;

import com.example.kcruz.gamenews.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

//maneja las rutas
public interface GameNewsAPI {

    @FormUrlEncoded
    @POST("/login")
    Call<Login> token(@Field("user") String user, @Field("password") String password);

    @GET("/news/type/list")
    Call<String[]> getGames();

}
