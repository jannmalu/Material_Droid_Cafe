package com.mbetemalu.droidcafe;

public class Recipe {
    //Declare private member variables
    private final int recipe_image;
    private String recipe_title;
    private String recipe_description;
    private String recipe_ingredients;
    private String recipe_steps;

    //Creating a recipe constructor and pass parameter

    Recipe(int recipe_image, String recipe_title, String recipe_description, String recipe_ingredients, String recipe_steps) {
        this.recipe_image = recipe_image;
        this.recipe_title = recipe_title;
        this.recipe_description = recipe_description;
        this.recipe_ingredients = recipe_ingredients;
        this.recipe_steps = recipe_steps;
    }

    //Getters and return the objects
    public int getRecipe_image(){
        return recipe_image;
    }

    public String getRecipe_title(){
        return recipe_title;
    }

    public String getRecipe_description(){
        return recipe_description;
    }

    public String getRecipe_ingredients() {
        return recipe_ingredients;
    }

    public String getRecipe_steps() {
        return recipe_steps;
    }
}
