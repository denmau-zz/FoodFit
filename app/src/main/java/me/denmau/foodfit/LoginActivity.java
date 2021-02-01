package me.denmau.foodfit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    private FirebaseAuth mAuth;
    private String TAG = "LoginActivity";
    // declare views
    Button btnRegister, btnForgotPass, btnLogin;
    EditText emailField, passwordField;

    String capturedEmail, capturedPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // instantiate views
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        btnForgotPass = findViewById(R.id.forgotPass);
        btnForgotPass.setOnClickListener(this);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        emailField = (EditText) findViewById(R.id.reset_email);
        passwordField = (EditText) findViewById(R.id.login_password);
        // instantiate Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

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
                capturedEmail = emailField.getText().toString().trim();
                capturedPassword = passwordField.getText().toString().trim();
                if (passwordIsNotNullAndEmailIsValid(capturedEmail, capturedPassword)) {
                    SweetAlertDialog signInProgress = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    signInProgress.setTitleText("Signing in");
                    signInProgress.setCancelable(false);
                    signInProgress.show();
                    // Firebase Sign in
                    mAuth.signInWithEmailAndPassword(capturedEmail, capturedPassword)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        signInProgress.dismiss();
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "signInWithEmail:success");
                                        SweetAlertDialog signInSuccessDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                                        signInSuccessDialog.setTitleText("Sign in Successful");
                                        signInSuccessDialog.setCanceledOnTouchOutside(true);
                                        signInSuccessDialog.show();
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class).putExtra("LogedInUser", user));
                                    } else {
                                        signInProgress.dismiss();
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                                .setTitleText("Oops...")
                                                .setContentText("Authentication Failed")
                                                .show();
                                    }
                                }
                            });
                }
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

        Toast.makeText(this, "reset password fragment has been opened", Toast.LENGTH_SHORT).show();

    }

    private boolean passwordIsNotNullAndEmailIsValid(String capturedEmail, String capturedPassword) {
        // returns true if password is not null and email is valid
        if (TextUtils.isEmpty(capturedEmail)) {
            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("enter your email")
                    .show();
            return false;
        }

        if (TextUtils.isEmpty(capturedPassword)) {
            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("enter password")
                    .show();
            return false;
        }

        if (capturedPassword.length() < 6) {
            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Your password must have at least 6 characters")
                    .show();
            return false;
        }

        /*
         One line solution from @AdamvandenHoven
        https://stackoverflow.com/questions/1819142/how-should-i-validate-an-e-mail-address
        */
        boolean emailIsValid = !TextUtils.isEmpty(capturedEmail) && Patterns.EMAIL_ADDRESS.matcher(capturedEmail).matches();
        if (emailIsValid == false) {
            new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("invalid email")
                    .show();
        }
        return emailIsValid;
    }
}
