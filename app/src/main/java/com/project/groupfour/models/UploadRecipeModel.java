package com.project.groupfour.models;

public class UploadRecipeModel {
    private String recipeName;
    private String category;
    private String subCategory;
    private String recipeRating;
    private String prepTime;
    private String ingredients;
    private String recipe;
    private String imageUrl;

    public UploadRecipeModel(){
        //empty constructor needed
    }

    public UploadRecipeModel(String recipeName, String category, String subCategory, String recipeRating, String prepTime, String ingredients, String recipe, String imageUrl) {
        if(recipeName.trim().equals("")){
            recipeName = "Unnamed Recipe";
        }

        this.recipeName = recipeName;
        this.category = category;
        this.subCategory = subCategory;
        this.recipeRating = recipeRating;
        this.prepTime = prepTime;
        this.ingredients = ingredients;
        this.recipe = recipe;
        this.imageUrl = imageUrl;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getRecipeRating() {
        return recipeRating;
    }

    public void setRecipeRating(String recipeRating) {
        this.recipeRating = recipeRating;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

