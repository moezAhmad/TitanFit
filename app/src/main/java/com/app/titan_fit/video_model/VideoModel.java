package com.app.titan_fit.video_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoModel {
    @SerializedName("items")
    @Expose
    private List<Video> items;

    public VideoModel() {
    }
    public VideoModel(List<Video> items) {
        this.items = items;
    }

    public List<Video> getItems() {
        return items;
    }

    public void setItems(List<Video> items) {
        this.items = items;
    }
}
