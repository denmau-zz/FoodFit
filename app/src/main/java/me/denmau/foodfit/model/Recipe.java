package me.denmau.foodfit.model;

public class Recipe {
    private int recipeImage;
    private String recipeTitle;
    private int preptime;
    private double healthscore;
    private double foodFitScore;
    private String dishtype;


    public Recipe(int recipeImage, String recipeTitle, int preptime, double healthscore, double foodFitScore, String dishtype) {
        this.recipeImage = recipeImage;
        this.recipeTitle = recipeTitle;
        this.preptime = preptime;
        this.healthscore = healthscore;
        this.foodFitScore = foodFitScore;
        this.dishtype = dishtype;
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

    public int getPreptime() {
        return preptime;
    }

    public void setPreptime(int preptime) {
        this.preptime = preptime;
    }

    public double getHealthscore() {
        return healthscore;
    }

    public void setHealthscore(double healthscore) {
        this.healthscore = healthscore;
    }

    public double getFoodFitScore() {
        return foodFitScore;
    }

    public void setFoodFitScore(double foodFitScore) {
        this.foodFitScore = foodFitScore;
    }

    public String getDishtype() {
        return dishtype;
    }

    public void setDishtype(String dishtype) {
        this.dishtype = dishtype;
    }
}
