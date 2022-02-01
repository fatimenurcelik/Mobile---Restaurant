package com.example.cse_restaurant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adAdapter extends  RecyclerView.Adapter<adAdapter.ImageViewHolder> {
    private Context context;
    private List<Advertisement> ads;
    private RecyclerViewClickListener listener;

    public adAdapter(Context context, List<Advertisement> ads) {
        listener=new RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent i= new Intent(v.getContext(), adClick.class);
                context.startActivity(i);
            }
        };
        this.context = context;
        this.ads = ads;
    }

    public adAdapter(Context context, List<Advertisement> ads,RecyclerViewClickListener listener) {
        this.listener=listener;
        this.context = context;
        this.ads = ads;
    }

    @NonNull
    @Override
    public adAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ad_recycle_row,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  adAdapter.ImageViewHolder holder, int position) {
        Advertisement ad = ads.get(position);
        Picasso.get().load(ad.getResim()).placeholder(R.drawable.ic_launcher_background)
                .fit().centerCrop().into(holder.resim);
        holder.title.setText(ad.title);
       // holder.explanation.setText(ad.explanation);
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView resim;
        public TextView title;
       // public TextView explanation;
        public ImageViewHolder(@NonNull  View itemView) {
            super(itemView);
            resim=itemView.findViewById(R.id.recyclerview_imageview);
            title= itemView.findViewById(R.id.recyclerview_textview);
          // explanation =itemView.findViewById(R.id.recyclerview_textviewexplanation);
            itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(v.getContext(),adClick.class);
                intent.putExtra("title",ads.get(getAdapterPosition()).getTitle());
                intent.putExtra("image" , ads.get(getAdapterPosition()).getResim());
                intent.putExtra("aciklama" , ads.get(getAdapterPosition()).getExplanation());
                v.getContext().startActivity(intent);
            }
        });
        }
    }
    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
