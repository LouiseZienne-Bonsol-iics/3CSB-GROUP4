package com.project.groupfour;

public class HomeConstructor {

    int cHomeImageTL;
    int cHomeImage;
    int cHomeImageTR;
    int cHomeImageBL;
    int cHomeImageB;
    int cHomeImageBR;
    String cHomeName;
    boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public HomeConstructor(int cHomeImageTL, int cHomeImage, int cHomeImageTR, int cHomeImageBL, int cHomeImageB, int cHomeImageBR, String cHomeName) {
        this.cHomeImageTL = cHomeImageTL;
        this.cHomeImage = cHomeImage;
        this.cHomeImageTR = cHomeImageTR;
        this.cHomeImageBL = cHomeImageBL;
        this.cHomeImageB = cHomeImageB;
        this.cHomeImageBR = cHomeImageBR;
        this.cHomeName = cHomeName;
        this.expandable = false;
    }

    public int getcHomeImageTL() {
        return cHomeImageTL;
    }

    public void setcHomeImageTL(int cHomeImageTL) {
        this.cHomeImageTL = cHomeImageTL;
    }

    public int getcHomeImage() {
        return cHomeImage;
    }

    public void setcHomeImage(int cHomeImage) {
        this.cHomeImage = cHomeImage;
    }

    public int getcHomeImageTR() {
        return cHomeImageTR;
    }

    public void setcHomeImageTR(int cHomeImageTR) {
        this.cHomeImageTR = cHomeImageTR;
    }

    public int getcHomeImageBL() {
        return cHomeImageBL;
    }

    public void setcHomeImageBL(int cHomeImageBL) {
        this.cHomeImageBL = cHomeImageBL;
    }

    public int getcHomeImageB() {
        return cHomeImageB;
    }

    public void setcHomeImageB(int cHomeImageB) {
        this.cHomeImageB = cHomeImageB;
    }

    public int getcHomeImageBR() {
        return cHomeImageBR;
    }

    public void setcHomeImageBR(int cHomeImageBR) {
        this.cHomeImageBR = cHomeImageBR;
    }

    public String getcHomeName() {
        return cHomeName;
    }

    public void setcHomeName(String cHomeName) {
        this.cHomeName = cHomeName;
    }

}