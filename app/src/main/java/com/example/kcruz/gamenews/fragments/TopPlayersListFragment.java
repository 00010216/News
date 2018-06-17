package com.example.kcruz.gamenews.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.adapters.TopPlayersListAdapter;
import com.example.kcruz.gamenews.database.models.Player;
import com.example.kcruz.gamenews.database.models.TopPlayer;

import java.util.ArrayList;
import java.util.List;

public class TopPlayersListFragment extends Fragment {
    public static final String ARG_ITEM_ID = "tab_top_players_list";

    Activity activity;
    TopPlayersListAdapter topPlayersListAdapter;
    RecyclerView topPlayersListView;
    LinearLayoutManager lManager;
    List<TopPlayer> topPlayers;

    public TopPlayersListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //inflamos vista que contiene el recycler
        View view = inflater.inflate(R.layout.fragment_top_players_list,container,false);

        //asigna la vista del recyclerview al recycler
        topPlayersListView = (RecyclerView) view.findViewById(R.id.top_players_list);
        topPlayersListView.setHasFixedSize(true);

        lManager = new LinearLayoutManager(container.getContext());
        topPlayersListView.setLayoutManager(lManager);

        //llamar funcion de arreglo con contenido
        setPlayers();

        topPlayersListAdapter = new TopPlayersListAdapter(activity, topPlayers);
        topPlayersListView.setAdapter(topPlayersListAdapter);

        return view;
    }


    public void setPlayers(){

        TopPlayer player1 = new TopPlayer(R.drawable.grey,"Player Name", "Subtitle");
        TopPlayer player2 = new TopPlayer(R.drawable.grey,"Player Name", "Subtitle");
        TopPlayer player3 = new TopPlayer(R.drawable.grey,"Player Name", "Subtitle");
        TopPlayer player4 = new TopPlayer(R.drawable.grey,"Player Name", "Subtitle");
        TopPlayer player5 = new TopPlayer(R.drawable.grey,"Player Name", "Subtitle");
        TopPlayer player6 = new TopPlayer(R.drawable.grey,"Player Name", "Subtitle");

        topPlayers = new ArrayList<TopPlayer>();
        topPlayers.add(player1);
        topPlayers.add(player2);
        topPlayers.add(player3);
        topPlayers.add(player4);
        topPlayers.add(player5);
        topPlayers.add(player6);
    }
}
