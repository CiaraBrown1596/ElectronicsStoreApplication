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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText EmailLogin;
    private EditText passwordLogin;
    private TextView textViewLogIn;

    private ProgressDialog progressDialog;
    private FirebaseAuth myFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myFirebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        if (myFirebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        EmailLogin = findViewById(R.id.editTextLoginEmail);
        passwordLogin = findViewById(R.id.editTextLoginPassword);
        buttonSignIn = findViewById(R.id.buttonLogin);
        textViewLogIn = findViewById(R.id.textViewSignUp);

        buttonSignIn.setOnClickListener(this);
        textViewLogIn.setOnClickListener(this);

    }

    private void userLogin() {
        String email = EmailLogin.getText().toString().trim();
        String password = passwordLogin.getText().toString().trim();

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
        progressDialog.setMessage("Logging in!");
        progressDialog.show();

        myFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //start the activity
                            finish();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v == buttonSignIn) {
            userLogin();
        }
        if (v == textViewLogIn) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
