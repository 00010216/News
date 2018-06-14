package com.example.kcruz.gamenews.API;

import com.example.kcruz.gamenews.utils.GameNewsSharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Instancia que maneja las peticiones a la web y recibir una respuesta
public class GamesAPIUtils {
    //pueden ser instanciados sin necesidad de crear un objeto
    private static Retrofit retrofit;
    private static String ENDPOINT = "http://gamenewsuca.herokuapp.com/";
    private static GameNewsAPI gameNewsAPI;

    public static GameNewsAPI getApiInstance(){
        //instancia para llamar la API y manejar la informacion
        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gameNewsAPI = retrofit.create(GameNewsAPI.class);
        return gameNewsAPI;
    }

    public static GameNewsAPI getApiInstanceWithAuthorization() {
        //cuando ocupa el POJO no necesita de un gson
        return getApiInstanceWithAuthorization(new GsonBuilder().create());
    }

    public static GameNewsAPI getApiInstanceWithAuthorization(Gson gson) {
        //cuando ocupa el POJO no necesita de un gson
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request newRequest = chain.request()
                                .newBuilder()
                                .addHeader("Authorization", "Bearer " + GameNewsSharedPreferences.getToken())
                                .build();
                        return chain.proceed(newRequest);
                    }
                }).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gameNewsAPI = retrofit.create(GameNewsAPI.class);
        return gameNewsAPI;
    }
}
