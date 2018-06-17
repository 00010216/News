package com.example.kcruz.gamenews.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.database.models.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private Context context;
    List<News> news; //declarando lista que contendra todos los topplayers
    Resources resources;

    public interface NewsAdapterClickListener{
        public void onNewsClick(View v, int position);
        public void onFavoriteClick(String id, boolean value, ImageView tb);
    }

    private NewsAdapterClickListener mListener;

    public NewsAdapter(Context context, Resources resources, NewsAdapterClickListener mListener) {
        this.context = context;
        this.resources = resources;
        this.mListener = mListener;
    }

    public void setNews(List<News> news) {
        this.news = news;
        notifyDataSetChanged();
    }


    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se infla la vista del item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return (new NewsViewHolder(view));
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        Picasso.with(context)
                .load(news.get(position).getCoverImage())
                .into(holder.imageNews);
        //holder.imageNews.setImageResource(news.get(position).getCoverImage());
        //fixText(holder,position);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onNewsClick(v, position);
            }
        });
        holder.title.setText(news.get(position).getTitle());
        holder.description.setText(news.get(position).getDescription());
        holder.btnFavorite.setImageResource(news.get(position).isFavorite() ? R.drawable.ic_star_black_24dp : R.drawable.ic_star_border_black_24dp);
        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFavoriteClick(news.get(position).get_id(),
                        !(news.get(position).isFavorite()),
                        holder.btnFavorite);
            }
        });
    }


    @Override
    public int getItemCount() {
        return news == null ? 0 : news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageNews, btnFavorite;
        TextView title, description;
        public NewsViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            imageNews = itemView.findViewById(R.id.img_news);
            title = itemView.findViewById(R.id.news_title);
            description = itemView.findViewById(R.id.news_description);
            btnFavorite = itemView.findViewById(R.id.img_fav);
        }
    }

    public void fixText( NewsViewHolder holder, int position){
        //Se verifica el largo del titulo y apartir de ese valor segun la orientacion del dispositivo se corta la cadena de descripcion o el titulo
        String text = news.get(position).getTitle();
        String fixedDescription = news.get(position).getDescription().substring(0,89) + "..."; //se corta descripcion  a solo dos lineas
        int size = text.length();

        if (resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE || resources.getConfiguration().isLayoutSizeAtLeast(Configuration.SCREENLAYOUT_SIZE_LARGE)) {
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