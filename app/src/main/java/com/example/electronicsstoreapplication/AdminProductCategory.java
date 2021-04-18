package com.example.electronicsstoreapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class AdminProductCategory extends AppCompatActivity {

    private ImageView phones, laptops, tvs, fridges, gaming, blenders;
    private Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_category);

        phones = findViewById(R.id.Phones);
        laptops = findViewById(R.id.Laptops);
        tvs = findViewById(R.id.Tvs);
        blenders = findViewById(R.id.Blenders);
        fridges = findViewById(R.id.Fridge);
        gaming = findViewById(R.id.Gaming);
        btnLogout = findViewById(R.id.admin_Logout);

        phones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProductCategory.this, AdminAddProduct.class);
                intent.putExtra("category", "Phones");
                startActivity(intent);
            }
        });

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProductCategory.this, AdminAddProduct.class);
                intent.putExtra("category", "Laptops");
                startActivity(intent);
            }
        });

        tvs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProductCategory.this, AdminAddProduct.class);
                intent.putExtra("category", "Tvs");
                startActivity(intent);
            }
        });

        blenders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProductCategory.this, AdminAddProduct.class);
                intent.putExtra("category", "Blenders");
                startActivity(intent);
            }
        });


        gaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProductCategory.this, AdminAddProduct.class);
                intent.putExtra("category", "Gaming");
                startActivity(intent);
            }
        });

        fridges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProductCategory.this, AdminAddProduct.class);
                intent.putExtra("category", "Fridges");
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
