package com.swl.catchmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class onclick_promotion extends AppCompatActivity {
    private TextView tvName, tvDescription;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclick_promotion);

        tvName = (TextView) findViewById(R.id.promoName_id);
        tvDescription = (TextView) findViewById(R.id.promoDescription_id);
        imgView = (ImageView) findViewById(R.id.thumbnail_id);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("Name");
        String description = intent.getExtras().getString("Description");
        int thumbnail = intent.getExtras().getInt("Thumbnail");

        tvName.setText(name);
        tvDescription.setText(description);
        imgView.setImageResource(thumbnail);
    }
}
