package me.denmau.foodfit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Button btnHaveAccount;
    Button btnCreateAccount;

    EditText passwordField;
    EditText repeatPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Assign views
        btnHaveAccount = findViewById(R.id.btnIHaveAccount);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        passwordField = findViewById(R.id.password);
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnHaveAccount.setOnClickListener(v -> {
            // Go back to Login screen
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

        btnCreateAccount.setOnClickListener(v -> {

            // Check if both passwords match
            if ( (passwordField.getText() != repeatPasswordField.getText()) || (passwordField.isEmpty() || repeatPasswordField.isEmpty()) ) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }

        });

    }

}