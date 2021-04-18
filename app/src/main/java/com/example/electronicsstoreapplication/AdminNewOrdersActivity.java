package com.example.electronicsstoreapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Orders;

public class AdminNewOrdersActivity extends AppCompatActivity {

    private RecyclerView ordersList;
    private DatabaseReference ordersRef;
    Button btnShowOrders;
    RecyclerView recyclerView;
    MyAdapterOrders myAdapterOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_orders);

        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders");

        btnShowOrders = findViewById(R.id.btn_show_orders);

        ordersList = findViewById(R.id.ordersList);
        ordersList.setLayoutManager(new LinearLayoutManager(this));

        btnShowOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminNewOrdersActivity.this, AdminUserProductsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Orders> options =
                new FirebaseRecyclerOptions.Builder<Orders>()
                .setQuery(ordersRef,Orders.class)
                .build();

        myAdapterOrders = new MyAdapterOrders(options);
        recyclerView.setAdapter(myAdapterOrders);

    }

    public static class OrdersViewHolder extends RecyclerView.ViewHolder
    {
        public TextView username, userEmail, userTotalPrice, userDateTime, userAddress;
        public Button btnShowOrders;



        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.order_user_name);
            userEmail = itemView.findViewById(R.id.order_email);
            userAddress = itemView.findViewById(R.id.order_address);
            userDateTime = itemView.findViewById(R.id.order_date_time);
            userTotalPrice = itemView.findViewById(R.id.order_total_price);

            btnShowOrders = itemView.findViewById(R.id.btn_show_orders);
        }
    }

}