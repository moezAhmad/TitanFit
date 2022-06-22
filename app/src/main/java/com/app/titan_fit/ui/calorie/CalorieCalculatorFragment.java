package com.app.titan_fit.ui.calorie;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.app.titan_fit.AppConstants;
import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentCalorieCalculatorBinding;
import com.app.titan_fit.ui.muscle.MuscleViewModel;
import com.google.android.material.slider.RangeSlider;

import java.util.Objects;


public class CalorieCalculatorFragment extends Fragment {
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    private MuscleViewModel muscleViewModel;
    private Context context;
    private FragmentCalorieCalculatorBinding binding;
    private RangeSlider ageSlider;
    private RangeSlider weightSlider;
    private RangeSlider ftSlider;
    private RangeSlider inchSlider;
    private Button exerciseFltr;
    private Button weightFltr;
    private Button calculate;
    private final int[] weightCheck = {-1};
    private final int[] exerciseCheck = {-1};




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        assert container != null;
        context = container.getContext();
        binding = FragmentCalorieCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ageSlider = binding.ageSlider;
        weightSlider = binding.weightSlider;
        ftSlider = binding.ftSlider;
        inchSlider = binding.inchSlider;
        exerciseFltr = binding.exerciseFltr;
        weightFltr = binding.weightFilter;
        calculate = binding.calculateBtn;

