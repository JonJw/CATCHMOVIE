package com.swl.catchmovie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.InputStreamReader;

public class ParentRecyclerAdapter extends RecyclerView.Adapter<ParentRecyclerAdapter.MyViewHolder> {
    ArrayList<String> parentArrayList;
    Context context;
    ArrayList<PromotionInfo> daysArrayList = new ArrayList<>();
    ArrayList<PromotionInfo> daysArrayList_disc = new ArrayList<>();
    ArrayList<PromotionInfo> daysArrayList_perks = new ArrayList<>();
    ArrayList<PromotionInfo> daysArrayList_mem = new ArrayList<>();
    ArrayList<PromotionInfo> daysArrayList_part = new ArrayList<>();
    ArrayList<PromotionInfo> daysArrayList_fnb = new ArrayList<>();
    public static List<String> imgList_name = new ArrayList<String>();
    public static List<String> imgList_url = new ArrayList<String>();
    public static List<String> imgList_desc = new ArrayList<String>();

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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.ChildRV.setLayoutManager(layoutManager);
        holder.ChildRV.setHasFixedSize(true);
        daysArrayList.clear();
        Log.d("printParent",parentArrayList.get(position));
        Log.d("printPlaceholder","Movie Perks");
        Log.v("isEqual?",Boolean.toString(parentArrayList.get(position).equals("Movie Perks")));

        BufferedReader br = null;
        try{
            String line = new String();
            InputStream is = context.getResources().openRawResource(R.raw.promotions_final);
            br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            while ((line = br.readLine())!=null) {
                String[] col = line.split(",");
                switch (parentArrayList.get(position)) {
                    case "Discounts":
                        if (!col[1].equals("n")) {
                            col[1] = col[1].replaceAll("\"", "").trim();
                            //System.out.println(col[1]);
                            imgList_name.add(col[1]);
                            col[7] = col[7].replaceAll("\"", "").trim();
                            imgList_url.add(col[7]);
                            col[6] = col[6].replaceAll("\"", "").trim();
                            imgList_desc.add(col[6]);
                        }
                        //System.out.println("User chose Discounts.");
                        break;
                    case "All Current":
                        if (!col[0].equals("n")) {
                            col[0] = col[0].replaceAll("\"", "").trim();
                            //System.out.println(col[0]);
                            imgList_name.add(col[0]);
                            col[7] = col[7].replaceAll("\"", "").trim();
                            imgList_url.add(col[7]);
                            col[6] = col[6].replaceAll("\"", "").trim();
                            imgList_desc.add(col[6]);
                        }
                        //System.out.println("User chose All Current.");
                        break;
                    case "Movie Perks":
                        if (!col[2].equals("n")) {
                            col[2] = col[2].replaceAll("\"", "").trim();
                            //System.out.println(col[2]);
                            imgList_name.add(col[2]);
                            col[7] = col[7].replaceAll("\"", "").trim();
                            imgList_url.add(col[7]);
                            col[6] = col[6].replaceAll("\"", "").trim();
                            imgList_desc.add(col[6]);
                        }
                        //System.out.println("User chose Movie Perks.");
                        break;
                    case "Food and Beverages":
                        if (!col[3].equals("n")) {
                            col[3] = col[3].replaceAll("\"", "").trim();
                            //System.out.println(col[3]);
                            imgList_name.add(col[3]);
                            col[7] = col[7].replaceAll("\"", "").trim();
                            imgList_url.add(col[7]);
                            col[6] = col[6].replaceAll("\"", "").trim();
                            imgList_desc.add(col[6]);
                        }
                        //System.out.println("User chose Food and Beverages.");
                        break;
                    case "Member's Exclusives":
                        if (!col[4].equals("n")) {
                            col[4] = col[4].replaceAll("\"", "").trim();
                            //System.out.println(col[4]);
                            imgList_name.add(col[4]);
                            col[7] = col[7].replaceAll("\"", "").trim();
                            imgList_url.add(col[7]);
                            col[6] = col[6].replaceAll("\"", "").trim();
                            imgList_desc.add(col[6]);
                        }
                        //System.out.println("User chose Member's Exclusives.");
                        break;
                    case "Partnerships":
                        if (!col[5].equals("n")) {
                            col[5] = col[5].replaceAll("\"", "").trim();
                            //System.out.println(col[5]);
                            imgList_name.add(col[5]);
                            col[7] = col[7].replaceAll("\"", "").trim();
                            imgList_url.add(col[7]);
                            col[6] = col[6].replaceAll("\"", "").trim();
                            imgList_desc.add(col[6]);
                        }
                        //System.out.println("User chose Partnerships.");
                        break;
                    default:
                        System.out.println("Went to default");
                        break;
                }
//                daysArrayList.add(new PromotionInfo(imgList_name.get(i),imgList_desc.get(i),imgList_url.get(i)));
            }
            br.close();

        }catch(IOException e){
            e.printStackTrace();
        }
//        ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter();

