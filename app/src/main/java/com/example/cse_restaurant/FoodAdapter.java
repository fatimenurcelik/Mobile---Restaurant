package com.example.cse_restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.myViewHolder> {
    private Context context;
    private List<Food>foods;
    private RecyclerViewClickListener listener;

    public FoodAdapter(Context context, List<Food> foods) {
        listener=new RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent i= new Intent(v.getContext(), foodItemActivity.class);
                context.startActivity(i);
            }
        };
        this.context = context;
        this.foods = foods;
    }

    public FoodAdapter(Context context, List<Food> foods,RecyclerViewClickListener listener) {
        this.listener=listener;
        this.context = context;
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodAdapter.myViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_recycle_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  FoodAdapter.myViewHolder holder, int position) {

        Picasso.get().load(foods.get(position).getResim()).placeholder(R.drawable.ic_launcher_background)
                .fit().centerCrop().into(holder.resim);
        holder.name.setText(foods.get(position).name);
        holder.fiyat.setText(foods.get(position).fiyat + " TL");
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        public ImageView resim;
        public TextView name;
        public TextView fiyat;
        public myViewHolder(@NonNull  View itemView) {
            super(itemView);
            resim=itemView.findViewById(R.id.recyclerview_imageview);
            name= itemView.findViewById(R.id.recyclerview_textview);
            fiyat =itemView.findViewById(R.id.recyclerview_textviewfiyat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(v.getContext(),foodItemActivity.class);
                    intent.putExtra("name",foods.get(getAdapterPosition()).getName());
                    intent.putExtra("image" , foods.get(getAdapterPosition()).getResim());
                    intent.putExtra("aciklama" , foods.get(getAdapterPosition()).getAcıklama());
                    intent.putExtra("fiyat" , foods.get(getAdapterPosition()).getFiyat()+" TL");
                    v.getContext().startActivity(intent);

                }
            });
        }
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

}
