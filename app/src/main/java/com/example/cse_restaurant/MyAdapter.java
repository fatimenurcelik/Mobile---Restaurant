package com.example.cse_restaurant;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;

    public MyAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public MyAdapter(Context c, FragmentManager fm, int totalTabs){
        super(fm);
        context=c;
        this.totalTabs=totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                home homeFrag = new home();
                return homeFrag;
            case 1:
                ads adsFrag = new ads();
                return adsFrag;
            case 2:
                basket basketFrag = new basket();
                return basketFrag;
            case 3:
                contact contactFrag =new contact();
                return contactFrag;
            case 4:
                detail detailFrag = new detail();
                return detailFrag;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
