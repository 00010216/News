package com.example.kcruz.gamenews.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.kcruz.gamenews.models.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News...news);

    @Update
    void update(News...news);

    @Query("SELECT * FROM News")
    List<News> getAll(); //devuelve todos los news que se encuentran en la tabla news

}
