package com.example.kcruz.gamenews.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.kcruz.gamenews.database.models.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news ORDER BY created_date DESC")
    LiveData<List<News>> getNewsList();

    @Query("SELECT * FROM news WHERE favorite = 1 ORDER BY created_date DESC")
    LiveData<List<News>> getFavoritesList();

    @Query("SELECT * FROM news WHERE game LIKE :game ORDER BY created_date DESC")
    LiveData<List<News>> getNewsFilterByGame(String game);

    @Query("SELECT _id FROM news")
    List<String> getNewsId();

    @Query("SELECT * FROM news WHERE _id LIKE :id")
    LiveData<News> fetchNewsById(String id);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long[] insertAll(News... news);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    int updateAll(News... news);

    @Query("UPDATE news SET favorite = :favorite WHERE _id LIKE :id")
    void updateFavorite(boolean favorite, String id);

    @Query("DELETE FROM news WHERE _id LIKE :id")
    int deleteNewsById(String id);

    @Query("DELETE FROM news")
    int deleteAll();
}
