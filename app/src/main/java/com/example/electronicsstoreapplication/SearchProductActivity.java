package com.example.electronicsstoreapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import Model.Products;

public class SearchProductActivity extends AppCompatActivity {

    private Button btnSearch;
    private EditText inputTxt;
    private RecyclerView searchList;
    private String SearchInput;
    MyAdapter myAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        inputTxt = findViewById(R.id.search_product);
        btnSearch = findViewById(R.id.btn_search);
        searchList = findViewById(R.id.searchList);
        searchList.setLayoutManager(new LinearLayoutManager(SearchProductActivity.this));

        btnSearch.setOnClickListener(v -> {
            SearchInput = inputTxt.getText().toString();

            onStart();
        });

        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products"), Products.class)
                        .build();

        myAdapter = new MyAdapter(options);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myAdapter.stopListening();
    }
}

