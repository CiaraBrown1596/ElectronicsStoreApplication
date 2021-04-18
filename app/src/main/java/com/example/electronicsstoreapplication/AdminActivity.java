package com.example.electronicsstoreapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    private Button btnAddProduct, btnOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Toast.makeText(AdminActivity.this,"Welcome Admin", Toast.LENGTH_LONG).show();

        btnAddProduct = findViewById(R.id.btnAddNewProducts);

        btnOrders = findViewById(R.id.admin_orders);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminProductCategory.class);
                startActivity(intent);
            }
        });

        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });

    }
}
