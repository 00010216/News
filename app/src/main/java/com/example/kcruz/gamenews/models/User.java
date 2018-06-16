package com.example.kcruz.gamenews.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity (indices = {@Index(value = {"_id"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    private int _id;
    private String user;
    private String password;
    @Ignore
    private List<String> favoriteNews;

    @Ignore
    public User() {
    }

    public User(int _id, String user, String password, List<String> favoriteNews) {
        this._id = _id;
        this.user = user;
        this.password = password;
        this.favoriteNews = favoriteNews;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFavoriteNews() {
        return favoriteNews;
    }

    public void setFavoriteNews(List<String> favoriteNews) {
        this.favoriteNews = favoriteNews;
    }
}
