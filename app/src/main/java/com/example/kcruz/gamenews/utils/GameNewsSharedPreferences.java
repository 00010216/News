package com.example.kcruz.gamenews.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.kcruz.gamenews.API.User;

public class GameNewsSharedPreferences {
    private static final String SHARED_PREFS_NAME = "NEWS_APP";

    public static Context context;
    public static SharedPreferences settings;
    //modifica los valores de SharedPreferences
    public static SharedPreferences.Editor editor; //garantiza que los valores de preferencia permanezcan constantes

    private static final String KEY_TOKEN = "TOKEN";
    private static final String KEY_LOGGED_IN = "LOGGED_IN";
    private static final String KEY_GAMES = "GAMES";
    private static final String KEY_USER_ID = "KEY_USER_ID";
    private static final String KEY_USERNAME = "KEY_USERNAME";

   public static void initiate(Context context){
       //se ve si no hay algo guardado en preferences
       if(settings == null){
           settings = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
           editor = settings.edit(); //crea la preferencia con el nombre de SHARED_PREFS_NAME
       }

   }

    public  static void setToken(String token){
       //decimos que el usuario esta logged y por eso se asigna el token
       editor.putBoolean(KEY_LOGGED_IN,true);
       editor.putString(KEY_TOKEN,token);
       editor.commit(); //cambia del editor al SharedPreferences object
    }

    public static String getToken(){
       return settings.getString(KEY_TOKEN, null);
    }

    public static boolean userIsLoggedIn() {
       return settings.getBoolean(KEY_LOGGED_IN, false);
    }

    public static void logOut(){
       editor.clear();
       editor.commit();
    }

    public static void setGames(String games[]) {
        String gamesList = TextUtils.join(",", games);
        editor.putString(KEY_GAMES, gamesList);
        editor.commit();
    }

    public static String[] getGames() {
        return settings.getString(KEY_GAMES, "").split(",");
    }

    public static boolean hasGames() {
        return !settings.getString(KEY_GAMES, "").isEmpty();
    }

    public static void setUserDetail(User user) {
        editor.putString(KEY_USER_ID, user.get_id());
        editor.putString(KEY_USERNAME, user.getUser());
        editor.commit();
    }

    public static String getUserId() {
        return  settings.getString(KEY_USER_ID, "");
    }

    public static String getUsername() {
        return settings.getString(KEY_USERNAME, "");
    }
}

