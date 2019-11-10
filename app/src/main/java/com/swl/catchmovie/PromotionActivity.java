package com.swl.catchmovie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swl.catchmovie.fragment.MovieFragment;
import com.swl.catchmovie.fragment.ProfileFragment;
import com.swl.catchmovie.fragment.PromotionFragment;
import com.swl.catchmovie.fragment.SearchFragment;
import com.swl.catchmovie.helper.BottomNavigationBehavior;

import java.util.ArrayList;

public class PromotionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<String> itemsArrayList = new ArrayList<>();
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        recyclerView = (RecyclerView) findViewById(R.id.ParentRV);
        layoutManager = new LinearLayoutManager(PromotionActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new ParentRecyclerAdapter(itemsArrayList, PromotionActivity.this);
        recyclerView.setAdapter(adapter);
        setData();
        MainActivity mainActivity = new MainActivity();
        toolbar = getSupportActionBar();
        BottomNavigationView navigation = mainActivity.getNavigation();
        navigation.setOnNavigationItemSelectedListener(mainActivity.getmOnNavigationItemSelectedListener());


        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        toolbar.setTitle("Promotion");
        loadFragment(new PromotionFragment());
    }

    private void setData() {
        String[] items = {"Current", "Discounts", "Movie Perks", "Food and Beverages", "Member's Exclusives", "Partnerships"};
        for (int i = 0; i < items.length; i++) {
            itemsArrayList.add(items[i]);
        }
        adapter.notifyDataSetChanged();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    toolbar.setTitle("Search");
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_movies:
                    toolbar.setTitle("Movies");
                    fragment = new MovieFragment();
                    loadFragment(fragment);
                    Intent intentMovie = new Intent(PromotionActivity.this, MainActivity.class);
                    startActivity(intentMovie);
                    intentMovie.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finishAffinity();
                    return true;
                case R.id.navigation_promo:
                    toolbar.setTitle("Promotions");
                    fragment = new PromotionFragment();
                    loadFragment(fragment);
                    //Intent intentPromo = new Intent(PromotionActivity.this, PromotionActivity.class);
                    //startActivity(intentPromo);
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    Intent intentProfile = new Intent(PromotionActivity.this, ProfileActivity.class);
                    startActivity(intentProfile);
                    //intentProfile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    // finishAffinity();
                    return true;
            }

            return false;
        }
    };

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
