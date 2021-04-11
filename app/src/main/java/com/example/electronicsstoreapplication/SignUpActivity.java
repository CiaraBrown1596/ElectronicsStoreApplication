package com.example.electronicsstoreapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private Button buttonRegister;
    private EditText editTextEmail, editUserName;
    private EditText editTextPassword;
    private TextView textViewSignIn;

    private ProgressDialog progressDialog;
    private FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        myFirebaseAuth = FirebaseAuth.getInstance();

        if (myFirebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }
        progressDialog = new ProgressDialog(this);

        buttonRegister = findViewById(R.id.buttonRegister);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editUserName = findViewById(R.id.editTextName);
        progressDialog = new ProgressDialog(this);


        textViewSignIn = findViewById(R.id.textViewSignIn);


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String name = editUserName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter a Username", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter an Email Address", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter a Password", Toast.LENGTH_LONG).show();
        } else {
            progressDialog.setTitle("Create Account");
            progressDialog.setMessage("Please wait while your account is being set up");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            ValidateEmail(name, email, password);

        }


    }

    private void ValidateEmail(final String username, final String email, final String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!(snapshot.child("Users").child(username).exists())) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("Username", username);
                    userDataMap.put("Email", email);
                    userDataMap.put("Password", password);

                    RootRef.child("Users").child(username).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "Your Account has been created", Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();

                                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(SignUpActivity.this, "Error: Please retry again later", Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                    }

                                }
                            });


                } else {
                    Toast.makeText(SignUpActivity.this, "Error this Username already exists..", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}








