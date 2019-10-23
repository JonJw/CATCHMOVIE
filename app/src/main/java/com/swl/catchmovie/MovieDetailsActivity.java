package com.swl.catchmovie;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailsActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();

        //Add Back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent:checking for incoming intents.");
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("movie_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");
            String imageurl = getIntent().getStringExtra("image_url");
            String moviename = getIntent().getStringExtra("movie_name");
            String movieadvice = getIntent().getStringExtra("movie_advice");
            String movielanguage = getIntent().getStringExtra("movie_language");
            String movieduration = getIntent().getStringExtra("movie_duration");
            String moviesynopsis = getIntent().getStringExtra("movie_synopsis");
            String moviedirector = getIntent().getStringExtra("movie_director");
            String moviecast = getIntent().getStringExtra("movie_cast");
            setImage(imageurl, moviename, movieadvice, movielanguage, movieduration, moviesynopsis, moviedirector, moviecast);
        }
    }

    private void setImage(String imageurl, String moviename, String movieadvice, String movielanguage, String movieduration, String moviesynopsis, String moviedirector, String moviecast){
        Log.d(TAG, "setImage: setting to image and name to widgets.");

        TextView image = findViewById(R.id.movie_title);
        image.setText(moviename);


        TextView advice = findViewById(R.id.movie_advice);
        advice.setText(movieadvice);

        TextView language = findViewById(R.id.movie_language);
        language.setText(movielanguage);

        TextView duration = findViewById(R.id.movie_duration);
        duration.setText(movieduration);

        TextView synopsis = findViewById(R.id.movie_synopsis);
        moviecast = moviecast.replace("\n", " ");
        moviecast = moviecast.replace("  ", "");
        synopsis.append(": " + moviesynopsis + "\n" + "\n" + moviedirector + "\n"+  "\n" + " " + moviecast);

        //TextView director = findViewById(R.id.movie_director);
        //director.setText(moviedirector);

        //TextView cast = findViewById(R.id.movie_cast);
        //cast.setText(moviecast);

        ImageView imageView = findViewById(R.id.movie_image);


        Glide.with(this)
                .asBitmap()
                .load(imageurl)
                .into(imageView);

    }


}
