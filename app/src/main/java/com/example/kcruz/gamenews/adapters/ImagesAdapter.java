package com.example.kcruz.gamenews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.database.models.Image;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder> {
    private Context context;
    List<Image> images; //declarando lista que contendra todos los topplayers

    public ImagesAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public ImagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //se infla la vista del item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.images_item,parent,false);
        return (new ImagesViewHolder(view));
    }

    @Override
    public void onBindViewHolder(ImagesViewHolder holder, int position) {
        holder.image.setImageResource(images.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }


    public static class ImagesViewHolder  extends RecyclerView.ViewHolder{
        ImageView image;

        public ImagesViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_images);
        }
    }


}

