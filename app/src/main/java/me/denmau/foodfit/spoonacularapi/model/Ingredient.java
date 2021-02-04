package me.denmau.foodfit.spoonacularapi.model;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("id")
    private int id;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("originalString")
    private String originalString;

    @SerializedName("amount")
    private double amount;
}
