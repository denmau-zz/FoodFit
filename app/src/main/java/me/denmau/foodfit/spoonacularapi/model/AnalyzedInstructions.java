package me.denmau.foodfit.spoonacularapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AnalyzedInstructions {

    @SerializedName("name")
    private String name;

    @SerializedName("steps")
    private List<Steps> steps = new ArrayList<>();
}
