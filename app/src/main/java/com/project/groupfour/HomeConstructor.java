package com.project.groupfour;

public class HomeConstructor {

    int cHomeImage;
    String cHomeName;

    public HomeConstructor(int cHomeImage, String cHomeName) {
        this.cHomeImage = cHomeImage;
        this.cHomeName = cHomeName;
    }

    public int getcHomeImage() {
        return cHomeImage;
    }

    public void setcHomeImage(int cHomeImage) {
        this.cHomeImage = cHomeImage;
    }

    public String getcHomeName() {
        return cHomeName;
    }

    public void setcHomeName(String cHomeName) {
        this.cHomeName = cHomeName;
    }
}
