package com.app.titan_fit.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentCalorieCalculatorBinding;
import com.app.titan_fit.databinding.FragmentCalorieResultBinding;
import com.app.titan_fit.ui.calorie.CalorieCalculatorViewModel;

public class CalorieResultFragment extends Fragment {
    private FragmentCalorieResultBinding binding;
    private TextView calories;
    private Button finish;
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCalorieResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        calories = binding.calories;
        finish = binding.finishBtn;

        //View Models
        calorieCalculatorViewModel = new ViewModelProvider(requireActivity()).get(CalorieCalculatorViewModel.class);
        //Observers
        calorieCalculatorViewModel.getCalories().observe(getViewLifecycleOwner(), s-> calories.setText(String.valueOf(s)));
        //Listners
        finish.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_calorieResultFragment_to_navigation_muscle);
        });
        return root;
    }
}