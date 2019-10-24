package com.swl.catchmovie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.swl.catchmovie.fragment.MovieFragment;
import com.swl.catchmovie.fragment.ProfileFragment;
import com.swl.catchmovie.fragment.PromotionFragment;
import com.swl.catchmovie.fragment.SearchFragment;
import com.swl.catchmovie.helper.BottomNavigationBehavior;

public class ProfileActivity extends AppCompatActivity {


    private ActionBar toolbar;

    GoogleSignInClient mGoogleSignInClient;
    //ProfileController profileController;
    Button sign_out;
    Button view_support;
    TextView nameTV;
    TextView emailTV;
    TextView idTV;
    ImageView photoIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sign_out = findViewById(R.id.log_out);
        view_support = findViewById(R.id.viewsupport);
        nameTV = findViewById(R.id.name);
        emailTV = findViewById(R.id.email);
        //idTV = findViewById(R.id.id);
        photoIV = findViewById(R.id.photo);

        toolbar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        // attaching bottom sheet behaviour - hide / show on scroll
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        // load the store fragment by default
        toolbar.setTitle("Profile");
        loadFragment(new ProfileFragment());

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ProfileActivity.this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            nameTV.setText("Welcome Back! "+personName);
            emailTV.setText("Email: "+personEmail);
            //idTV.setText("ID: "+personId);
            //photoIV.setImageURI(personPhoto);
            Glide.with(this).load(personPhoto).into(photoIV);
        }

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        view_support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSupport();
            }
        });


    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ProfileActivity.this,"Successfully signed out",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                        finish();
                    }
                });
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
                    Intent intentMovie = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(intentMovie);
                    intentMovie.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finishAffinity();
                    return true;
                case R.id.navigation_promo:
                    toolbar.setTitle("Promotions");
                    fragment = new PromotionFragment();
                    loadFragment(fragment);
                    //Intent intentPromo = new Intent(ProfileActivity.this, PromotionFragment.class);
                    //startActivity(intentPromo);
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    //Intent intentProfile = new Intent(ProfileActivity.this, ProfileActivity.class);
                   // startActivity(intentProfile);
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

    //TODO:
    private void viewSupport()
    {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(ProfileActivity.this);
        Intent intent = new Intent(ProfileActivity.this, SupportActivity.class);
        if (acct !=null)
        {
            intent.putExtra("USER_ID", acct.getId());
        }
        startActivity(intent);
    }



    /*
    private void displayUserProfile()
    {
        GoogleSignInAccount acct = profileController.getAcct();
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            this.nameTV.setText("Welcome Back! "+personName);
            this.emailTV.setText("Email: "+personEmail);
            //idTV.setText("ID: "+personId);
            //photoIV.setImageURI(personPhoto);
            Glide.with(this).load(personPhoto).into(this.photoIV);
        }
    }

     */


}