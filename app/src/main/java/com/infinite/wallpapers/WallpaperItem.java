package com.infinite.wallpapers;

public class WallpaperItem {
    private String title;
    private String category;
    private String imageUrl;
    private String resolution;

    public WallpaperItem(String title, String category, String imageUrl, String resolution) {
        this.title = title;
        this.category = category;
        this.imageUrl = imageUrl;
        this.resolution = resolution;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getImageUrl() { return imageUrl; }
    public String getResolution() { return resolution; }
}
