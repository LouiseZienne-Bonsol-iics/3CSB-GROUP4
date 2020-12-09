package com.project.groupfour;

import com.project.groupfour.adapters.ResultsAdapter;

public class ResultsConstructor {
    String name;
    int pic;

    ResultsConstructor(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public ResultsConstructor(String name, int pic){
        this.name = name;
        this.pic = pic;
    }
}
