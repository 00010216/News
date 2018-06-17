package com.example.kcruz.gamenews.fragments;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kcruz.gamenews.API.GamesAPIUtils;
import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.adapters.NewsAdapter;
import com.example.kcruz.gamenews.database.NewsRepository;
import com.example.kcruz.gamenews.database.models.News;
import com.example.kcruz.gamenews.database.viewmodels.NewsViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment implements NewsAdapter.NewsAdapterClickListener{
    private static final String TAG = NewsFragment.class.getSimpleName();

    public static final String ARG_ITEM_ID = "news_list";
    Activity activity;
    NewsAdapter newsAdapter;
    RecyclerView newsView;
    NewsViewModel newsViewModel;

    public NewsFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //se inicializa el viewmodel
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        //ya obtiene como lista los datos insertados en el call a la base de datos
        newsViewModel.getAllNews().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(@Nullable List<News> news) {
                //envia al adapter los datos insertados en la base de datos como una lista
                newsAdapter.setNews(news);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflamos vista que contiene el recycler
        View view = inflater.inflate(R.layout.fragment_news,container,false);

        //asigna la vista del recyclerview al recycler
        newsView = (RecyclerView) view.findViewById(R.id.news_list);
        newsView.setHasFixedSize(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager managerLand = new GridLayoutManager(container.getContext(),4);
            managerLand.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    if( position == 0 || position%3 == 0){
                        return 4;
                    }else{
                        return 2;
                    }
                }
            });
            setGridLayout(managerLand);
        }else {
            GridLayoutManager managerPort = new GridLayoutManager(container.getContext(),5);
            managerPort.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {

                    if( position == 0 || position%3 == 0){
                        return 5;
                    } else if(position%2 == 0){
                        return 3;
                    }else{
                        return 2;
                    }
                }
            });

            setGridLayout(managerPort);
        }
        //llamar funcion de arreglo con contenido
        setNews();
        Log.d("news", "va entrar a dapter ");
        newsAdapter = new NewsAdapter(getContext(), getResources(),this);
        newsView.setAdapter(newsAdapter);

        return view;
    }


    private void setNews() {
        System.out.println("Entro a set news");
        Call<News[]> news = GamesAPIUtils.getApiInstanceWithAuthorization().getNews();
        news.enqueue(new Callback<News[]>() {
            @Override
            public void onResponse(Call<News[]> call, Response<News[]> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "onResponse: list size " + response.body().length);
                    newsViewModel.syncNewsApi(response.body());
                    //newsAdapter.setNews(response.body());
                }

            }

            @Override
            public void onFailure(Call<News[]> call, Throwable t) {
                Log.d("news", "onFailure: " + t.getMessage());
            }
        });
    }

    public void setGridLayout(GridLayoutManager manager){
        newsView.setLayoutManager(manager);
    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.news);
        super.onResume();
    }

    @Override
    public void onNewsClick(View v, int position) {

    }

}
