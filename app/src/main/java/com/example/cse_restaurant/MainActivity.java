package com.example.cse_restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private StorageReference mStorageRef ;
    Typeface tf1;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerViewFood;
    private List<Food> foods;
    private FoodAdapter foodAdapter;
    private FirebaseDatabase mFireBaseDatabase;
    private ScrollView s1;
    ImageSlider mainslider;
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mStorageRef= FirebaseStorage.getInstance().getReference();

        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        tf1=Typeface.createFromAsset(getAssets(),"fonts/Ka.ttf");
        TextView tV1=(TextView)findViewById(R.id.tV1);
        tV1.setTypeface(tf1);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            Intent mainpageIntent = new Intent(this, MainpageActivity.class);
            startActivity(mainpageIntent);
        }
        s1= findViewById(R.id.scrollView);

        mainslider=(ImageSlider) findViewById(R.id.image_slider);
        final List<SlideModel> remoteImages = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("slider").
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data:dataSnapshot.getChildren())
                            remoteImages.add(new SlideModel(data.child("resim").getValue().toString(), ScaleTypes.FIT));
                        mainslider.setImageList(remoteImages,ScaleTypes.FIT);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

        recyclerViewFood = findViewById(R.id.main_activity_recyclerViewFood);
        recyclerViewFood.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewFood.setLayoutManager(layoutManager);
        foods = new ArrayList<>();
        mDatabase = mFireBaseDatabase.getInstance().getReference();
        Query query = mDatabase.child("food");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Food food = new Food();
                    food.setName(postSnapshot.child("name").getValue().toString());
                    food.setResim(postSnapshot.child("resim").getValue().toString());
                    food.setFiyat(postSnapshot.child("fiyat").getValue().toString());
                    foods.add(food);
                }
                if (getApplicationContext()!=null){
                    foodAdapter = new FoodAdapter(getApplicationContext(), foods);
                    recyclerViewFood.setAdapter(foodAdapter); //burda başlıyr
                    foodAdapter.notifyDataSetChanged();
                }else{
                    System.out.println("if e girilmedi");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void login (View view){
        Button buttonLogin= (Button) findViewById(R.id.buttonLogin);
        Intent i= new Intent(MainActivity.this, login.class);
        startActivity(i);
        System.out.println("logine tıklanma başarılı");
    }
    public void signup (View view){
        Button buttonSignup= (Button) findViewById(R.id.buttonSignup);
        Intent i2 = new Intent(MainActivity.this, signin.class);
        startActivity(i2);
    }
}
