package com.example.kcruz.gamenews.adapters;

import android.content.Context;
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

    public NewsAdapter(Context context, List<News> news) {
        this.context = context;
        this.news = news;
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
        holder.title.setText(news.get(position).getTitle());
        holder.description.setText(news.get(position).getDescription());
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
}
