package com.example.kcruz.gamenews.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity (indices = {@Index(value = {"_id"}, unique = true)})
public class Player {
    @PrimaryKey
    private int _id;
    private String name;
    private String biografia;
    private String avatar; //url de foto
    private String game;

    @Ignore
    public Player() {
    }

    public Player(int _id, String name, String biography, String avatar, String game) {
        this._id = _id;
        this.name = name;
        this.biografia = biography;
        this.avatar = avatar;
        this.game = game;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
