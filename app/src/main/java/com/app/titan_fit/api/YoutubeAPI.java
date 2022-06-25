package com.app.titan_fit.api;

import com.app.titan_fit.video_model.VideoModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class YoutubeAPI {
    public static final String BASE_URL = "https://youtube.googleapis.com/youtube/v3/";
    public static final String PLAY_LIST = "playlistItems?part=snippet&playlistId=";
    public static final String KEY = "&key=AIzaSyCfw4VjfEyIsVOAdefPUiAOt0SDEJOsIcU";


    public interface YoutubeVideo{
        @GET
        Call<VideoModel> getYT(@Url String url);
    }
    private static YoutubeVideo youtubeVideo = null;
    public static YoutubeVideo getYoutubeVideo(){
        if(youtubeVideo==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            youtubeVideo = retrofit.create(YoutubeVideo.class);
        }
        return youtubeVideo;
    }
}
