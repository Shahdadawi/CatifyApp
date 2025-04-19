package com.example.catify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtFullName, edtEmail, edtAge, edtCity,
            edtUsername, edtPassword, edtConfirmPassword;
    private Button btnRegister;

    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rejester);

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

        edtFullName = findViewById(R.id.edtFullName);
        edtEmail = findViewById(R.id.edtEmail);
        edtAge = findViewById(R.id.edtAge);
        edtCity = findViewById(R.id.edtCity);
        edtUsername = findViewById(R.id.edtRegisterUsername);
        edtPassword = findViewById(R.id.edtRegisterPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        backButton = findViewById(R.id.backButton);

        btnRegister.setOnClickListener(v -> {

            String fullName = edtFullName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String age = edtAge.getText().toString().trim();
            String city = edtCity.getText().toString().trim();
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            String confirmPassword = edtConfirmPassword.getText().toString().trim();


            if (fullName.isEmpty() || email.isEmpty() || age.isEmpty()
                    || city.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("CatifyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();


            editor.putString("USERNAME", username);
            editor.putString("PASSWORD", password);
            editor.putString("FULL_NAME", fullName);
            editor.putString("EMAIL", email);
            editor.putString("AGE", age);
            editor.putString("CITY", city);
            editor.putBoolean("IS_LOGGED_IN", true);

            editor.apply();

            Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

    }



}
