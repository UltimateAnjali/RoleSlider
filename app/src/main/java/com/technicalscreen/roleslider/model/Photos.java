package com.technicalscreen.roleslider.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anjali desai on 15-01-2018.
 */

public class Photos {

    @SerializedName("id")
    private int photoId;

    @SerializedName("name")
    private String name;

    @SerializedName("images")
    private List<Images> images;

    //Getters
    public int getPhotoId() {
        return photoId;
    }

    public List<Images> getImages() {
        return images;
    }

    public String getName(){return name;}

    //Setters
    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public void setName(String name){this.name = name;}
}
