package me.denmau.foodfit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    private FirebaseAuth mAuth;
    private String TAG = "LoginActivity";
    // declare views
    Button btnRegister, btnForgotPass;
    EditText emailField, passwordField;

    String email, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // instantiate views
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnForgotPass = findViewById(R.id.forgotPass);
        btnForgotPass.setOnClickListener(this);
        emailField = (EditText) findViewById(R.id.username);
        passwordField = (EditText) findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

            case R.id.forgotPass:
                showResetPasswordFragment();
                break;

            case R.id.btnLogin:
                // Firebase Sign in
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    new SweetAlertDialog(LoginActivity.this)
                                            .setTitleText("Sign in Successful")
                                            .show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Oops...")
                                            .setContentText("Authentication Failed")
                                            .show();
                                }
                            }
                        });
                break;

        }
    }

    private void showResetPasswordFragment() {
        // Instantiate the fragment.
        ResetPasswordFragment resetPasswordFrag = ResetPasswordFragment.newInstance();

        // Get the FragmentManager and start a transaction.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // Add the Reset Password Fragment.
        //fragmentTransaction.add(R.id.fragment_container, resetPasswordFrag).addToBackStack(null).commit();\
        fragmentTransaction.add(R.id.fragment_container, resetPasswordFrag).addToBackStack(null).commit();

        Log.d(TAG, "reset password fragment has been opened");
        Toast.makeText(this, "reset password fragment has been opened", Toast.LENGTH_SHORT).show();

    }

}
