package com.example.kcruz.gamenews.database.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "news")
public class News {
    @NonNull
    @PrimaryKey
    private String _id;

    private String title;
    private String body;
    private String game;
    private String coverImage;
    private String description;

    @NonNull
    public String get_id() {
        return _id;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void set_id(@NonNull String _id) {
        this._id = _id;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    private Date created_date;
    private boolean favorite;

    public String getTitle() {
        return title == null ? "" : title.trim();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body == null ? "" : body.trim();
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getGame() {
        return game == null ? "" : game.trim();
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getCoverImage() {
        return coverImage == null ? "" : coverImage.trim();
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getDescription() {
        return description == null ? "" : description.trim();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(Date created_date) {
        this.created_date = created_date;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}