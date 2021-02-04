package me.denmau.foodfit.spoonacularapi.model;

public class Ingredient {
    private String name;
    private String Thumbnail;
    private boolean selected;

    public Ingredient(String name, String thumbnail) {
        this.name = name;
        // URL link from Spoonacular Docs
        Thumbnail = "https://spoonacular.com/cdn/ingredients_100x100/" + thumbnail;
        selected = false;
    }

    public String getName() {
        return name;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected() {
        selected = !selected;
    }
}
