package com.example.kcruz.gamenews.activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.kcruz.gamenews.utils.GameNewsSharedPreferences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.SocketTimeoutException;

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

        GameNewsSharedPreferences.initiate(this); //activa las preferencias

        //verifica el estado si es false no hay token aun, si hay token no pasa por el log in se lo salta
        if(GameNewsSharedPreferences.userIsLoggedIn()) {
            startMainActivity();
            finish();
        }

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
        initiateLogIn();
        /*if(fieldsCheckUp()){
            initiateLogIn(); //solo inicia el login si los datos estan bien ingresados
        }*/
    }

    /*public boolean fieldsCheckUp(){
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        int error = validateCarnet(username);
        switch(error) {
            case 1:
                if (username.equals(password))
                    return true;
                else {
                    Toast.makeText(this, "Password incorrect. Try again", Toast.LENGTH_SHORT).show();
                    return false;
                }
            case 2:
                Toast.makeText(this, R.string.wrong_last_two_digits, Toast.LENGTH_SHORT).show();
                return false;
            case 3:
                Toast.makeText(this, R.string.cannot_be_zero, Toast.LENGTH_SHORT).show();
                return false;
        }
        /*if(validateCarnet(username)){
            if(username.equals(password))
                return true;
            else {
                Toast.makeText(this, "Password incorrect. Try again", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if(error == 4)
            Toast.makeText(this, "Username or password is incorrect.Try again.", Toast.LENGTH_SHORT).show();
            return false;
    }*/

    public void initiateLogIn(){
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
                String[] val = response.body().split(":");
                if(response.isSuccessful() && !response.body().equals("")){
                    if (val[0].equals("token")){
                        GameNewsSharedPreferences.initiate(LogInActivity.this);
                        GameNewsSharedPreferences.setToken(val[1]);
                        startMainActivity();
                        finish();
                    } else Log.d("xd", "onResponse:"+val[1]);
                }else{
                    //Toast.makeText(LogInActivity.this, val[1], Toast.LENGTH_SHORT).show();
                    Log.d("fail", "onResponse:Response vacio ");}
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                // cuando revisa API y no funciona no recibe nada
                if (t instanceof SocketTimeoutException) {
                    Toast.makeText(LogInActivity.this, "Timed out.", Toast.LENGTH_SHORT).show();
                }
                Log.d("fail", "onFailure:NO funciona ");
            }
        });
    }

    public void startMainActivity(){
        //abre actividad que contiene el drawer y el menu de noticias y juegos
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    /*public int validateCarnet(String username){
        String regex = "[0-9]{8}";
        int year;
        if(username.matches(regex)){
            year = Integer.parseInt(username.substring(7,8));
            if(((year >= 65 && year <= 99) || (year>=0 && year <=18 )) &&  (!(username.substring(1,6).equals("000000")))) {
                return 1;
            }else if(!((year >= 65 && year <= 99) || (year>=0 && year <=18 ))){
               // Toast.makeText(this, R.string.wrong_last_two_digits, Toast.LENGTH_SHORT).show();
                return 2;
            }else if(username.substring(1,6).equals("000000")){
                //Toast.makeText(this, R.string.cannot_be_zero, Toast.LENGTH_SHORT).show();
                return 3;
            }
        }
        //Toast.makeText(this, R.string.not_enough_digits, Toast.LENGTH_SHORT).show();
        return 4;
    }*/
}
