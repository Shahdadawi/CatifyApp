package com.example.catify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class WishlistActivity extends AppCompatActivity {

    private RecyclerView recyclerWishlist;
    private WishlistRecyclerAdapter adapter;
    private ArrayList<Product> wishlistItems;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_wishlist);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerWishlist = findViewById(R.id.recyclerWishlist);
        recyclerWishlist.setLayoutManager(new LinearLayoutManager(this));

        btnBack = findViewById(R.id.backButton);
        btnBack.setOnClickListener(v -> finish());

        loadWishlist();
    }

    private void loadWishlist() {
        SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);
        String json = prefs.getString("WISHLIST", "");

        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        wishlistItems = new Gson().fromJson(json, type);
        if (wishlistItems == null) wishlistItems = new ArrayList<>();

        adapter = new WishlistRecyclerAdapter(this, wishlistItems);
        recyclerWishlist.setAdapter(adapter);
    }


    @SuppressLint("NotifyDataSetChanged")
    public void removeFromWishlist(Product product) {
        wishlistItems.remove(product);
        saveWishlist();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Removed from wishlist", Toast.LENGTH_SHORT).show();
    }

    private void saveWishlist() {
        SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = new Gson().toJson(wishlistItems);
        editor.putString("WISHLIST", json);
        editor.apply();
    }
}
