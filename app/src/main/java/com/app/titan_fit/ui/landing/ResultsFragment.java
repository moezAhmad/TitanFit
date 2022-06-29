package com.app.titan_fit.ui.landing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.MainActivity;
import com.app.titan_fit.databinding.FragmentResultsBinding;
import com.app.titan_fit.ui.calorie.CalorieCalculatorViewModel;
import com.app.titan_fit.ui.macro.MacroCalculatorViewModel;
import com.app.titan_fit.ui.muscle.MuscleViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ResultsFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FragmentResultsBinding binding;
    private SharedPreferences sharedPrefs;
    private MuscleViewModel muscleViewModel;
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    private MacroCalculatorViewModel macroCalculatorViewModel;
    private Button continueBtn;
    private Context context;
    private TextView caloriesResult;
    private TextView carbsResult;
    private TextView proteinsResults;
    private TextView fatsResults;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultsBinding.inflate(inflater,container,false);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        muscleViewModel = new ViewModelProvider(requireActivity()).get(MuscleViewModel.class);
        calorieCalculatorViewModel = new ViewModelProvider(requireActivity()).get(CalorieCalculatorViewModel.class);
        macroCalculatorViewModel = new ViewModelProvider(requireActivity()).get(MacroCalculatorViewModel.class);
        context = container.getContext();
        caloriesResult = binding.caloriesResult;
        carbsResult = binding.carbsResult;
        proteinsResults = binding.protiensResult;
        fatsResults = binding.fatsResult;
        View root = binding.getRoot();
        continueBtn= binding.continueBtn;
        sharedPrefs = requireActivity().getSharedPreferences(user.getEmail(), Context.MODE_PRIVATE);
        calculateCalories();
        calculateMacros();
        continueBtn.setOnClickListener(view -> {
            saveData();
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            requireActivity().startActivity(intent);
        });
        calorieCalculatorViewModel.getCalories().observe(getViewLifecycleOwner(),s->caloriesResult.setText(String.valueOf(s)));
        macroCalculatorViewModel.getCarbs().observe(getViewLifecycleOwner(),s->carbsResult.setText(String.valueOf(s)));
        macroCalculatorViewModel.getProteins().observe(getViewLifecycleOwner(),s->proteinsResults.setText(String.valueOf(s)));
        macroCalculatorViewModel.getFats().observe(getViewLifecycleOwner(),s->fatsResults.setText(String.valueOf(s)));
        return root;
    }
    private void calculateCalories(){
        int age = Objects.requireNonNull(calorieCalculatorViewModel.getAge().getValue());
        int weight = Objects.requireNonNull(calorieCalculatorViewModel.getWeight().getValue());
        double height = (Objects.requireNonNull(calorieCalculatorViewModel.getFt().getValue()) * 30.48)
                + (Objects.requireNonNull(calorieCalculatorViewModel.getInches().getValue()) * 2.54);

        double calories = 0;
        switch (Objects.requireNonNull(muscleViewModel.getUserType().getValue())){
            case AppConstants.MALE_USER:
                calories = (10 * weight)  + (6.25 * height) - (5 * age)  + 5;
                break;
            case AppConstants.FEMALE_USER:
                calories = (10 * weight)  + (6.25 * height) - (5 * age)  - 161;
                break;
        }

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
    private void calculateMacros(){
        int calories = Objects.requireNonNull(macroCalculatorViewModel.getCalories().getValue());
        switch (Objects.requireNonNull(macroCalculatorViewModel.getDiet().getValue())){
            case AppConstants.DIET_1:
                macroCalculatorViewModel.getCarbs().setValue((int) ((60 * calories / 100.0)/4.0));
                macroCalculatorViewModel.getProteins().setValue((int) ((25 * calories / 100.0)/4.0));
                macroCalculatorViewModel.getFats().setValue((int)((15 * calories / 100.0)/9.0));
                break;
            case AppConstants.DIET_2:
                macroCalculatorViewModel.getCarbs().setValue((int) ((50 * calories / 100.0)/4.0));
                macroCalculatorViewModel.getProteins().setValue((int) ((30 * calories / 100.0)/4.0));
                macroCalculatorViewModel.getFats().setValue((int)((20 * calories / 100.0)/9.0));
                break;
            case AppConstants.DIET_3:
                macroCalculatorViewModel.getCarbs().setValue((int) ((40 * calories / 100.0)/4.0));
                macroCalculatorViewModel.getProteins().setValue((int) ((30 * calories / 100.0)/4.0));
                macroCalculatorViewModel.getFats().setValue((int)((30 * calories / 100.0)/9.0));
                break;
        }
    }
    private void saveData(){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(AppConstants.NAME_PREFS, Objects.requireNonNull(muscleViewModel.getName().getValue()).trim());
        editor.putString(AppConstants.USER_PREFS, Objects.requireNonNull(muscleViewModel.getUserType().getValue()).trim());
        editor.putString(AppConstants.WEIGHT_FLTR_PREFS, Objects.requireNonNull(calorieCalculatorViewModel.getWeightFltr().getValue()).trim());
        editor.putString(AppConstants.EXERCISE_FLTR_PREFS, Objects.requireNonNull(calorieCalculatorViewModel.getExerciseFltr().getValue()).trim());
        editor.putInt(AppConstants.AGE_PREFS,Objects.requireNonNull(calorieCalculatorViewModel.getAge().getValue()));
        editor.putInt(AppConstants.WEIGHT_PREFS,Objects.requireNonNull(calorieCalculatorViewModel.getWeight().getValue()));
        editor.putInt(AppConstants.FEET_PREFS,Objects.requireNonNull(calorieCalculatorViewModel.getFt().getValue()));
        editor.putInt(AppConstants.INCHES_PREFS,Objects.requireNonNull(calorieCalculatorViewModel.getInches().getValue()));
        editor.putInt(AppConstants.CALORIES_PREFS,Objects.requireNonNull(calorieCalculatorViewModel.getCalories().getValue()));

        editor.putString(AppConstants.DIET_PREFS, Objects.requireNonNull(macroCalculatorViewModel.getDiet().getValue()).trim());
        editor.putInt(AppConstants.CARBS_PREFS,Objects.requireNonNull(macroCalculatorViewModel.getCarbs().getValue()));
        editor.putInt(AppConstants.PROTEINS_PREFS,Objects.requireNonNull(macroCalculatorViewModel.getProteins().getValue()));
        editor.putInt(AppConstants.FATS_PREFS,Objects.requireNonNull(macroCalculatorViewModel.getFats().getValue()));
        editor.apply();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}