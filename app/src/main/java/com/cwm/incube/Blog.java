package com.cwm.incube;

/**
 * Created by Phakin on 1/8/2018.
 */

public class Blog {

    private String image,text ;

    public Blog(){

    }

    public Blog(String image, String text) {
        this.image = image;
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
