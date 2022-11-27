package com.org.skc.astronomypictureoftheday.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.org.skc.astronomypictureoftheday.R;

import static com.org.skc.astronomypictureoftheday.view.FavItemsListActivity.EXTRA_TITLE;
import static com.org.skc.astronomypictureoftheday.view.FavItemsListActivity.EXTRA_DATE;
import static com.org.skc.astronomypictureoftheday.view.FavItemsListActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String dateTaken = intent.getStringExtra(EXTRA_DATE);

        ImageView imageView = findViewById(R.id.imageViewDetail);
        TextView textViewTitle = findViewById(R.id.textViewTitleDetail);
        TextView textViewDateTaken = findViewById(R.id.textViewDateTakenDetail);

        //Call to Glide library to fetch image from URL and add to imageview widget
        Glide.with(this)
                .load(imageUrl).into(imageView);
        textViewTitle.setText(title);
        textViewDateTaken.setText(dateTaken);
    }
}