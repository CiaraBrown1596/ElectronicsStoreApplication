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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Users;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn;
    private EditText usernameLogin;
    private EditText passwordLogin;
    private TextView textViewLogIn, textViewAdmin;
    private String ParentDBName = "Users";

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
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        }

        usernameLogin = findViewById(R.id.editTextLoginUsername);
        passwordLogin = findViewById(R.id.editTextLoginPassword);
        buttonSignIn = findViewById(R.id.buttonLogin);
        textViewLogIn = findViewById(R.id.textViewSignUp);
        textViewAdmin = findViewById(R.id.textViewAdmin);

       buttonSignIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               userLogin();
           }
       });

        textViewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSignIn.setText(R.string.btnAdmin);
                textViewAdmin.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void userLogin()
    {
       String Username = usernameLogin.getText().toString();
       String Password = passwordLogin.getText().toString();

      if (TextUtils.isEmpty(Username)) {
        Toast.makeText(this, "Please enter your Username Address", Toast.LENGTH_LONG).show();
    } else if (TextUtils.isEmpty(Password)) {
        Toast.makeText(this, "Please enter a Password", Toast.LENGTH_LONG).show();
    } else
        {
            progressDialog.setTitle("Login Account");
            progressDialog.setMessage("Please wait while your account is being Logged in");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }



        AccessAccount(Username, Password);


    }

    private void AccessAccount(final String username, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.child(ParentDBName).child(username).exists())
                {
                    Users userData = snapshot.child(ParentDBName).child(username).getValue(Users.class);

                    if(userData.getUsername().equals(username))
                    {
                        if(userData.getPassword().equals(password))
                        {
                            Toast.makeText(LoginActivity.this,"Logged in Successfully", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);

                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Error: Password is incorrect", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }

                    }


                }
                else
                {
                    Toast.makeText(LoginActivity.this,"Error: Username already exists", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"Retry with a different Email", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

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
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }


}
