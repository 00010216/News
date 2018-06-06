package com.example.kcruz.gamenews.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.adapters.ImagesAdapter;
import com.example.kcruz.gamenews.adapters.TopPlayersListAdapter;
import com.example.kcruz.gamenews.models.Image;

import java.util.ArrayList;
import java.util.List;


public class ImagesFragment extends Fragment {

    public static final String ARG_ITEM_ID = "tab_images_grid";
    Activity activity;
    ImagesAdapter imagesAdapter;
    RecyclerView imagesGridView;
    GridLayoutManager lManager;
    List<Image> images;

    public ImagesFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_images,container,false);

        //asigna la vista del recyclerview al recycler
        imagesGridView = (RecyclerView) view.findViewById(R.id.images_grid);
        imagesGridView.setHasFixedSize(true);

        lManager = new GridLayoutManager(getContext(),3);
        imagesGridView.setLayoutManager(lManager);

        //llamar funcion de arreglo con contenido
        setImages();

        imagesAdapter= new ImagesAdapter(activity, images);
        imagesGridView.setAdapter(imagesAdapter);

        return view;
    }

    public void setImages(){
        Image image1 = new Image(R.drawable.grey);
        Image image2 = new Image(R.drawable.brown);
        Image image3 = new Image(R.drawable.brown);
        Image image4 = new Image(R.drawable.brown);
        Image image5 = new Image(R.drawable.gamenlogo);
        Image image6 = new Image(R.drawable.gamenlogo);
        Image image7 = new Image(R.drawable.gamenlogo);

        images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);
        images.add(image6);
        images.add(image7);
    }
}
