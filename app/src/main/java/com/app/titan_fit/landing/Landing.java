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
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.titan_fit.databinding.ActivityLandingBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Landing extends AppCompatActivity {

    private ActivityLandingBinding binding;
//    private SharedPreferences sharedPrefs;
    private MuscleViewModel muscleViewModel;
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    private MacroCalculatorViewModel macroCalculatorViewModel;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
//        sharedPrefs = getSharedPreferences(AppConstants.SHARED_PREFRENCES, Context.MODE_PRIVATE);
        muscleViewModel = new ViewModelProvider(this).get(MuscleViewModel.class);
        calorieCalculatorViewModel = new ViewModelProvider(this).get(CalorieCalculatorViewModel.class);
        macroCalculatorViewModel = new ViewModelProvider(this).get(MacroCalculatorViewModel.class);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        String user = sharedPrefs.getString(AppConstants.USER_PREFS,"");
//
//        String weight_fltr = sharedPrefs.getString(AppConstants.WEIGHT_FLTR_PREFS,"");
//        String exercise_fltr = sharedPrefs.getString(AppConstants.EXERCISE_FLTR_PREFS,"");
//        int age = sharedPrefs.getInt(AppConstants.AGE_PREFS,-1);
//        int weight = sharedPrefs.getInt(AppConstants.WEIGHT_PREFS,-1);
//        int feet = sharedPrefs.getInt(AppConstants.FEET_PREFS,-1);
//        int inches = sharedPrefs.getInt(AppConstants.INCHES_PREFS,-1);
//        int calories = sharedPrefs.getInt(AppConstants.CALORIES_PREFS,-1);
//
//        String diet = sharedPrefs.getString(AppConstants.DIET_PREFS,"");
//        int carbs = sharedPrefs.getInt(AppConstants.CARBS_PREFS,-1);
//        int proteins = sharedPrefs.getInt(AppConstants.PROTEINS_PREFS,-1);
//        int fats = sharedPrefs.getInt(AppConstants.FATS_PREFS,-1);
//        Toast.makeText(this, age + " " + weight + " " + feet + " " + inches + " " + diet, Toast.LENGTH_SHORT).show();
//        if(!(user.equals("")||weight_fltr.equals("")||exercise_fltr.equals("")||age==-1||weight==-1||feet==-1||inches==-1||calories==-1||
//                diet.equals("")||carbs==-1||proteins==-1||fats==-1)){
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}