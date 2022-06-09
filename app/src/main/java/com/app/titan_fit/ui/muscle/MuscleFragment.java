package com.app.titan_fit.ui.muscle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.titan_fit.databinding.FragmentMuscleBinding;

public class MuscleFragment extends Fragment {

    private MuscleViewModel muscleViewModel;
    private FragmentMuscleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        muscleViewModel =
                new ViewModelProvider(this).get(MuscleViewModel.class);

        binding = FragmentMuscleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        muscleViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}