        switch (parentArrayList.get(position)) {
            case "All Current":
                for (int i = 0; i < imgList_name.size(); i++) {
                    daysArrayList.add(new PromotionInfo(imgList_name.get(i), imgList_desc.get(i), imgList_url.get(i)));
                    //System.out.println(imgList_url.get(i) + "\t" + imgList_name.get(i));
                }
                ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(daysArrayList, context);
                holder.ChildRV.setAdapter(childRecyclerAdapter);
                childRecyclerAdapter.notifyDataSetChanged();
                break;
            case "Discounts":
                for (int i = 0; i < imgList_name.size(); i++) {
                    daysArrayList_disc.add(new PromotionInfo(imgList_name.get(i), imgList_desc.get(i), imgList_url.get(i)));
                    //System.out.println(imgList_url.get(i) + "\t" + imgList_name.get(i));
                }
                ChildRecyclerAdapter childRecyclerAdapter_disc = new ChildRecyclerAdapter(daysArrayList_disc, context);
                holder.ChildRV.setAdapter(childRecyclerAdapter_disc);
                childRecyclerAdapter_disc.notifyDataSetChanged();
                break;

            case "Movie Perks":
                for (int i = 0; i < imgList_name.size(); i++) {
                    daysArrayList_perks.add(new PromotionInfo(imgList_name.get(i), imgList_desc.get(i), imgList_url.get(i)));
                    //System.out.println(imgList_url.get(i) + "\t" + imgList_name.get(i));
                }
                ChildRecyclerAdapter childRecyclerAdapter_perk = new ChildRecyclerAdapter(daysArrayList_perks, context);
                holder.ChildRV.setAdapter(childRecyclerAdapter_perk);
                childRecyclerAdapter_perk.notifyDataSetChanged();
                break;

            case "Member's Exclusives":
                for (int i = 0; i < imgList_name.size(); i++) {
                    daysArrayList_mem.add(new PromotionInfo(imgList_name.get(i), imgList_desc.get(i), imgList_url.get(i)));
                    //System.out.println(imgList_url.get(i) + "\t" + imgList_name.get(i));
                }
                ChildRecyclerAdapter childRecyclerAdapter_mem = new ChildRecyclerAdapter(daysArrayList_mem, context);
                holder.ChildRV.setAdapter(childRecyclerAdapter_mem);
                childRecyclerAdapter_mem.notifyDataSetChanged();
                break;

            case "Food and Beverages":
                for (int i = 0; i < imgList_name.size(); i++) {
                    daysArrayList_fnb.add(new PromotionInfo(imgList_name.get(i), imgList_desc.get(i), imgList_url.get(i)));
                    //System.out.println(imgList_url.get(i) + "\t" + imgList_name.get(i));
                }
                ChildRecyclerAdapter childRecyclerAdapter_fnb = new ChildRecyclerAdapter(daysArrayList_fnb, context);
                holder.ChildRV.setAdapter(childRecyclerAdapter_fnb);
                childRecyclerAdapter_fnb.notifyDataSetChanged();
                break;

            case "Partnerships":
                daysArrayList_part.clear();
                for (int i = 0; i < imgList_name.size(); i++) {
                    daysArrayList_part.add(new PromotionInfo(imgList_name.get(i), imgList_desc.get(i), imgList_url.get(i)));
                    System.out.println(imgList_url.get(i) + "\t" + imgList_name.get(i));
                }
                ChildRecyclerAdapter childRecyclerAdapter_part = new ChildRecyclerAdapter(daysArrayList_part, context);
                holder.ChildRV.setAdapter(childRecyclerAdapter_part);
                childRecyclerAdapter_part.notifyDataSetChanged();
                break;
        }
        imgList_url.clear();
        imgList_desc.clear();
        imgList_name.clear();
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
