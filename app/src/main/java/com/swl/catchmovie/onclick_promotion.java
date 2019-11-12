package com.swl.catchmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

        //add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            //ends the activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
