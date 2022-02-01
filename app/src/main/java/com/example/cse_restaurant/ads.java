package com.example.cse_restaurant;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ads extends Fragment {

    RecyclerView recyclerViewAds;
    private List<Advertisement> ads;
    private adAdapter adAdapter;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFireBaseDatabase;
    private ScrollView s1;
    Typeface tf1;
    private adAdapter.RecyclerViewClickListener listener;

    public ads(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ads_fragment, container, false);
        s1= view.findViewById(R.id.scrollView);
        tf1=Typeface.createFromAsset(view.getContext().getAssets(), "fonts/ar.ttf");
        TextView tV1=(TextView)view.findViewById(R.id.tV1);
        tV1.setTypeface(tf1);

        recyclerViewAds = view.findViewById(R.id.main_activity_recyclerViewAds);
        recyclerViewAds.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewAds.setLayoutManager(layoutManager);
        ads = new ArrayList<>();
        mDatabase = mFireBaseDatabase.getInstance().getReference();
        Query query = mDatabase.child("ads");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Advertisement ad = new Advertisement();
                    ad.setTitle(postSnapshot.child("title").getValue().toString());
                    ad.setResim(postSnapshot.child("resim").getValue().toString());
                    ad.setExplanation(postSnapshot.child("explanation").getValue().toString());
                    ads.add(ad);
                }
                adAdapter = new adAdapter(getActivity(), ads);
                recyclerViewAds.setAdapter(adAdapter);
                adAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    setAdAdapter();
     return view;
    }
    public void setAdAdapter(){

        adAdapter adapter = new adAdapter(getActivity(),ads,listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerViewAds.setLayoutManager(layoutManager);
        recyclerViewAds.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAds.setAdapter(adapter);
    }
}
