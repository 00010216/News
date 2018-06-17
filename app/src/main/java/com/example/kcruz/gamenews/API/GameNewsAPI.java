package com.example.kcruz.gamenews.API;

import com.example.kcruz.gamenews.database.models.News;
import com.example.kcruz.gamenews.database.models.Player;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

//maneja las rutas
public interface GameNewsAPI {

    @FormUrlEncoded
    @POST("/login")
    Call<Login> token(@Field("user") String user, @Field("password") String password);

    @GET("/news/type/list")
    Call<String[]> getGames();

    @GET("/news")
    Call<News[]> getNews();

    @GET("/players")
    Call<Player[]> getPlayers();

    @GET("/users/detail")
    Call<User> userDetail();

    @FormUrlEncoded
    @PUT("/users/{userId}")
    Call<User> updateUserDetail(@Path("userId") String userId, @Field("user") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/users/{userId}/fav")
    Call<Favorites> setFavorite(@Path("userId") String userId, @Field("new") String newsId);

    @FormUrlEncoded
    @HTTP(method = "DELETE",path = "/users/{userId}/fav", hasBody = true)
    Call<Favorites> removeFavorite(@Path("userId") String userId, @Field("new") String newsId);

}
