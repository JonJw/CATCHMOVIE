package com.swl.catchmovie;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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
        Glide.with(this.context)
                .asBitmap()
                .load(arrayList.get(position).getPromotion_thumbnail())
                .apply(new RequestOptions().override(348,528))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.img);
        System.out.println(arrayList.get(position).getPromotion_thumbnail());

        if(Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,onclick_promotion.class);
                intent.putExtra("Name",arrayList.get(position).getPromotionInfo_name());
                intent.putExtra("Description",arrayList.get(position).getPromotion_Description());
                intent.putExtra("Thumbnail",arrayList.get(position).getPromotion_thumbnail());

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
        ImageView img,thumbnail;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Days = itemView.findViewById(R.id.daytxt);
            img = itemView.findViewById(R.id.day_img);
            thumbnail = itemView.findViewById(R.id.thumbnail_id);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }
}
