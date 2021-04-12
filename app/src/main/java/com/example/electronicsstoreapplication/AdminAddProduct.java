package com.example.electronicsstoreapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class AdminAddProduct extends AppCompatActivity {

    private String CategoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);

        CategoryName = getIntent().getExtras().get("category").toString();

        Toast.makeText(AdminAddProduct.this,CategoryName, Toast.LENGTH_LONG).show();

    }
}
