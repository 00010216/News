package com.example.kcruz.gamenews.models;

public class TopPlayer {
    private int image;
    private String name;
    private String subtitle;

    public TopPlayer() {
    }

    public TopPlayer(int image, String name, String subtitle) {
        this.image = image;
        this.name = name;
        this.subtitle = subtitle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
