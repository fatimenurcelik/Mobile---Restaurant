package com.example.cse_restaurant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

public class home extends Fragment {

    RecyclerView recyclerViewFood;
    private List<Food> foods;
    private FoodAdapter foodAdapter;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFireBaseDatabase;
    private ScrollView s1;
    private FoodAdapter.RecyclerViewClickListener listener;

    Typeface tf1;

    ImageSlider mainslider;

    public home() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        s1= view.findViewById(R.id.scrollView);

        tf1=Typeface.createFromAsset(view.getContext().getAssets(), "fonts/Ka.ttf");
        TextView tV1=(TextView)view.findViewById(R.id.tV1);
        tV1.setTypeface(tf1);
        recyclerViewFood = view.findViewById(R.id.main_activity_recyclerViewFood);
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
                    food.setAcıklama(postSnapshot.child("acıklama").getValue().toString());
                    foods.add(food);
                }
                foodAdapter = new FoodAdapter(getActivity(), foods);
                recyclerViewFood.setAdapter(foodAdapter);
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mainslider=(ImageSlider) view.findViewById(R.id.image_slider);
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
        setFoodAdapter();
        return view;
    }
    public void setFoodAdapter(){

        FoodAdapter adapter = new FoodAdapter(getActivity(),foods,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerViewFood.setLayoutManager(layoutManager);
        recyclerViewFood.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFood.setAdapter(adapter);
    }

}




