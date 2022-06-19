package com.app.titan_fit.ui.muscle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.app.titan_fit.R;
import com.app.titan_fit.AppConstants;
import com.app.titan_fit.databinding.FragmentMuscleBinding;

import java.util.Objects;

public class MuscleFragment extends Fragment {

    private MuscleViewModel muscleViewModel;
    private FragmentMuscleBinding binding;
    private ImageView imgBody;
    private ImageView rotate;
    private Boolean front = true;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        muscleViewModel =
                new ViewModelProvider(this).get(MuscleViewModel.class);

        binding = FragmentMuscleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        imgBody = binding.bodyImage;
        rotate = binding.rotate;

        muscleViewModel.getUserType().observe(getViewLifecycleOwner(), s -> {
            switch (s){
                case AppConstants.MALE_USER:
                    imgBody.setImageResource(R.drawable.male_front);
                    front = true;
                    break;
                case AppConstants.FEMALE_USER:
                    imgBody.setImageResource(R.drawable.female_front);
                    front = true;
                    break;
            }
        });
        rotate.setOnClickListener(view -> {
            if(Objects.equals(muscleViewModel.getUserType().getValue(), AppConstants.MALE_USER)){
                if(front){
                    front = false;
                    imgBody.setImageResource(R.drawable.male_back);
                }else {
                    front = true;
                    imgBody.setImageResource(R.drawable.male_front);
                }
            }
            else if(Objects.equals(muscleViewModel.getUserType().getValue(), AppConstants.FEMALE_USER)){
                if(front){
                    front = false;
                    imgBody.setImageResource(R.drawable.female_back);
                }else {
                    front = true;
                    imgBody.setImageResource(R.drawable.female_front);
                }
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}