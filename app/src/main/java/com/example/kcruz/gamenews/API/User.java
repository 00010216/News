package com.example.kcruz.gamenews.API;

import java.util.List;

public class User {

    private String _id;
    private String user;
    private String password;
    private String[] favoriteNews;

    public User() {
    }

    public User(String _id, String user, String password, String[] favoriteNews) {
        this._id = _id;
        this.user = user;
        this.password = password;
        this.favoriteNews = favoriteNews;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
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

    public String[] getFavoriteNews() {
        return favoriteNews;
    }

    public void setFavoriteNews(String[] favoriteNews) {
        this.favoriteNews = favoriteNews;
    }
}
