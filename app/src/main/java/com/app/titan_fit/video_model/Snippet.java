package com.app.titan_fit.video_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snippet {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;
    public Snippet(){

    }
    public Snippet(String title, String description, Thumbnails thumbnails) {
        this.title = title;
        this.description = description;
        this.thumbnails = thumbnails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    @Override
    public String toString() {
        return "Snippet{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", thumbnails=" + thumbnails +
                '}';
    }
}
