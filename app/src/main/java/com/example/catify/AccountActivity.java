package com.example.catify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccountActivity extends AppCompatActivity {


    private TextView txtUsername, txtEmail, txtAge, txtCity;
    private Button btnLogout;

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtUsername = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtAge = findViewById(R.id.txtAge);
        txtCity = findViewById(R.id.txtCity);
        btnLogout = findViewById(R.id.btnLogout);
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(v -> {
            finish();
        });

        SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);

        String name = prefs.getString("USERNAME", "N/A");
        String email = prefs.getString("EMAIL", "N/A");
        String age = prefs.getString("AGE", "N/A");
        String city = prefs.getString("CITY", "N/A");

        txtUsername.setText( name);
        txtEmail.setText( email);
        txtAge.setText( age);
        txtCity.setText(city);

        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("IS_LOGGED_IN", false);
            editor.apply();
            startActivity(new Intent(AccountActivity.this, MainActivity.class));
            finish();
        });
    }
}