package me.denmau.foodfit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String TAG = "LoginActivity";
    boolean isFragmentDisplayed = false; // Reset Fragment is not displayed

    Button btnRegister;
    Button btnForgotPass;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // instantiate views
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        btnForgotPass = findViewById(R.id.forgotPass);
        btnForgotPass.setOnClickListener(this);
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
        // Set boolean flag to indicate fragment is open.
        isFragmentDisplayed = true;

    }

}
