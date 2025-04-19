package com.example.catify;

import android.content.Intent;
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

public class ContactActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtEmail;
    private EditText edtMessage;
    private Button btnSend;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUpViews();

        backButton.setOnClickListener(v -> finish());
    }


    private void setUpViews() {
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);
        backButton = findViewById(R.id.backButton);

        btnSend.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String message = edtMessage.getText().toString().trim();

            String subject = "Massage From Catify .." + name;

            String mailBody = "Email: " + email + "\n\nMessage:\n" + message;

            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/text");
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"shahdadawi301@gmail.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(Intent.EXTRA_TEXT, mailBody);

            try {
                startActivity(Intent.createChooser(emailIntent, "Send email via..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "No email apps found!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}