package com.example.kcruz.gamenews.daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.kcruz.gamenews.models.Player;
import com.example.kcruz.gamenews.models.User;

import java.util.List;

@Dao
public interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Player... players);

    @Update
    void update(Player... players);

    @Query("SELECT * FROM Player")
    List<Player> getAll(); //devuelve todos los players que se encuentran en la tabla news
}
