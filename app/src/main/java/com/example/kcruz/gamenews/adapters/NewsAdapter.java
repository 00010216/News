package com.example.kcruz.gamenews.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.models.Image;
import com.example.kcruz.gamenews.models.News;

import org.w3c.dom.Text;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    List<News> news; //declarando lista que contendra todos los topplayers
    Resources resources;

    public NewsAdapter(Context context, List<News> news, Resources resources) {
        this.context = context;
        this.news = news;
        this.resources = resources;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se infla la vista del item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return (new NewsViewHolder(view));
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.imageNews.setImageResource(news.get(position).getImage());
        fixText(holder,position);
        //holder.title.setText(news.get(position).getTitle());
        //holder.description.setText(news.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView imageNews, btnFavorite;
        TextView title, description;
        public NewsViewHolder(View itemView) {
            super(itemView);
            imageNews = itemView.findViewById(R.id.img_news);
            title = itemView.findViewById(R.id.news_title);
            description = itemView.findViewById(R.id.news_description);
            btnFavorite = itemView.findViewById(R.id.img_fav);
        }
    }

    public void fixText( NewsViewHolder holder, int position){
        //Se verifica el largo del titulo y apartir de ese valor segun la orientacion del dispositivo se corta la cadena de descripcion o el titulo
        String text = news.get(position).getTitle();
        String fixedDescription = news.get(position).getDescription().substring(0,86) + "..."; //se corta descripcion  a solo dos lineas
        int size = text.length();

        if (resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            holder.title.setText(news.get(position).getTitle());
            holder.description.setText(news.get(position).getDescription());
        }else {
            if (position == 0 || position % 3 == 0) {
                if (size <= 40) {
                    holder.title.setText(news.get(position).getTitle());
                    holder.description.setText(fixedDescription);
                    //return 5;
                } else {
                    holder.title.setText(news.get(position).getTitle());
                    holder.description.setText(news.get(position).getDescription());
                }
            } else if (position % 2 == 0) {
                if (size <= 20) {
                    holder.title.setText(news.get(position).getTitle());
                    holder.description.setText(news.get(position).getDescription().substring(0,55) + "...");
                    //return 3;
                } else {
                    holder.title.setText(news.get(position).getTitle());
                    holder.description.setText(news.get(position).getDescription());
                }
            } else {
                if (size > 20) {
                    //holder.title.setText(text);
                    holder.title.setText(news.get(position).getTitle().substring(0,20) + "...");
                    holder.description.setText(fixedDescription);
                    //return 2; //caso especifico para lineas mayor a 20
                }else if ( size == 20 || size >= 13){
                    holder.title.setText(news.get(position).getTitle());
                    holder.description.setText(news.get(position).getDescription().substring(0,17) + "...");
                } else {
                    holder.title.setText(news.get(position).getTitle());
                    holder.description.setText(news.get(position).getDescription().substring(0,30) + "...");
                }
            }
        }
    }

}