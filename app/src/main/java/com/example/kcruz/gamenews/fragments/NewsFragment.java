package com.example.kcruz.gamenews.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kcruz.gamenews.adapters.ImagesAdapter;
import com.example.kcruz.gamenews.adapters.NewsAdapter;
import com.example.kcruz.gamenews.models.Image;
import com.example.kcruz.gamenews.models.News;

import java.util.List;

public class NewsFragment extends Fragment {

    public static final String ARG_ITEM_ID = "tab_images_grid";
    Activity activity;
    NewsAdapter newsAdapter;
    RecyclerView newsView;
    GridLayoutManager lManager;
    List<News> news;

    public NewsFragment() {
    }


}
