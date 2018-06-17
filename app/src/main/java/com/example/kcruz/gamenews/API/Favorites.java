package com.example.kcruz.gamenews.API;

import com.example.kcruz.gamenews.database.models.News;

public class Favorites {
    private boolean success;
    private News add;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
