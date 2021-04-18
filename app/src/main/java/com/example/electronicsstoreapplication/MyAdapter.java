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

import java.util.List;

import Model.Products;

public class MyAdapter extends FirebaseRecyclerAdapter<Products, MyAdapter.myViewHolder> {


    public MyAdapter(@NonNull FirebaseRecyclerOptions<Products> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i, @NonNull Products products)
    {
        myViewHolder.productName.setText(products.getProductName());
        Glide.with(myViewHolder.productImage.getContext()).load(products.getImage()).into(myViewHolder.productImage);
        myViewHolder.productDescription.setText(products.getProductDescription());
        myViewHolder.productPrice.setText(products.getPrice());

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {


        ImageView productImage;
        TextView productName, productPrice, productDescription;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = (ImageView)itemView.findViewById(R.id.product_image);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            productDescription = (TextView) itemView.findViewById(R.id.product_description);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);

        }
    }


}