package com.example.electronicsstoreapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import Prevalent.Prevalent;

public class SettingActivity extends AppCompatActivity {

    TextView saveInfo, closeSettings, updateProfile;
    EditText editUsername, editUserPassword, editUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        saveInfo = findViewById(R.id.settings_update);
        closeSettings = findViewById(R.id.settings_close);
        updateProfile = findViewById(R.id.settings_update);
        editUsername = findViewById(R.id.setting_username);
        editUserPassword = findViewById(R.id.setting_password);
        editUserEmail = findViewById(R.id.setting_email);
        
        userInfoDisplay(editUsername,editUserPassword,editUserEmail);

        closeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    userInfoSaved();
            }
        });

    }

    private void userInfoSaved() {
        if (TextUtils.isEmpty(editUsername.getText().toString())) {
            Toast.makeText(this, "Must Enter Username", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(editUserPassword.getText().toString())) {
            Toast.makeText(this, "Must Enter Passoword", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(editUserEmail.getText().toString())) {
            Toast.makeText(this, "Must Enter Email", Toast.LENGTH_LONG).show();
        } else {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("Username", editUsername.getText().toString());
            userMap.put("Password", editUserPassword.getText().toString());
            userMap.put("Email", editUserEmail.getText().toString());
            ref.child(Prevalent.currentUser.getUsername()).updateChildren(userMap);

            startActivity(new Intent(SettingActivity.this, HomeActivity.class));
            Toast.makeText(this, "Profile Info Updated", Toast.LENGTH_LONG).show();
            finish();
        }
    }


    private void userInfoDisplay(EditText editUsername, EditText editUserPassword, EditText editUserEmail)
    {
        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(Prevalent.currentUser.getUsername());

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists()){
                    String name = snapshot.child("Username").getValue().toString();
                    String password = snapshot.child("Password").getValue().toString();
                    String email = snapshot.child("Email").getValue().toString();

                    editUsername.setText(name);
                    editUserPassword.setText(password);
                    editUserEmail.setText(email);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}