package com.example.cse_restaurant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class signin extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
    }
    public void createUser(View view){
        EditText editTextFullName = (EditText) findViewById(R.id.editTextFullName);
        String fullName= editTextFullName.getText().toString();

        EditText editTextEmailAddress = (EditText) findViewById(R.id.login_editTextEmailAddress);
        String email= editTextEmailAddress.getText().toString();

        EditText editTextPassword = (EditText) findViewById(R.id.login_editTextPassword);
        String password= editTextPassword.getText().toString();

        EditText editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);
        String confirmPassword= editTextConfirmPassword.getText().toString();

        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        String phone= editTextPhone.getText().toString();

        User userInfo= new User(fullName,email,password,phone);

        Button button2= (Button) findViewById(R.id.button2);

        if (!password.equals(confirmPassword)){
            Toast.makeText(signin.this,"Password and confirm password should be the same!", Toast.LENGTH_LONG).show();
        }else {
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user=mAuth.getCurrentUser();

                        mDatabase = FirebaseDatabase.getInstance().getReference();
                        mDatabase.child("users").child(user.getUid()).setValue(userInfo);

                        System.out.println(user.getEmail());
                        TextView textView = findViewById(R.id.textView);
                        textView.setText("Registration Successful");
                    }else{
                        Toast.makeText(signin.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}