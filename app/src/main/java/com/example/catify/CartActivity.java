package com.example.catify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    private TextView txtTotal;
    private Button btnBuyNow;
    private RecyclerView recyclerCart;
    private CartRecyclerAdapter adapter;
    private ImageView backButton;
    private ArrayList<Product> cartItems;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        recyclerCart = findViewById(R.id.recyclerCart);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));

        txtTotal = findViewById(R.id.txtTotal);
        backButton = findViewById(R.id.backButton);

        loadCart();

        backButton.setOnClickListener(v -> {
            finish();
        });

        btnBuyNow = findViewById(R.id.btnBuyNow);




        btnBuyNow.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);
            boolean isLoggedIn = prefs.getBoolean("IS_LOGGED_IN", false);

            if (!isLoggedIn) {
                Toast.makeText(CartActivity.this, "You need to login first.", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(CartActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            } else if (cartItems.isEmpty()) {
                Toast.makeText(CartActivity.this, "Your cart is empty.", Toast.LENGTH_SHORT).show();
            } else {
                ArrayList<Product> allProducts = ProductManager.loadProducts(CartActivity.this);

                for (Product cartProduct : cartItems) {
                    for (Product realProduct : allProducts) {
                        if (cartProduct.getName().equals(realProduct.getName())) {
                            int currentQ = realProduct.getQuantity();
                            if (currentQ >=1) {
                                realProduct.setQuantity(currentQ - 1);
                            }
                            else {
                                Toast.makeText(CartActivity.this, "Out of stock", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                ProductManager.saveProducts(CartActivity.this, allProducts);
                cartItems.clear();
                saveCart();
                adapter.notifyDataSetChanged();
                calculateTotal();
                Toast.makeText(CartActivity.this, "Purchase successful!", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void loadCart() {
        SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);
        String json = prefs.getString("CART", "");

        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        cartItems = new Gson().fromJson(json, type);

        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }


        adapter = new CartRecyclerAdapter(this, cartItems, this);
        recyclerCart.setAdapter(adapter);


        calculateTotal();


    }

    private void calculateTotal() {
        double total = 0.0;
        for (Product p : cartItems) {
            total += p.getPrice();
        }
        txtTotal.setText("Total: $" + total);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void removeFromCart(Product product) {
        cartItems.remove(product);
        saveCart();
        adapter.notifyDataSetChanged();
        calculateTotal();
        Toast.makeText(this, "Removed from cart", Toast.LENGTH_SHORT).show();
    }

    private void saveCart() {
        SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = new Gson().toJson(cartItems);
        editor.putString("CART", json);
        editor.apply();
    }
}
