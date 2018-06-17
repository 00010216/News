package com.example.kcruz.gamenews.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.kcruz.gamenews.database.daos.NewsDao;
import com.example.kcruz.gamenews.database.daos.PlayerDao;
import com.example.kcruz.gamenews.database.models.News;
import com.example.kcruz.gamenews.database.models.Player;

import java.util.List;

public class NewsRepository {
    //se establecen os hilos quevan a trabajar ciertas actividades en segundo plano los cuales utilizara el viewmodel
    private static final String TAG = NewsRepository.class.getSimpleName();
    private static NewsRepository sInstance;

    private static final Object LOCK = new Object();

    private NewsDao newsDao;
    private PlayerDao playerDao;

    private NewsRepository(NewsDao newsDao, PlayerDao playerDao) {
        this.newsDao = newsDao;
        this.playerDao = playerDao;
    }

    public static NewsRepository getInstance(NewsDao newsDao, PlayerDao playerDao) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new NewsRepository(newsDao, playerDao);
            }
        }
        return sInstance;
    }

    public void insertAllNews(News[] news) {
        new SyncNewsApiTask(newsDao).execute(news);
    }

    public void insertAllPlayers(Player[] players) {
        new  InsertPlayersTask(playerDao).execute(players);
    }

    //Estos metodos el viewmodel los llamara para obtener los resultados de las query
    public LiveData<List<News>> getNewsList() {
        return newsDao.getNewsList();
    }

    public LiveData<List<News>> getFavoritesList() {
        return newsDao.getFavoritesList();
    }

    public LiveData<List<News>> getNewsByGame(String game) {
        return newsDao.getNewsFilterByGame(game);
    }

    public LiveData<News> fetchNewsById(String id) {
        return newsDao.fetchNewsById(id);
    }

    public void updateFavorite(boolean value, String... favoritesId) {
        new UpdateFavoritesTask(newsDao, value).execute(favoritesId);
    }

    public void clearDatabase(Context context) {
        new ClearDatabaseTask().execute(context);
    }

    public LiveData<List<Player>> getPlayersByGame(String game) {
        return playerDao.getPlayersByGame(game);
    }

    private static class SyncNewsApiTask extends AsyncTask<News, Void, Long[]> {
        NewsDao newsDao;

        SyncNewsApiTask(NewsDao newsDao) {
            this.newsDao = newsDao;
        }

        @Override
        protected Long[] doInBackground(News... lists) {
            Log.d(TAG, "doInBackground: lists length " + lists.length);
            List<String> newsId = newsDao.getNewsId();
            int i = 0;
            for (News news : lists) {
                if (newsId.contains(news.get_id())) {
                    newsId.remove(news.get_id());
                }
            }
            for (String id: newsId) {
                newsDao.deleteNewsById(id);
                i++;
            }
            Log.d(TAG, "doInBackground: removed accumulated " + i);
            int updated = newsDao.updateAll(lists);
            Log.d(TAG, "doInBackground: updated news " + updated);
            return newsDao.insertAll(lists);
        }
    }

    private static class ClearDatabaseTask extends AsyncTask<Context, Void, Void> {
        @Override
        protected Void doInBackground(Context... contexts) {
            AppDatabase.getInstance(contexts[0]).clearAllTables();
            return null;
        }
    }

    private static class UpdateFavoritesTask extends AsyncTask<String, Void, Void> {
        NewsDao newsDao;
        boolean value;

        UpdateFavoritesTask(NewsDao newsDao, boolean value) {
            this.newsDao = newsDao;
            this.value = value;
        }

        @Override
        protected Void doInBackground(String... favoritesId) {
            for (String favorite: favoritesId) {
                newsDao.updateFavorite(value, favorite);
            }
            return null;
        }
    }

    //Se crea un hilo para cada insert, primero recibe los parametros los demas son void pq no se ocupa ningun refresh
    private static class InsertPlayersTask extends AsyncTask<Player, Void, Void> {
        PlayerDao playerDao;

        public InsertPlayersTask(PlayerDao playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.insertPlayers(players);
            return null;
        }
    }
}