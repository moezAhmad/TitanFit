package com.app.titan_fit.ui.videos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentVideosBinding;

public class VideosFragment extends Fragment {
    private FragmentVideosBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVideosBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        return root;
    }
}