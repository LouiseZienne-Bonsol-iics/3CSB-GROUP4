package com.project.groupfour;

public class ResultsConstructor {
    String recipeName;
    int imageUrl;

    ResultsConstructor(){
    }

    public String getName() {
        return recipeName;
    }

    public void setName(String name) {
        this.recipeName = name;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ResultsConstructor(String name, int pic){
        this.recipeName = name;
        this.imageUrl = pic;
    }
}
