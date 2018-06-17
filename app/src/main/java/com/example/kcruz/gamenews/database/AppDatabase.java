package com.example.kcruz.gamenews.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.example.kcruz.gamenews.database.daos.NewsDao;
import com.example.kcruz.gamenews.database.daos.PlayerDao;
import com.example.kcruz.gamenews.database.models.News;
import com.example.kcruz.gamenews.database.models.Player;

@Database(entities = {News.class, Player.class}, version = 1)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = AppDatabase.class.getSimpleName();
    private static AppDatabase sInstance;

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "game_news";

    public abstract NewsDao newsDao();
    public abstract PlayerDao playerDao();

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                        .build();
                Log.d(TAG, "getInstance: made new database instance");
            }
        }
        return sInstance;
    }
}