package com.example.kcruz.gamenews.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favorites_per_user",primaryKeys = {"news_id","news_id"},
        foreignKeys = {
                @ForeignKey
                        (entity = User.class,
                                parentColumns = "_id",
                                childColumns = "user_id",
                                onDelete = ForeignKey.CASCADE,
                                onUpdate = ForeignKey.CASCADE),
                @ForeignKey
                        (entity = User.class,
                                parentColumns = "_id",
                                childColumns = "news_id",
                                onDelete = ForeignKey.CASCADE,
                                onUpdate = ForeignKey.CASCADE)})
public class FavoritesperUser {
    @NonNull
    private int user_id;
    @NonNull
    private int news_id;

    public FavoritesperUser(@NonNull int news_id, @NonNull int user_id) {
        this.news_id = news_id;
        this.user_id = user_id;
    }
}
