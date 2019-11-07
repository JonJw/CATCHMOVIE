package com.swl.catchmovie.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.swl.catchmovie.LoginActivity;
import com.swl.catchmovie.ProfileActivity;
import com.swl.catchmovie.R;
import com.swl.catchmovie.SupportActivity;
import com.swl.catchmovie.helper.BottomNavigationBehavior;

public class ProfileFragment extends Fragment {

    GoogleSignInClient mGoogleSignInClient;
    //ProfileController profileController;
    Button sign_out;
    Button view_support;
    TextView nameTV;
    TextView emailTV;
    //TextView idTV;
    ImageView photoIV;

    public ProfileFragment() {
        // Required empty public constructor


    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sign_out = view.findViewById(R.id.log_out);
        view_support = view.findViewById(R.id.viewsupport);
        nameTV = view.findViewById(R.id.name);
        emailTV = view.findViewById(R.id.email);
        //idTV = findViewById(R.id.id);
        photoIV = view.findViewById(R.id.photo);



        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            nameTV.setText("Welcome Back! " + personName);
            emailTV.setText("Email: " + personEmail);
            //idTV.setText("ID: "+personId);
            //photoIV.setImageURI(personPhoto);
            Glide.with(getActivity()).load(personPhoto).into(photoIV);
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

        return view;
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getContext(), "Successfully signed out", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(), LoginActivity.class));

                    }
                });
    }
    private void viewSupport() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getContext());
        Intent intent = new Intent(getContext(), SupportActivity.class);
        if (acct != null) {
            intent.putExtra("USER_ID", acct.getId());
        }
        startActivity(intent);
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //Log.d(TAG, "Entering new Fragment");
        transaction.replace(R.id.frame_container, fragment);

        transaction.commit();
       // Log.d(TAG, "Entered new Fragment");
    }
}
