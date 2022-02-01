package com.example.cse_restaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.w3c.dom.Text;

import java.net.URL;

public class foodItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_item);

        ImageView image = (ImageView) findViewById(R.id.recyclerview_imageview);
        TextView name = (TextView) findViewById(R.id.textviewname);
        TextView fiyat = (TextView) findViewById(R.id.textviewfiyat);
        TextView aciklama =(TextView) findViewById(R.id.textviewaciklama);

        Glide.with(this).load(getIntent().getStringExtra("image")).into(image);
        fiyat.setText(getIntent().getStringExtra("fiyat"));
        name.setText(getIntent().getStringExtra("name"));
        aciklama.setText(getIntent().getStringExtra("aciklama"));
    }


}
