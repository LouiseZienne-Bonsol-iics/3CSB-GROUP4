package com.project.groupfour;

public class FavoriteConstructor {
    int cFavoriteImage;
    String cFavoriteName;

    public FavoriteConstructor(int cFavoriteImage, String cFavoriteName) {
        this.cFavoriteImage = cFavoriteImage;
        this.cFavoriteName = cFavoriteName;
    }

    public int getcFavoriteImage() {
        return cFavoriteImage;
    }

    public void setcFavoriteImage(int cFavoriteImage) { this.cFavoriteImage = cFavoriteImage; }

    public String getcFavoriteName() {
        return cFavoriteName;
    }

    public void setcFavoriteName(String cFavoriteName) {
        this.cFavoriteName = cFavoriteName;
    }
}

