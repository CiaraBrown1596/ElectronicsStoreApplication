package com.example.electronicsstoreapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;

import Model.Cart;
import Model.Orders;
import Model.Products;

public class MyAdapterOrders extends FirebaseRecyclerAdapter<Orders, MyAdapterOrders.myViewHolder> {

    ArrayList<Orders> orderData;
    Context context;


    public MyAdapterOrders(@NonNull FirebaseRecyclerOptions<Orders> options, Context context, ArrayList<Orders> orderData) {
        super(options);
        this.orderData = orderData;
        this.context = context;
    }

    public MyAdapterOrders(FirebaseRecyclerOptions<Orders> options) {
        super(options);
    }




    @Override
    protected void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position, @NonNull Orders orders) {


        final Orders temp = orderData.get(position);

        myViewHolder.orderName.setText(orders.getName());
        myViewHolder.orderEmail.setText(orders.getEmail());
        myViewHolder.orderAddress.setText(orders.getAddress());
        myViewHolder.orderDataTime.setText(orders.getDateTime());
        myViewHolder.orderTotalPrice.setText(orders.getTotalAmount());

    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {



        TextView orderName, orderEmail, orderAddress, orderDataTime, orderTotalPrice;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            orderName = (TextView) itemView.findViewById(R.id.order_user_name);
            orderEmail = (TextView) itemView.findViewById(R.id.order_email);
            orderAddress = (TextView) itemView.findViewById(R.id.order_address);
            orderDataTime = (TextView) itemView.findViewById(R.id.order_date_time);
            orderTotalPrice = (TextView) itemView.findViewById(R.id.order_total_price);

        }
    }

    @Override
    public int getItemCount() {
        return orderData == null ? 0 : orderData.size();
    }

}
