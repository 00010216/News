package com.example.kcruz.gamenews.models;

import java.util.List;

public class User {
    private int _id;
    private String user;
    private String password;
    private List<String> favoriteNews;

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
