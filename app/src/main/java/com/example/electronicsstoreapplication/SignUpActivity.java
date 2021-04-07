package com.example.electronicsstoreapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignIn;

    private ProgressDialog progressDialog;
    private FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFirebaseAuth = FirebaseAuth.getInstance();

        if (myFirebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        progressDialog = new ProgressDialog(this);

        buttonRegister = findViewById(R.id.buttonRegister);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        textViewSignIn = findViewById(R.id.textViewSignIn);

        buttonRegister.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
            return;
        }
        //if validation ok
        progressDialog.setMessage("Registering User!");
        progressDialog.show();

        myFirebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user registered successfully
                            Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();
        }
        if (view == textViewSignIn) {
            //will open sign in activity
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}





