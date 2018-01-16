package com.technicalscreen.roleslider.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by anjali desai on 15-01-2018.
 */

public class PhotoResponse {
    @SerializedName("current_page")
    private int current_page;

    @SerializedName("total_pages")
    private int total_pages;

    @SerializedName("total_items")
    private int total_items;

    @SerializedName("photos")
    private List<Photos> photos;

    //Getters
    public List<Photos> getPhotos() {
        return photos;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_items() {
        return total_items;
    }

    //Setters
    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }
}
