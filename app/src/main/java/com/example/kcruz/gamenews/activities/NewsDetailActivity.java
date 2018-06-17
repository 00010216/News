package com.example.kcruz.gamenews.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kcruz.gamenews.R;
import com.example.kcruz.gamenews.database.models.News;
import com.example.kcruz.gamenews.database.viewmodels.NewsViewModel;
import com.squareup.picasso.Picasso;


public class NewsDetailActivity extends AppCompatActivity {
    ImageView imageView;
    TextView description, title, newsbody;
    String id;
    NewsViewModel newsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        imageView = findViewById(R.id.img_detail_news);
        description = findViewById(R.id.news_detail_description);
        title = findViewById((R.id.news_detail_title));
        newsbody = findViewById(R.id.news_body_detail);

        id = getIntent().getStringExtra("KEY");
        if(id == null) {
            finish();
        }
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        newsViewModel.fetchNewsById(id).observe(this, new Observer<News>() {
            @Override
            public void onChanged(@Nullable News news) {
                Picasso.with(NewsDetailActivity.this)
                        .load(news.getCoverImage())
                        .into(imageView);
                description.setText(news.getDescription());
                title.setText(news.getTitle());
                newsbody.setText(news.getBody());
            }
        });

    }


}
