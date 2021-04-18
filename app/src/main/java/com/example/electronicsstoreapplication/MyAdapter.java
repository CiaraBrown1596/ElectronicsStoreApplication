package com.example.electronicsstoreapplication;

import android.content.Intent;
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
import java.util.List;

import Model.Products;

public class MyAdapter extends FirebaseRecyclerAdapter<Products, MyAdapter.myViewHolder> {

    ArrayList<Products> productData;
    Context context;


    public MyAdapter(@NonNull FirebaseRecyclerOptions<Products> options, Context context, ArrayList<Products> productData) {
        super(options);
        this.productData = productData;
        this.context = context;
    }

    public MyAdapter(FirebaseRecyclerOptions<Products> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder myViewHolder, int position, @NonNull Products products) {


        final Products temp = productData.get(position);

        myViewHolder.productName.setText(products.getProductName());
        Glide.with(myViewHolder.productImage.getContext()).load(products.getImage()).into(myViewHolder.productImage);
        myViewHolder.productDescription.setText(products.getProductDescription());
        myViewHolder.productPrice.setText(products.getPrice());

        myViewHolder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetailsActivity.class);
                intent.putExtra("ProductName", temp.getProductName());
                intent.putExtra("ProductDescription", temp.getProductDescription());
                intent.putExtra("Price", temp.getPrice());
                intent.putExtra("Image", temp.getImage());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

            }
        });

    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout, parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder {


        ImageView productImage;
        TextView productName, productPrice, productDescription;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = (ImageView) itemView.findViewById(R.id.product_image);
            productName = (TextView) itemView.findViewById(R.id.product_name);
            productDescription = (TextView) itemView.findViewById(R.id.product_description);
            productPrice = (TextView) itemView.findViewById(R.id.product_price);

        }
    }

    @Override
    public int getItemCount() {
        return productData == null ? 0 : productData.size();
    }

}