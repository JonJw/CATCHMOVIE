package com.swl.catchmovie;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ProfileController {

    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount acct;
    private Context appContext;

    public ProfileController(Context appContext)
    {
        this.appContext = appContext;
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        this.mGoogleSignInClient = GoogleSignIn.getClient(appContext, gso);
        this.acct = GoogleSignIn.getLastSignedInAccount(appContext);
    }

    public GoogleSignInClient getmGoogleSignInClient() {
        return mGoogleSignInClient;
    }

    public GoogleSignInAccount getAcct() {
        return acct;
    }


}
