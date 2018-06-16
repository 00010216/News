package com.example.kcruz.gamenews.models;

import java.util.Date;

public class News {
    private int _id;
    private String title;
    private String coverImage;
    private String create_date;
    private String description;
    private String body;
    private String game;
    private int _v;

    public News() {

    }

    public News(int _id, String title, String coverImage, String create_date, String description, String body, String game, int _v) {
        this._id = _id;
        this.title = title;
        this.coverImage = coverImage;
        this.create_date = create_date;
        this.description = description;
        this.body = body;
        this.game = game;
        this._v = _v;
    }

    public int get_v() {
        return _v;
    }

    public void set_v(int _v) {
        this._v = _v;
    }
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
