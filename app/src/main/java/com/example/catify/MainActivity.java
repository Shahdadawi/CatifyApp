package com.example.catify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtWelcome;
    private EditText edtS;
    private Spinner spinnerCategory;
    private RecyclerView recyclerProducts;
    private Button btnwishlist, btnContact, btnLogin, btnCart;

    private ProductRecyclerAdapter adapter;
    private ArrayList<Product> allProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ProductManager.initializeProductsIfEmpty(this);
        setupViews();


        btnContact.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ContactActivity.class)));


        btnwishlist.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WishlistActivity.class)));


        btnCart.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));


        btnLogin.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);
            boolean isLoggedIn = prefs.getBoolean("IS_LOGGED_IN", false);
            startActivity(new Intent(MainActivity.this,
                    isLoggedIn ? AccountActivity.class : LoginActivity.class));
        });

        allProducts = ProductManager.loadProducts(this);

        adapter = new ProductRecyclerAdapter(allProducts, selectedProduct -> {
            Intent intent = new Intent(MainActivity.this, ProductActivity.class);
            intent.putExtra("product", selectedProduct);
            startActivity(intent);
        });


        recyclerProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerProducts.setAdapter(adapter);

        bindSpinner();
        setupSearch();
    }

    private void setupViews() {
        txtWelcome = findViewById(R.id.txtWelcome);
        edtS = findViewById(R.id.edtS);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        recyclerProducts = findViewById(R.id.recyclerProducts);
        btnwishlist = findViewById(R.id.btnWishlist);
        btnContact = findViewById(R.id.btnContact);
        btnLogin = findViewById(R.id.btnLogin);
        btnCart = findViewById(R.id.btnCart);
    }

    private void bindSpinner() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("All");

        for (Product p : allProducts) {
            if (!categories.contains(p.getCategory())) {
                categories.add(p.getCategory());
            }
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = categories.get(position);
                if (selectedCategory.equals("All")) {
                    adapter.updateList(allProducts);
                } else {
                    filterByCategory(selectedCategory);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                adapter.updateList(allProducts);
            }
        });
    }

    private void filterByCategory(String category) {
        ArrayList<Product> filtered = new ArrayList<>();
        for (Product p : allProducts) {
            if (p.getCategory().equalsIgnoreCase(category)) {
                filtered.add(p);
            }
        }
        adapter.updateList(filtered);
    }

    private void setupSearch() {
        edtS.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase();
                ArrayList<Product> filtered = new ArrayList<>();

                for (Product p : allProducts) {
                    if (p.getName().toLowerCase().contains(query)
                            || p.getDescription().toLowerCase().contains(query)
                            || p.getCategory().toLowerCase().contains(query)
                            || p.getSubcategory().toLowerCase().contains(query)) {
                        filtered.add(p);
                    }
                }

                adapter.updateList(filtered);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void afterTextChanged(Editable s) {}
        });
    }

    // عشان بس يرجع للصفحة الرئيسة وهو شاري اشي ترجع تتحمل المنتجات ويقل عددهم
    @Override
    protected void onResume() {
        super.onResume();
        allProducts = ProductManager.loadProducts(this);
        adapter.updateList(allProducts);
    }
}
