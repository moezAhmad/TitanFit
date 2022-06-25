package com.app.titan_fit.video_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails {
    @SerializedName("medium")
    @Expose
    private MediumThumb medium;
    public Thumbnails() {
    }
    public Thumbnails(MediumThumb medium) {
        this.medium = medium;
    }

    public MediumThumb getMedium() {
        return medium;
    }

    public void setMedium(MediumThumb medium) {
        this.medium = medium;
    }

    public class MediumThumb{
        @SerializedName("url")
        @Expose
        private String url;
        public MediumThumb(){

        }
        public MediumThumb(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