        //View Models
        calorieCalculatorViewModel = new
                ViewModelProvider(requireActivity()).get(CalorieCalculatorViewModel.class);
        muscleViewModel = new
                ViewModelProvider(requireActivity()).get(MuscleViewModel.class);
        //Listeners
        ageSlider.addOnChangeListener((slider, value, fromUser) -> calorieCalculatorViewModel.getAge().setValue((int)value));
        weightSlider.addOnChangeListener((slider, value, fromUser) -> calorieCalculatorViewModel.getWeight().setValue((int)value));
        ftSlider.addOnChangeListener((slider, value, fromUser) -> calorieCalculatorViewModel.getFt().setValue((int) value));
        inchSlider.addOnChangeListener((slider, value, fromUser) -> calorieCalculatorViewModel.getInches().setValue((int)value));
        exerciseFltr.setOnClickListener(view -> setExerciseFilter());
        weightFltr.setOnClickListener(view -> setWeightFilter());
        calculate.setOnClickListener(view -> {
            calculateCalories();
            Navigation.findNavController(view).navigate(R.id.action_calorieCalculatorFragment_to_calorieResultFragment);
        });
        //Observers
        calorieCalculatorViewModel.getAge().observe(getViewLifecycleOwner(),s-> ageSlider.setValues(Float.valueOf(s)));
        calorieCalculatorViewModel.getWeight().observe(getViewLifecycleOwner(),s-> weightSlider.setValues(Float.valueOf(s)));
        calorieCalculatorViewModel.getFt().observe(getViewLifecycleOwner(),s-> ftSlider.setValues(Float.valueOf(s)));
        calorieCalculatorViewModel.getInches().observe(getViewLifecycleOwner(),s-> inchSlider.setValues(Float.valueOf(s)));
        calorieCalculatorViewModel.getWeightFltr().observe(getViewLifecycleOwner(), s-> weightFltr.setText(s));
        calorieCalculatorViewModel.getExerciseFltr().observe(getViewLifecycleOwner(), s-> exerciseFltr.setText(s));
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setExerciseFilter(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setTitle("Choose an item");
        final String[] listItems = new String[]{
                "Little to no exercise ",
                "Little exercise (1-3 days per week",
                "Moderate exercise (3-5 days per week)",
                "Heavy exercise (6-7 days per week)",
                "Very heavy exercise (twice per week)"};
        alertDialog.setSingleChoiceItems(listItems, exerciseCheck[0], (dialogInterface, i) -> {
           exerciseCheck[0] = i;
            switch (i){
                case 0:
                    calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_1); break;
                case 1:
                    calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_2); break;
                case 2:
                    calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_3); break;
                case 3:
                    calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_4); break;
                case 4:
                    calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_5); break;
            }
           dialogInterface.dismiss();
        });
        AlertDialog customAlertDialog = alertDialog.create();
        customAlertDialog.show();
    }
    private void setWeightFilter(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setTitle("Choose an item");
        final String[] listItems = new String[]{
                "Lose 2 Pounds per week",
                "Lose 1.5 Pounds per week",
                "Lose 1 Pound per week",
                "Lose 0.5 Pound per week",
                "Stay the same weight",
                "Gain 0.5 Pound per week",
                "Gain 1 Pound per week",
                "Gain 1.5 Pounds per week",
                "Gain 2 Pounds per week"};
        alertDialog.setSingleChoiceItems(listItems, weightCheck[0], (dialogInterface, i) -> {
            weightCheck[0] = i;
            switch (i){
                case 0:
                    calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_1); break;
                case 1:
                    calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_2); break;
                case 2:
                    calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_3); break;
                case 3:
                    calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_4); break;
                case 4:
                    calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_5); break;
                case 5:
                    calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_6); break;
                case 6:
                    calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_7); break;
                case 7:
                    calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_8); break;
                case 8:
                    calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_9); break;

            }
            dialogInterface.dismiss();
        });
        AlertDialog customAlertDialog = alertDialog.create();
        customAlertDialog.show();
    }
    private void calculateCalories(){
        int age = Objects.requireNonNull(calorieCalculatorViewModel.getAge().getValue());
        int weight = Objects.requireNonNull(calorieCalculatorViewModel.getWeight().getValue());
        double height = (Objects.requireNonNull(calorieCalculatorViewModel.getFt().getValue()) * 30.48)
                      + (Objects.requireNonNull(calorieCalculatorViewModel.getInches().getValue()) * 2.54);

        Toast.makeText(context, age + "  "+weight + "  " + height, Toast.LENGTH_SHORT).show();
        double calories = 0;
        switch (Objects.requireNonNull(muscleViewModel.getUserType().getValue())){
            case AppConstants.MALE_USER:
                calories = (10 * weight)  + (6.25 * height) - (5 * age)  + 5;
                break;
            case AppConstants.FEMALE_USER:
                calories = (10 * weight)  + (6.25 * height) - (5 * age)  - 161;
                break;
        }
        Toast.makeText(context, age + "  "+weight + "  " + height + "  " + calories, Toast.LENGTH_SHORT).show();

        switch (Objects.requireNonNull(calorieCalculatorViewModel.getExerciseFltr().getValue())){
            case AppConstants.EXERCISE_1:
                calories *= 1.2;
                break;
            case AppConstants.EXERCISE_2:
                calories *= 1.55;
                break;
            case AppConstants.EXERCISE_3:
                calories *= 1.85;
                break;
            case AppConstants.EXERCISE_4:
                calories *= 2.2;
                break;
            case AppConstants.EXERCISE_5:
                calories *= 2.4;
                break;
        }
        switch (Objects.requireNonNull(calorieCalculatorViewModel.getWeightFltr().getValue())){
            case AppConstants.WEIGHT_1:
                calories -= 2000;
                break;
            case AppConstants.WEIGHT_2:
                calories -= 1500;
                break;
            case AppConstants.WEIGHT_3:
                calories -= 1000;
                break;
            case AppConstants.WEIGHT_4:
                calories -= 500;
                break;
            case AppConstants.WEIGHT_6:
                calories += 500;
                break;
            case AppConstants.WEIGHT_7:
                calories += 1000;
                break;
            case AppConstants.WEIGHT_8:
                calories += 1500;
                break;
            case AppConstants.WEIGHT_9:
                calories += 2000;
                break;
        }
        calorieCalculatorViewModel.getCalories().setValue((int)calories);

    }
}