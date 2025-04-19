package com.example.catify;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private TextView txtProductName;
    private TextView txtItemPrice;
    private TextView txtDescription;
    private TextView txtCategory;
    private TextView txtSubCategory;
    private ImageView imageView;
    private ImageView imageView2;
    private Button btnAddToCard;
    private Button btnAddToWishlist;

    private Product currentProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        currentProduct = (Product) getIntent().getSerializableExtra("product");
        setUpViews();

        if (currentProduct != null) {
            txtProductName.setText(currentProduct.getName());
            txtItemPrice.setText("$" + " " + currentProduct.getPrice());
            txtDescription.setText(currentProduct.getDescription());
            txtCategory.setText("Category:" + " " + currentProduct.getCategory());
            txtSubCategory.setText("SubCategory:" + " " + currentProduct.getSubcategory());

            int imageRes = getResources().getIdentifier(currentProduct.getImageName(), "drawable", getPackageName());
            imageView.setImageResource(imageRes);
        }

        imageView2.setOnClickListener(v -> finish());

        btnAddToWishlist.setOnClickListener(v -> addToList("WISHLIST"));
        btnAddToCard.setOnClickListener(v -> addToList("CART"));
    }

    private void setUpViews() {
        txtProductName = findViewById(R.id.txtProductName);
        txtItemPrice = findViewById(R.id.txtItemPrice);
        txtDescription = findViewById(R.id.txtDescription);
        txtCategory = findViewById(R.id.txtCategory);
        txtSubCategory = findViewById(R.id.txtSubCategory);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);

        btnAddToCard = findViewById(R.id.btnAddToCard);
        btnAddToWishlist = findViewById(R.id.btnAddToWishlist);
    }

    private void addToList(String key) {
        SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();

        String json = prefs.getString(key, "");
        Type type = new TypeToken<ArrayList<Product>>() {}.getType();
        ArrayList<Product> list = gson.fromJson(json, type);

        if (list == null){
            list = new ArrayList<>();
        }

        list.add(currentProduct);
        String updatedJson = gson.toJson(list);
        editor.putString(key, updatedJson);
        editor.apply();

        String message;
        if (key.equals("WISHLIST")) {
            message = "Added to Wishlist";
        } else {
            message = "Added to Cart";
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
