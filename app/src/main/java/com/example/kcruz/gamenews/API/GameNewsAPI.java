package com.example.kcruz.gamenews.API;

import com.example.kcruz.gamenews.models.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GameNewsAPI {

    String ENDPOINT = "http://gamenewsuca.herokuapp.com/";


    @FormUrlEncoded
    @POST("/login")
    Call<String> token(@Field("user") String user, @Field("password") String password);

}
