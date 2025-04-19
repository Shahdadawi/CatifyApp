package com.example.catify;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ProductManager {

    private static final String DATA_KEY = "PRODUCTS_DATA";

    public static void saveProducts(Context context, ArrayList<Product> products) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String jsonData = gson.toJson(products);

        editor.putString(DATA_KEY, jsonData);
        editor.apply();
    }

    public static ArrayList<Product> loadProducts(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(DATA_KEY, "");

        if (json.isEmpty()){
             return new ArrayList<>();
        }

        else {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Product>>() {}.getType();
            return gson.fromJson(json, type);

        }

    }

    public static void initializeProductsIfEmpty(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(DATA_KEY, "");

        if (json.isEmpty()) {
            ArrayList<Product> Products = ProductData.getProducts();
            saveProducts(context, Products);
        }
    }

}
