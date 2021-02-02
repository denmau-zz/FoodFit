package me.denmau.foodfit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class RecipesFragment extends Fragment {

    /*
     * Created by Dennis Kamau
     * website: https://www.denmau.me
     */


    public RecipesFragment() {
        // Required empty public constructor
    }

    public static RecipesFragment newInstance(String param1, String param2) {
        return new RecipesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false);
    }
}