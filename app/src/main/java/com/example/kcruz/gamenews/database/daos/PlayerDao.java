package com.example.kcruz.gamenews.database.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.kcruz.gamenews.database.models.Player;

import java.util.List;

@Dao
public interface PlayerDao {
    @Query("SELECT * FROM player WHERE game LIKE :game")
    LiveData<List<Player>> getPlayersByGame(String game);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlayers(Player... players);
}
