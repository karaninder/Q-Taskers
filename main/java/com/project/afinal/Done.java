package com.project.afinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import com.siddhant.afinal.Operation;


public class Done  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

    }
    public void onClick(View view) {
        Intent i = new Intent(this, Operation.class);
        startActivity(i);

    }
}
