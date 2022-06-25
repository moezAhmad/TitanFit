package com.app.titan_fit.adapter;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.titan_fit.R;
import com.app.titan_fit.video_model.Video;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Video> videos;

    public VideosAdapter(Context context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }
    class YoutubeHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView title;
        TextView description;
        public YoutubeHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }


        public void setData(Video data) {
            String getTitle = data.getSnippet().getTitle();
            String getDescription = data.getSnippet().getDescription();
            String getThumbnail = data.getSnippet().getThumbnails().getMedium().getUrl();

            Picasso.get()
                    .load(getThumbnail)
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(thumbnail, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d(TAG, "onSuccess: loaded successfully");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.e(TAG, "onError:", e);
                        }
                    });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercise_card,parent,false);
        return new YoutubeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Video video = videos.get(position);
        YoutubeHolder yt = (YoutubeHolder) holder;
        yt.setData(video);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
