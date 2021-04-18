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
import Model.Products;

public class MyAdapterCart extends FirebaseRecyclerAdapter<Cart, MyAdapterCart.myViewHolder> {

    ArrayList<Cart> cartData;
    Context context;


    public MyAdapterCart(@NonNull FirebaseRecyclerOptions<Cart> options, Context context, ArrayList<Cart> cartData) {
        super(options);
        this.cartData = cartData;
        this.context = context;
    }

    public MyAdapterCart(FirebaseRecyclerOptions<Cart> options) {
        super(options);
    }




    @Override
    protected void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position, @NonNull Cart cart) {


        final Cart temp = cartData.get(position);

        myViewHolder.CartProductName.setText(cart.getPname());
        myViewHolder.CartProductPrice.setText(cart.getPrice());
        myViewHolder.CartQuantity.setText(cart.getQuantity());

    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {


        TextView CartProductName, CartProductPrice, CartProductQuantity, CartQuantity;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);


            CartProductName = (TextView) itemView.findViewById(R.id.cart_product_name);
            CartProductQuantity = (TextView) itemView.findViewById(R.id.cart_product_quantity);
            CartProductPrice = (TextView) itemView.findViewById(R.id.cart_product_price);

        }
    }

    @Override
    public int getItemCount() {
        return cartData == null ? 0 : cartData.size();
    }
}
