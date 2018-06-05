package com.example.kcruz.gamenews.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kcruz.gamenews.API.GameNewsAPI;
import com.example.kcruz.gamenews.API.TokenDeserializer;
import com.example.kcruz.gamenews.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_log_in);

        //asignando vistas a cada objeto
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogIn = findViewById(R.id.btn_log_in);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationForLogIn();
            }
        });

    }

    public void verificationForLogIn(){
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenDeserializer()).create();
        Retrofit.Builder retroBuilder = new Retrofit.Builder().baseUrl(GameNewsAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = retroBuilder.build();//ya esta listo para trabajar con el
        GameNewsAPI gameNewsAPI = retrofit.create(GameNewsAPI.class);
        //recibe como parametro el texto de los edit text
        Call<String> token = gameNewsAPI.token(edtUsername.getText().toString(),edtPassword.getText().toString()); // call de debe cambiar por single
        token.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //responde pero no garantiza que estara completamente bueno
                if(response.isSuccessful() && !response.body().equals("")){
                    startMainActivity();
                }else   Log.d("fail", "onResponse:Response vacio ");;
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // cuando revisa API y no funciona no recibe nada
                Log.d("fail", "onFailure:NO funciona ");
            }
        });
    }

    public void startMainActivity(){
        //abre actividad que contiene el drawer y el menu de noticias y juegos
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
