package com.swl.catchmovie;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swl.catchmovie.fragment.MovieFragment;
import com.swl.catchmovie.fragment.ProfileFragment;
import com.swl.catchmovie.fragment.PromotionFragment;
import com.swl.catchmovie.fragment.SearchFragment;
import com.swl.catchmovie.helper.BottomNavigationBehavior;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    private BottomNavigationView navigation;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        navigation.setSelectedItemId(R.id.navigation_profile);
        toolbar.setTitle("Profile");
        fragment = new ProfileFragment();
        loadFragment(fragment);


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_search:
                    toolbar.setTitle("Search");
                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_movies:
                    toolbar.setTitle("Movies");
                    fragment = new MovieFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_promo:
                    toolbar.setTitle("Promotions");
                    fragment = new PromotionFragment();
                    //Intent intentPromo = new Intent(MainActivity.this, PromotionActivity.class);
                    //startActivity(intentPromo);
                    loadFragment(fragment);
                    return true;
            }

            return false;
        }
    };


    public BottomNavigationView.OnNavigationItemSelectedListener getNavigation() {
        return getmOnNavigationItemSelectedListener();
    }

    public BottomNavigationView.OnNavigationItemSelectedListener getmOnNavigationItemSelectedListener() {
        return mOnNavigationItemSelectedListener;
    }

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
