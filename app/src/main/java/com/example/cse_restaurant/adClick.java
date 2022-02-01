package com.example.cse_restaurant;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class adClick extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_click_activity);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        TextView name = (TextView) findViewById(R.id.title);
        TextView aciklama =(TextView) findViewById(R.id.acıklama);

        Glide.with(this).load(getIntent().getStringExtra("image")).into(image);
        name.setText(getIntent().getStringExtra("title"));
        aciklama.setText(getIntent().getStringExtra("aciklama"));
    }
}
