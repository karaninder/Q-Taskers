package com.project.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Operation extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser==null){
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

    }


    public void onClick(View view) {
        Intent i = new Intent(this, Info.class);
        startActivity(i);
    }
    public void EClick(View view) {
        Intent i = new Intent(this, Electrician.class);
        startActivity(i);
    }
}
