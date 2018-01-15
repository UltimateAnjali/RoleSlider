package com.technicalscreen.roleslider.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anjali desai on 12-01-2018.
 */

public class Photos {

    @SerializedName("id")
    private int photoId;

    @SerializedName("images")
    private List<Images> images;

    //Getters
    public int getPhotoId() {
        return photoId;
    }

    public List<Images> getImages() {
        return images;
    }

    //Setters
    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }
}
