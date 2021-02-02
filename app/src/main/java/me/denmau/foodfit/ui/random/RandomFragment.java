package me.denmau.foodfit.ui.random;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import me.denmau.foodfit.R;

public class RandomFragment extends Fragment {

    private RandomViewModel RandomViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RandomViewModel =
                new ViewModelProvider(this).get(RandomViewModel.class);
        View root = inflater.inflate(R.layout.fragment_random, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
        RandomViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                //                textView.setText(s);
            }
        });
        return root;
    }
}