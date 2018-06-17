package com.example.kcruz.gamenews.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Player {
    @NonNull
    @PrimaryKey
    private String id;

    private String name;
    private String game;

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    private String biografia;
    private String avatar;

    public String getBiografia() {
        return biografia;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getBiography() {
        return biografia;
    }

    public void setBiography(String biography) {
        this.biografia = biography;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}