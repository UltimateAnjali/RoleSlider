package com.technicalscreen.roleslider.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anjali desai on 15-01-2018.
 */

public class Images {

    @SerializedName("size")
    private int imageSize;

    @SerializedName("url")
    private String imageUrl;

    @SerializedName("format")
    private String imageFormat;

    String name;

    //Constructor
    public Images(){

    }

    //Constructor with paramters
    public Images(int mInageSize, String mImageUrl, String mImageFormat, String name){
        this.imageSize = mInageSize;
        this.imageUrl = mImageUrl;
        this.imageFormat = mImageFormat;
        this.name = name;
    }

    //Getters
    public int getImageSize() {
        return imageSize;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public String getName(){
        return name;
    }

    //Setters
    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public void setName(String name){this.name = name;}
}
