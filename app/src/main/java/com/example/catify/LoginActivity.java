package com.example.catify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private TextView txtRegisterNow;

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       setUpViews();


       backButton.setOnClickListener(v -> {
           finish();
       });


    }


    private void setUpViews(){

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegisterNow = findViewById(R.id.txtRegisterNow);
        backButton = findViewById(R.id.backButton);



        btnLogin.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);
            String savedUser = prefs.getString("USERNAME", "");
            String savedPass = prefs.getString("PASSWORD", "");

            String inputUser = edtUsername.getText().toString();
            String inputPass = edtPassword.getText().toString();

            if (inputUser.equals(savedUser) && inputPass.equals(savedPass)) {
                prefs.edit().putBoolean("IS_LOGGED_IN", true).apply();
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
            }
        });



        txtRegisterNow.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

    }
}
