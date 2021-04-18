package com.example.electronicsstoreapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Cart;
import Model.Products;

public class CartFragment extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button btnNext;
    private TextView TotalAmount, txtmsg1;
    private DatabaseReference CartListRef;
    private Cart cart;
    Context context;
    MyAdapterCart myAdapterCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cart);

        recyclerView = recyclerView.findViewById(R.id.ReLayout);
        txtmsg1 = txtmsg1.findViewById(R.id.msg1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        btnNext = btnNext.findViewById(R.id.btn_Next);
        TotalAmount = TotalAmount.findViewById(R.id.total_price);


        recyclerView = (RecyclerView) findViewById(R.id.cartList);
        recyclerView.setLayoutManager(new LinearLayoutManager(CartFragment.this));

        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CartList"), Cart.class)
                        .build();

        myAdapterCart = new MyAdapterCart(options);
        recyclerView.setAdapter(myAdapterCart);
    }

    @Override
    protected void onStart(){
        super.onStart();
        myAdapterCart.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        myAdapterCart.stopListening();
    }
}
