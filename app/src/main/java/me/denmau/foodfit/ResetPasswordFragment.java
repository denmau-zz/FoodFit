package me.denmau.foodfit;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ResetPasswordFragment extends Fragment {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */

    FirebaseAuth mAuth;
    String TAG = "ResetPasswordFragment";
    Context thisContext;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    public static ResetPasswordFragment newInstance() {
        return new ResetPasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thisContext = getActivity();
        return inflater.inflate(R.layout.fragment_reset_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnResetPass = view.findViewById(R.id.btnResetPassword);
        EditText inputEmailField = view.findViewById(R.id.reset_email);
        btnResetPass.setOnClickListener(v -> {
            String resetEmail = inputEmailField.getText().toString().trim();
            if (!TextUtils.isEmpty(resetEmail) && Patterns.EMAIL_ADDRESS.matcher(resetEmail).matches()) {
                sendPasswordResetEmail(resetEmail);
            } else {
                new SweetAlertDialog(thisContext, SweetAlertDialog.ERROR_TYPE)
                        .setContentText("invalid email")
                        .show();
                Log.w(TAG, "invalid email");
            }
        });
    }

    private void sendPasswordResetEmail(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.e(TAG, "Success, Password reset email sent.!");
                        // tell user to check mail box
                        new SweetAlertDialog(thisContext, SweetAlertDialog.SUCCESS_TYPE)
                                .setContentText("password reset email sent, please check email")
                                .show();
                    } else {
                        Log.w(TAG, "Error generating email link: " + task.toString());
                        new SweetAlertDialog(thisContext, SweetAlertDialog.ERROR_TYPE)
                                .setContentText("error sending password reset email")
                                .show();
                    }
                });
    }
}
