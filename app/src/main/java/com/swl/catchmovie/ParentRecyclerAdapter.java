package com.swl.catchmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParentRecyclerAdapter extends RecyclerView.Adapter<ParentRecyclerAdapter.MyViewHolder> {
    ArrayList<String> parentArrayList;
    Context context;
    ArrayList<PromotionInfo> daysArrayList = new ArrayList<>();

    public ParentRecyclerAdapter(ArrayList<String> parentArrayList, Context context) {
        this.parentArrayList = parentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_rowlayout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.ItemName.setText(parentArrayList.get(position));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.ChildRV.setLayoutManager(layoutManager);
        holder.ChildRV.setHasFixedSize(true);
        daysArrayList.clear();

        String[] names = {"Free Popcorn", "Stamp Card", "Angry Birds", "Frappe Drinks", "UnionPay Deals", "Gemini Man", "OCBC Deals", "S$3 Off"};
        String descPlaceHolder = "Insert Promotion Description Here";
//        String [] urls = {"https://makingmulticulturalmusic.files.wordpress.com/2013/02/singing-bowl-on-red.jpg",
//        "https://makingmulticulturalmusic.files.wordpress.com/2013/02/singing-bowl-on-red.jpg",
//        "https://makingmulticulturalmusic.files.wordpress.com/2013/02/singing-bowl-on-red.jpg",
//        "https://makingmulticulturalmusic.files.wordpress.com/2013/02/singing-bowl-on-red.jpg",
//        "https://makingmulticulturalmusic.files.wordpress.com/2013/02/singing-bowl-on-red.jpg",
//        "https://makingmulticulturalmusic.files.wordpress.com/2013/02/singing-bowl-on-red.jpg",
//        "https://makingmulticulturalmusic.files.wordpress.com/2013/02/singing-bowl-on-red.jpg"};

//        for (int i=0;i<names.length;i++){
//            PromotionInfo promotionInfo = new PromotionInfo();
//            promotionInfo.setPromotionInfo_name(names[i]);
//            promotionInfo.setPromotion_thumbnail(R.drawable.thumbnail_1);
////            promotionInfo.setPromotionInfo_url(urls[i]);
        daysArrayList.add(new PromotionInfo(names[0], descPlaceHolder, R.drawable.thumbnail_1_min));
        daysArrayList.add(new PromotionInfo(names[1], descPlaceHolder, R.drawable.thumbnail_2_min));
        daysArrayList.add(new PromotionInfo(names[2], descPlaceHolder, R.drawable.thumbnail_3_min));
        daysArrayList.add(new PromotionInfo(names[3], descPlaceHolder, R.drawable.thumbnail_4_min));
        daysArrayList.add(new PromotionInfo(names[4], descPlaceHolder, R.drawable.thumbnail_5_min));
        daysArrayList.add(new PromotionInfo(names[5], descPlaceHolder, R.drawable.thumbnail_6_min));
        daysArrayList.add(new PromotionInfo(names[6], descPlaceHolder, R.drawable.thumbnail_7_min));
        daysArrayList.add(new PromotionInfo(names[7], descPlaceHolder, R.drawable.thumbnail_8_min));
//        }
        ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(daysArrayList, context);
        holder.ChildRV.setAdapter(childRecyclerAdapter);
        childRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ItemName;
        RecyclerView ChildRV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemName = itemView.findViewById(R.id.itemname);
            ChildRV = itemView.findViewById(R.id.childRV);
        }
    }
}
