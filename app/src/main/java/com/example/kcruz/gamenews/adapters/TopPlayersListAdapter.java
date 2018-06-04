package com.example.kcruz.gamenews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.models.Player;
import com.example.kcruz.gamenews.models.TopPlayer;

import java.util.List;

public class TopPlayersListAdapter extends RecyclerView.Adapter<TopPlayersListAdapter.TopPlayersViewHolder>{
    private Context context;
    List<TopPlayer> topPlayers; //declarando lista que contendra todos los topplayers

    //constructor que recibira la lista y el contexto desde el fragmento TopPlayerListFragment
    public TopPlayersListAdapter(Context context, List<TopPlayer> topPlayerList) {
        this.context = context;
        this.topPlayers = topPlayerList;
    }



    public static class TopPlayersViewHolder extends RecyclerView.ViewHolder {
        //indica la vista donde se ubicaran la informacion de la lista
        ImageView img;
        TextView title,subtitle;

        public TopPlayersViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.top_player_image);
            title = itemView.findViewById(R.id.top_player_name);
            subtitle = itemView.findViewById(R.id.top_player_subtitle);
        }
    }

    @Override
    public TopPlayersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se infla la vista del item
        View view = LayoutInflater.from(context).inflate(R.layout.top_player_item,parent,false);
        return (new TopPlayersViewHolder(view));
    }

    @Override
    public void onBindViewHolder(TopPlayersViewHolder holder, int position) {
        //se asignar el contenido a cada view que contiene el item
        holder.img.setImageResource(topPlayers.get(position).getImage());
        holder.title.setText(topPlayers.get(position).getName());
        holder.subtitle.setText(topPlayers.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return topPlayers.size();
    }

}
