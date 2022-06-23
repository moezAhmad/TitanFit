package com.app.titan_fit.landing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.MainActivity;
import com.app.titan_fit.R;
import com.app.titan_fit.ui.calorie.CalorieCalculatorViewModel;
import com.app.titan_fit.ui.macro.MacroCalculatorViewModel;
import com.app.titan_fit.ui.muscle.MuscleViewModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.titan_fit.databinding.ActivityLandingBinding;

public class Landing extends AppCompatActivity {

    private ActivityLandingBinding binding;
    private SharedPreferences sharedPrefs;
    private MuscleViewModel muscleViewModel;
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    private MacroCalculatorViewModel macroCalculatorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPrefs = getSharedPreferences(AppConstants.SHARED_PREFRENCES, Context.MODE_PRIVATE);
        muscleViewModel = new ViewModelProvider(this).get(MuscleViewModel.class);
        calorieCalculatorViewModel = new ViewModelProvider(this).get(CalorieCalculatorViewModel.class);
        macroCalculatorViewModel = new ViewModelProvider(this).get(MacroCalculatorViewModel.class);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String user = sharedPrefs.getString(AppConstants.USER_PREFS,"");

        String weight_fltr = sharedPrefs.getString(AppConstants.WEIGHT_FLTR_PREFS,"");
        String exercise_fltr = sharedPrefs.getString(AppConstants.EXERCISE_FLTR_PREFS,"");
        int age = sharedPrefs.getInt(AppConstants.AGE_PREFS,-1);
        int weight = sharedPrefs.getInt(AppConstants.WEIGHT_PREFS,-1);
        int feet = sharedPrefs.getInt(AppConstants.FEET_PREFS,-1);
        int inches = sharedPrefs.getInt(AppConstants.INCHES_PREFS,-1);
        int calories = sharedPrefs.getInt(AppConstants.CALORIES_PREFS,-1);

        String diet = sharedPrefs.getString(AppConstants.DIET_PREFS,"");
        int carbs = sharedPrefs.getInt(AppConstants.CARBS_PREFS,-1);
        int proteins = sharedPrefs.getInt(AppConstants.PROTEINS_PREFS,-1);
        int fats = sharedPrefs.getInt(AppConstants.FATS_PREFS,-1);
//        Toast.makeText(this, age + " " + weight + " " + feet + " " + inches + " " + diet, Toast.LENGTH_SHORT).show();
        if(!(user.equals("")||weight_fltr.equals("")||exercise_fltr.equals("")||age==-1||weight==-1||feet==-1||inches==-1||calories==-1||
                diet.equals("")||carbs==-1||proteins==-1||fats==-1)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(AppConstants.USER_PREFS,muscleViewModel.getUserType().getValue());
        editor.putString(AppConstants.WEIGHT_FLTR_PREFS,calorieCalculatorViewModel.getWeightFltr().getValue());
        editor.putString(AppConstants.EXERCISE_FLTR_PREFS,calorieCalculatorViewModel.getExerciseFltr().getValue());
        editor.putInt(AppConstants.AGE_PREFS,calorieCalculatorViewModel.getAge().getValue());
        editor.putInt(AppConstants.WEIGHT_PREFS,calorieCalculatorViewModel.getWeight().getValue());
        editor.putInt(AppConstants.FEET_PREFS,calorieCalculatorViewModel.getFt().getValue());
        editor.putInt(AppConstants.INCHES_PREFS,calorieCalculatorViewModel.getInches().getValue());
        editor.putInt(AppConstants.CALORIES_PREFS,calorieCalculatorViewModel.getCalories().getValue());

        editor.putString(AppConstants.DIET_PREFS,"");
        editor.putInt(AppConstants.CARBS_PREFS,-1);
        editor.putInt(AppConstants.PROTEINS_PREFS,-1);
        editor.putInt(AppConstants.FATS_PREFS,-1);
        editor.apply();
    }
}