package com.example.kcruz.gamenews.API;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class TokenDeserializer implements JsonDeserializer<String> {

    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if(json.getAsJsonObject()!= null){
            JsonObject jsonObject = json.getAsJsonObject();//obtenemos el jason del api sabiendo que no es nulo
            if(jsonObject.has("token")){
                return "token:"+ jsonObject.get("token").getAsString();
            }else{
                return "error:" + jsonObject.get("message").getAsString();
            }
        }else return"error: Log in failed";
    }
}
