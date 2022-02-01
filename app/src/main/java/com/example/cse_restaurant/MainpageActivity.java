package com.example.cse_restaurant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainpageActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);


        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_home_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_bookmark_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_shopping_basket_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_local_phone_24));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_baseline_table_rows_24));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
       // TextView textViewWelcome = (TextView) findViewById(R.id.textViewWelcome);
       // textViewWelcome.setText("Welcome our Restaurant "+mAuth.getCurrentUser().getEmail());
    }

   /* public void logout(View view){
        mAuth.signOut();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        System.out.println("logout tıklandı");
    }*/
}