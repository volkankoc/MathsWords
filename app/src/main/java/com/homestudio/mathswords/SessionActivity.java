package com.homestudio.mathswords;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public abstract class SessionActivity extends AppCompatActivity {

    protected FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    protected FirebaseUser mUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        if (mUser == null){
            onLogout();
        }
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if (mUser != null){
                    // User is signed in
                    Log.d("INFO", "onAuthStateChanged:signed_in:" + mUser.getUid());
                    onLogin();
                } else {
                    // User is signed out
                    Log.d("INFO", "onAuthStateChanged:signed_out");
                    onLogout();
                }
            }
        };
    }

    protected abstract void onLogin();

    protected abstract void onLogout();


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
