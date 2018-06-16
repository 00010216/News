package com.example.kcruz.gamenews.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.kcruz.gamenews.models.FavoritesperUser;

import java.util.List;

@Dao
public interface FavoritesperUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<FavoritesperUserDao> FavoritesperUser);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(FavoritesperUser favorite);

    //@Query("SELECT * FROM News INNER JOIN favorites_per_user ON News._id=favorites_per_user.news_id WHERE favorites_per_user.user_id=:user_id")
    //LiveData<List<News>> getFavoritesByUser(String user_id);

    //@Query("SELECT news_id FROM favorites_per_user WHERE user_id = :user_id")
    //LiveData<List<String>> getFavNewsId(String user_id);
}
