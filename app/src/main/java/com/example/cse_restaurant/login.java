package com.example.cse_restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        System.out.println("oncreatee");
    }

      @Override
    protected void onStart() {
        super.onStart();
    }

    public void login2(View view){
        EditText login_editTextEmailAddress = (EditText) findViewById(R.id.login_editTextEmailAddress);
        String email= login_editTextEmailAddress.getText().toString();

        EditText login_editTextPassword = (EditText) findViewById(R.id.login_editTextPassword);
        String password= login_editTextPassword.getText().toString();

        Intent mainpageIntent = new Intent(this,MainpageActivity.class);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(mainpageIntent);
                        }else{
                            Toast.makeText(login.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}