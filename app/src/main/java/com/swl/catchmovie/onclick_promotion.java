package com.swl.catchmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class onclick_promotion extends AppCompatActivity {
    ArrayList<PromotionInfo> arrayList;
    Context context;
    private TextView tvName,tvDescription;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclick_promotion);

        tvName = (TextView)findViewById(R.id.promoName_id);
        tvDescription = (TextView)findViewById(R.id.promoDescription_id);
        imgView = (ImageView)findViewById(R.id.thumbnail_id);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");
        String description = intent.getExtras().getString("Description");
        String thumbnail = intent.getExtras().getString("Thumbnail");

        tvName.setText(name);
        tvDescription.setText(description);
//        Bitmap bitmap = loadBitmapFromURL(thumbnail);
//        imgView.setImageBitmap(bitmap);
        Glide.with(this)
                .asBitmap()
                .load(thumbnail)
                .apply(new RequestOptions().override(348,528))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imgView);
    }
}
