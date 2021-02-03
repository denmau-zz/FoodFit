package me.denmau.foodfit.reciperecycler;

public class RecipeModel {
    private int recipeImage;
    private String recipeTitle;
    private int prepTime;
    private double healthScore;
    private double foodFitScore;
    private String dishType;


    public RecipeModel(int recipeImage, String recipeTitle, int prepTime, double healthScore, double foodFitScore, String dishType) {
        this.recipeImage = recipeImage;
        this.recipeTitle = recipeTitle;
        this.prepTime = prepTime;
        this.healthScore = healthScore;
        this.foodFitScore = foodFitScore;
        this.dishType = dishType;
    }

    public int getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(int recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
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
