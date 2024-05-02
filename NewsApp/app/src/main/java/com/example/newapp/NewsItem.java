package com.example.newapp;

public class NewsItem {
    private int imageResId;
    private String headline;
    private String description;

    public NewsItem(int imageResId, String headline, String description) {
        this.imageResId = imageResId;
        this.headline = headline;
        this.description = description;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getHeadline() {
        return headline;
    }

    public String getDescription() {
        return description;
    }
}