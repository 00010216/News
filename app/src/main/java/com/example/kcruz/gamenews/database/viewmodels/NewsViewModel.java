package com.example.kcruz.gamenews.database.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.kcruz.gamenews.database.AppDatabase;
import com.example.kcruz.gamenews.database.NewsRepository;
import com.example.kcruz.gamenews.database.models.News;
import com.example.kcruz.gamenews.database.models.Player;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private static NewsRepository repository;
    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = NewsRepository.getInstance(
                AppDatabase.getInstance(application).newsDao(),
                AppDatabase.getInstance(application).playerDao()
        );
    }

    public LiveData<List<News>> getAllNews() {
        return repository.getNewsList();
    }

    public LiveData<List<News>> getFavoritesList() {
        return repository.getFavoritesList();
    }

    public LiveData<List<News>> getNewsByGame(String game) {
        return repository.getNewsByGame(game);
    }

    public LiveData<List<Player>> getPlayersByGame(String game) {
        return repository.getPlayersByGame(game);
    }

    public LiveData<News> fetchNewsById(String id) {
        return repository.fetchNewsById(id);
    }

    public void syncNewsApi(News[] news) {
        repository.insertAllNews(news);
    }


    public void updateFavorite(boolean value, String... favoritesId) {
        repository.updateFavorite(value, favoritesId);
    }

    public void clearDatabase() {
        repository.clearDatabase(getApplication());
    }
}
