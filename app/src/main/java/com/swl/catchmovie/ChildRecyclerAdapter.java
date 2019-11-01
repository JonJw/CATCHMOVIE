package com.swl.catchmovie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.MyViewHolder> {

    ArrayList<PromotionInfo> arrayList;
    Context context;

    public ChildRecyclerAdapter(ArrayList<PromotionInfo> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_rowlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.Days.setText(arrayList.get(position).getPromotionInfo_name());
//        Picasso.get().setLoggingEnabled(true);
//        Picasso.get().load(arrayList.get(position).getPromotionInfo_url()).resize(120,180).into(holder.img);
        holder.img.setImageResource(arrayList.get(position).getPromotion_thumbnail());
        //Glide.get(context).load(arrayList.get(position).getPromotionInfo_url()).into(holder.img);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, onclick_promotion.class);
                intent.putExtra("Name", arrayList.get(position).getPromotionInfo_name());
                intent.putExtra("Description", arrayList.get(position).getPromotion_Description());
                intent.putExtra("Thumbnail", arrayList.get(position).getPromotion_thumbnail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Days;
        ImageView img;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Days = itemView.findViewById(R.id.daytxt);
            img = itemView.findViewById(R.id.day_img);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }
}
