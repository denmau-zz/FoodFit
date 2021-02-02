package me.denmau.foodfit.ui.home;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import me.denmau.foodfit.model.Recipe;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Recipe>> recipes;

    public HomeViewModel() {
    }

    public MutableLiveData<ArrayList<Recipe>> getRecipes() {
        if (recipes == null) {
            recipes = new MutableLiveData<>();
        }
        return recipes;
    }

}