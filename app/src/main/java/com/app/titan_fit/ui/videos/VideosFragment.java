package com.app.titan_fit.ui.videos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.titan_fit.R;
import com.app.titan_fit.adapter.VideosAdapter;
import com.app.titan_fit.databinding.FragmentVideosBinding;
import com.app.titan_fit.video_model.Video;

import java.util.ArrayList;
import java.util.List;

public class VideosFragment extends Fragment {
    private FragmentVideosBinding binding;
    private RecyclerView exercises;
    private VideosViewModel videosViewModel;

    private VideosAdapter adapter;
    private LinearLayoutManager manager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVideosBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        videosViewModel = new ViewModelProvider(requireActivity()).get(VideosViewModel.class);
        exercises = binding.exercises;
        adapter = new VideosAdapter(container.getContext(),videosViewModel.getVideos().getValue());
        manager = new LinearLayoutManager(container.getContext());
        exercises.setAdapter(adapter);
        exercises.setLayoutManager(manager);
        return root;
    }
}