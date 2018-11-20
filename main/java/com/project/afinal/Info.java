package com.project.afinal;

import android.app.ProgressDialog;
import android.app.Service;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.siddhant.afinal.Payment;


public class Info extends AppCompatActivity {

   EditText service,address,pincode,phonenumber;
   Button p_btn;
   FirebaseAuth firebaseAuth;
   FirebaseUser User;

    private ProgressDialog mInfoProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        firebaseAuth=FirebaseAuth.getInstance();

        mInfoProcess = new ProgressDialog(this);

        User=firebaseAuth.getCurrentUser();


        service = (EditText)findViewById(R.id.Service);
        address=(EditText)findViewById(R.id.Address);
        pincode=(EditText)findViewById(R.id.PinCode);
        phonenumber=(EditText)findViewById(R.id.PN);
        p_btn=(Button)findViewById(R.id.ptp_btn);

        p_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Adddatabase();
            }

        });

    }

    private void Adddatabase(){
        String ser=service.getText().toString().trim();
        String add=address.getText().toString().trim();
        String pc=pincode.getText().toString().trim();
        String pn=phonenumber.getText().toString().trim();
        if(!TextUtils.isEmpty(ser)||!TextUtils.isEmpty(add))
        {

            mInfoProcess.setTitle("Signing Up");
            mInfoProcess.setMessage("Please Wait");
            mInfoProcess.setCanceledOnTouchOutside(false);
            mInfoProcess.show();
            FirebaseDatabase database= FirebaseDatabase.getInstance();
            DatabaseReference myRef= database.getReference("Q-Taskers");
            myRef.child(User.getUid()).child("Service").setValue(ser);
            myRef.child(User.getUid()).child("Address").setValue(add);
            myRef.child(User.getUid()).child("Pincode").setValue(pc);
            myRef.child(User.getUid()).child("Phone Number").setValue(pn);
            Intent i = new Intent(this, Payment.class);
            startActivity(i);


        }
        else{
            Toast.makeText(Info.this, "Please fill up the empty fields", Toast.LENGTH_LONG).show();
        }
    }


}


