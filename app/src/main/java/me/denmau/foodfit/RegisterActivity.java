package me.denmau.foodfit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;
import me.denmau.foodfit.bottom_nav_views.HomeScreenActivity;

public class RegisterActivity extends AppCompatActivity {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    String TAG = "RegisterActivity";

    Button btnHaveAccount, btnCreateAccount;
    EditText emailField, passwordField, repeatPasswordField;

    // Instance variables
    String password, repeatPassword, email;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // instantiate views
        btnHaveAccount = findViewById(R.id.btnIHaveAccount);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        passwordField = (EditText) findViewById(R.id.login_password);
        repeatPasswordField = findViewById(R.id.repeat_password);
        emailField = (EditText) findViewById(R.id.reset_email);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnHaveAccount.setOnClickListener(v -> {
            // Go back to Login screen
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

        btnCreateAccount.setOnClickListener(v -> {
            email = emailField.getText().toString().trim();
            password = passwordField.getText().toString().trim();
            repeatPassword = repeatPasswordField.getText().toString().trim();
            createAccount(email, password, repeatPassword);
        });
    }

    private void createAccount(String email, String password, String repeatPassword) {

        if (!validateEmailAndPasswords(email, password, repeatPassword)) {
            return;
        }

        SweetAlertDialog createAccountProgress = new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        createAccountProgress.setTitleText("Creating Account");
        createAccountProgress.setCancelable(false);
        createAccountProgress.show();
        // create user
        Log.w(TAG, "Creating account for " + email + " password: " + password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            createAccountProgress.dismiss();
                            // Sign in success, update UI with the signed-in user's information
                            new SweetAlertDialog(RegisterActivity.this)
                                    .setTitleText("Success, Account created")
                                    .show();
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            RegisterActivity.this.updateUI(user);
                        } else {
                            createAccountProgress.dismiss();
                            // If sign in fails, display a message to the user.
                            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("Something went wrong!")
                                    .show();
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    private boolean validateEmailAndPasswords(String email, String password, String repeatPassword) {
        /*
         Returns true if:
            email is valid and not null
            passwords are more than 6 characters and they match
        */
        if (TextUtils.isEmpty(email)) {
            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("enter email")
                    .show();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("enter password")
                    .show();
            return false;
        }

        if (password.length() < 6) {
            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Your password must have at least 6 characters")
                    .show();
            return false;
        }

        if (!password.equals(repeatPassword)) {
            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Passwords do not match")
                    .show();
            return false;
        }

        /*
         One line solution from @AdamvandenHoven
        https://stackoverflow.com/questions/1819142/how-should-i-validate-an-e-mail-address
        */
        boolean emailIsValid = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (emailIsValid == false) {
            new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("invalid email")
                    .show();
        }
        return emailIsValid;
    }

    private void updateUI(FirebaseUser user) {
        startActivity(new Intent(RegisterActivity.this, HomeScreenActivity.class));
    }
}