package me.denmau.foodfit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.util.Strings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    /**
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    private FirebaseAuth mAuth;

    String TAG = "RegisterActivity";

    Button btnHaveAccount;
    Button btnCreateAccount;

    EditText passwordField;
    EditText repeatPasswordField;
    EditText emailField;

    // Instance variables
    String password;
    String repeatPassword;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Assign views
        btnHaveAccount = findViewById(R.id.btnIHaveAccount);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        passwordField = findViewById(R.id.password);

        password = passwordField.getText().toString();
        repeatPassword = repeatPasswordField.getText().toString();

        emailField = findViewById(R.id.username);

    }

    @Override
    protected void onStart() {
        super.onStart();
        email = emailField.getText().toString().trim();

        btnHaveAccount.setOnClickListener(v -> {
            // Go back to Login screen
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

        btnCreateAccount.setOnClickListener(v -> {
            validateEmailAndPasswords(email, password, repeatPassword);
        });
    }

    private boolean validateEmailAndPasswords(String email, String password, String repeatPassword) {
        /*
         Returns true if:
            email is valid and not null
            passwords are more than 6 characters and they match
        */
        if (Strings.isEmptyOrWhitespace(email) || Strings.isEmptyOrWhitespace(password)) {
            return false;
        }
        if (password.length() < 6) {
            Snackbar.make(btnCreateAccount, "Your password must have at least 6 characters.", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if (!password.equals(repeatPassword)) {
            Snackbar.make(btnCreateAccount, "Passwords do not match", Snackbar.LENGTH_SHORT).show();
            return false;
        }
        /*
         One line solution from @AdamvandenHoven
        https://stackoverflow.com/questions/1819142/how-should-i-validate-an-e-mail-address
        */
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void createAccount(String email, String password, String repeatPassword) {
        if (!validateEmailAndPasswords(email, password, repeatPassword)) {
            return;
        }
        SweetAlertDialog createAccountProgressDialog = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        createAccountProgressDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        createAccountProgressDialog.setTitleText("Loading");
        createAccountProgressDialog.setCancelable(false);
        createAccountProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        startActivity(new Intent(RegisterActivity.this, HomeScreenActivity.class));
    }
}