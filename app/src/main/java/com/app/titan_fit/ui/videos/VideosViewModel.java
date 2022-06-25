package com.app.titan_fit.ui.videos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.titan_fit.video_model.Video;

import java.util.List;

public class VideosViewModel extends ViewModel {
    private MutableLiveData<List<Video>> videos;

    public VideosViewModel() {
        this.videos = new MutableLiveData<>();
    }

    public MutableLiveData<List<Video>> getVideos() {
        return videos;
    }
}
