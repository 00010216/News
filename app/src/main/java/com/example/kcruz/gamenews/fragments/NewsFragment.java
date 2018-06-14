package com.example.kcruz.gamenews.fragments;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.adapters.ImagesAdapter;
import com.example.kcruz.gamenews.adapters.NewsAdapter;
import com.example.kcruz.gamenews.adapters.TopPlayersListAdapter;
import com.example.kcruz.gamenews.models.Image;
import com.example.kcruz.gamenews.models.News;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Configuration.*;

public class NewsFragment extends Fragment {

    public static final String ARG_ITEM_ID = "news_list";
    Activity activity;
    NewsAdapter newsAdapter;
    RecyclerView newsView;
    List<News> news;

    public NewsFragment() {
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

        newsAdapter = new NewsAdapter(activity, news, getResources());
        newsView.setAdapter(newsAdapter);

        return view;
    }


    private void setNews() {
        News news1 = new News(R.drawable.grey,"News 1", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi");
        News news2 = new News(R.drawable.grey,"Falling stars alone together forever alone alive", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi");
        News news3 = new News(R.drawable.grey,"Incredible things", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident");
        News news4 = new News(R.drawable.grey,"Falling stars alone together forever alone alive", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident  similique sunt in culpa qui officia deserunt mollitia animi");
        News news5 = new News(R.drawable.grey,"News 1", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident");
        News news6 = new News(R.drawable.grey,"Falling stars", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident");
        News news7 = new News(R.drawable.grey,"News 1", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident");
        News news8 = new News(R.drawable.grey,"News 1", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident");
        News news9 = new News(R.drawable.grey,"News 1", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident");
        News news10 = new News(R.drawable.grey,"News 1", "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident");

        news = new ArrayList<>();
        news.add(news1);
        news.add(news2);
        news.add(news3);
        news.add(news4);
        news.add(news5);
        news.add(news6);
        news.add(news7);
        news.add(news8);
        news.add(news9);
        news.add(news10);
    }

    public void setGridLayout(GridLayoutManager manager){
        newsView.setLayoutManager(manager);
    }

    @Override
    public void onResume() {
        getActivity().setTitle(R.string.news);
        super.onResume();
    }

}
