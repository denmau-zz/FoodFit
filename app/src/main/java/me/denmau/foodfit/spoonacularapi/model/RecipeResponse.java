package me.denmau.foodfit.spoonacularapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RecipeResponse {
    @SerializedName("vegetarian")
    private boolean vegetarian;

    @SerializedName("vegan")
    private boolean vegan;

    @SerializedName("glutenFree")
    private boolean glutenFree;

    @SerializedName("dairyFree")
    private boolean dairyFree;

    @SerializedName("veryHealthy")
    private boolean veryHealthy;

    @SerializedName("cheap")
    private boolean cheap;

    @SerializedName("veryPopular")
    private boolean veryPopular;

    @SerializedName("sustainable")
    private boolean sustainable;

    @SerializedName("aggregateLikes")
    private int aggregateLikes;

    @SerializedName("sourceName")
    private String sourceName;

    @SerializedName("extendedIngredients")
    private List<Ingredient> extendedIngredients = new ArrayList<>();

    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("readyInMinutes")
    private int readyInMinutes;

    @SerializedName("servings")
    private int servings;

    @SerializedName("image")
    private String image;

    @SerializedName("imageType")
    private String imageType;

    @SerializedName("summary")
    private String summary;

    @SerializedName("analyzedInstructions")
    private List<AnalyzedInstructions> analyzedInstructions = new ArrayList<>();

    @SerializedName("sourceUrl")
    private String sourceUrl;

    @SerializedName("spoonacularSourceUrl")
    private String spoonacularSourceUrl;

}
