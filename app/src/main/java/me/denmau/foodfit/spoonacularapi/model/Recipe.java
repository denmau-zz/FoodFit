package me.denmau.foodfit.spoonacularapi.model;

public class Recipe {

    private String id;
    private String recipeTitle;
    private String recipeImage;
    private int numServings;
    private int readyInMins;
    private double healthScore;
    private double foodFitScore;
    private String dishType;

    public Recipe(String id, String recipeTitle, String recipeImage, int numServings, int readyInMins, double healthScore, double foodFitScore, String dishType) {
        this.id = id;
        this.recipeTitle = recipeTitle;
        this.recipeImage = recipeImage;
        this.numServings = numServings;
        this.readyInMins = readyInMins;
        this.healthScore = healthScore;
        this.foodFitScore = foodFitScore;
        this.dishType = dishType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public int getNumServings() {
        return numServings;
    }

    public void setNumServings(int numServings) {
        this.numServings = numServings;
    }

    public int getReadyInMins() {
        return readyInMins;
    }

    public void setReadyInMins(int readyInMins) {
        this.readyInMins = readyInMins;
    }

    public double getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(double healthScore) {
        this.healthScore = healthScore;
    }

    public double getFoodFitScore() {
        return foodFitScore;
    }

    public void setFoodFitScore(double foodFitScore) {
        this.foodFitScore = foodFitScore;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }
}
