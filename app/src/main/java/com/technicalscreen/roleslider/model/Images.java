package com.technicalscreen.roleslider.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anjali desai on 12-01-2018.
 */

public class Images {

    @SerializedName("size")
    private int imageSize;

    @SerializedName("url")
    private String imageUrl;

    @SerializedName("format")
    private String imageFormat;

    public Images(){

    }

    public Images(int mInageSize, String mImageUrl, String mImageFormat){
        this.imageSize = mInageSize;
        this.imageUrl = mImageUrl;
        this.imageFormat = mImageFormat;
    }

    public int getImageSize() {
        return imageSize;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }
}
