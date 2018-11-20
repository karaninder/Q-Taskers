package com.project.afinal;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.siddhant.afinal.Operation;
import com.siddhant.afinal.Signup;


public class Login extends AppCompatActivity {


    private TextView mLoginEmail;
    private TextView mLoginPassword;
    private TextView signup;
    private Button mLogin_btn;

    private ProgressDialog mLoginProgress;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mLoginProgress=new ProgressDialog(this);

        mLoginEmail=(TextView)findViewById(R.id.LoginEmail);
        mLoginPassword=(TextView)findViewById((R.id.LoginPassword));
        mLogin_btn=(Button)findViewById(R.id.Loginbtn);

        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = mLoginEmail.getText().toString();
                String Password = mLoginPassword.getText().toString();

                if (!TextUtils.isEmpty(Email) || !TextUtils.isEmpty(Password)) {

                    mLoginProgress.setTitle("Logging in");
                    mLoginProgress.setMessage("Please Wait");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();


                    loginUser(Email, Password);

                }
                else{
                    mLoginEmail.setError("Please fill up the credentials");
                }
            }
        });


    }




    private void loginUser(String email, String password) {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        mLoginProgress.dismiss();
                        Intent i=new Intent(Login.this,Operation.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        mLoginProgress.hide();
                        Toast.makeText(Login.this, "Invalid Username or Password", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }


    public void Signup(View view){
        Intent i = new Intent(this, Signup.class);
        startActivity(i);
    }
}
