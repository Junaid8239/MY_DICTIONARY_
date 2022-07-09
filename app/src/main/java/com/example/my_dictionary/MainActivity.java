package com.example.my_dictionary;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText memail,mpassword;
    Button msearch,mregister;
    TextView mlogin;
    FirebaseAuth fAuth;
    ProgressBar mprogress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        memail = findViewById(R.id.login_mail);
        mpassword = findViewById(R.id.login_password);

        mregister = findViewById(R.id.login_btn);
        mlogin = findViewById(R.id.logintext);
        mprogress = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),Home.class));
            finish();
        }

        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is Reequired.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    mpassword.setError("Password Must be >= 6 Characters");
                    return;
                }
                mprogress.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"User created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }else{
                            Toast.makeText(MainActivity.this,"Error"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            mprogress.setVisibility(View.GONE);
                        }
                    }
                });



            }
        });


    }

    public void onClick(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }
}