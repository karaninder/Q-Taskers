package com.project.afinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.PasswordAuthentication;

public class Signup extends AppCompatActivity {
    private TextView mDisplayName;
    private TextView mPhoneNumber;
    private TextView mPassword;
    private TextView mEmail;


    private Button mCreateBtn;

    private FirebaseAuth mAuth;

    private ProgressDialog mRegProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        mRegProcess = new ProgressDialog(this);

        mDisplayName = (TextView) findViewById(R.id.FullName);
        mPhoneNumber = (TextView) findViewById(R.id.PhoneNumber);
        mPassword = (TextView) findViewById(R.id.PinCode);
        mEmail = (TextView) findViewById(R.id.City);
        mCreateBtn = (Button) findViewById(R.id.SignUp);






        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String FullName = mDisplayName.getText().toString();
                String PhoneNumber = mPhoneNumber.getText().toString();
                String Password = mPassword.getText().toString();
                String Email = mEmail.getText().toString();

                if(!TextUtils.isEmpty(FullName)||!TextUtils.isEmpty(Password)||!TextUtils.isEmpty(PhoneNumber)||!TextUtils.isEmpty(Email)) {


                    mRegProcess.setTitle("Signing Up");
                    mRegProcess.setMessage("Please Wait");
                    mRegProcess.setCanceledOnTouchOutside(false);
                    mRegProcess.show();
                    register_user(FullName, PhoneNumber, Password, Email);
                }
                else{
                    mDisplayName.setError("Please enter the Name and other credentials");
                }
            }
        });
    }

    private void register_user(String FullName, String phoneNumber, String Password, String Email) {
        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    mRegProcess.dismiss();
                    Intent mainIntent = new Intent(Signup.this, Operation.class);
                    startActivity(mainIntent);
                    finish();


                } else {
                    mRegProcess.hide();
                    Toast.makeText(Signup.this, "Fill All The Details And Check Your Internet", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

}

