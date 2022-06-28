package com.app.titan_fit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.titan_fit.landing.Landing;
import com.app.titan_fit.ui.calorie.CalorieCalculatorViewModel;
import com.app.titan_fit.ui.macro.MacroCalculatorViewModel;
import com.app.titan_fit.ui.muscle.MuscleViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.app.titan_fit.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ActivityMainBinding binding;
    private BottomNavigationView navView;
    private DrawerLayout drawer;
    private NavigationView navDrawer;
    private Boolean slideState = false;
    private ImageView closeButton;
    private MuscleViewModel muscleViewModel;
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    private MacroCalculatorViewModel macroCalculatorViewModel;
    private SharedPreferences sharedPrefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        mAuth = FirebaseAuth.getInstance();
        muscleViewModel = new ViewModelProvider(this).get(MuscleViewModel.class);
        calorieCalculatorViewModel = new ViewModelProvider(this).get(CalorieCalculatorViewModel.class);
        macroCalculatorViewModel = new ViewModelProvider(this).get(MacroCalculatorViewModel.class);
        setContentView(binding.getRoot());
        navView = binding.bottomNavView;
        drawer = binding.container;
        navDrawer = binding.drawerNavView;
        closeButton = binding.drawerNavView.getHeaderView(0).findViewById(R.id.close_btn);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupWithNavController(navView, navController);
        NavigationUI.setupWithNavController(navDrawer,navController);

        setContentView(binding.getRoot());
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.closeDrawer(Gravity.LEFT);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user = mAuth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(this, Landing.class);
            startActivity(intent);
            return;
        }
        sharedPrefs = getSharedPreferences(user.getEmail(), Context.MODE_PRIVATE);
        String name = sharedPrefs.getString(AppConstants.NAME_PREFS,"");
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
        if(name.equals("")||user.equals("")||weight_fltr.equals("")||exercise_fltr.equals("")||age==-1||weight==-1||feet==-1||inches==-1||calories==-1||
                diet.equals("")||carbs==-1||proteins==-1||fats==-1){
            mAuth.signOut();
            Intent intent = new Intent(this, Landing.class);
            startActivity(intent);
        }else {
            muscleViewModel.getUserType().setValue(user);

            calorieCalculatorViewModel.getWeightFltr().setValue(weight_fltr);
            calorieCalculatorViewModel.getExerciseFltr().setValue(exercise_fltr);
            calorieCalculatorViewModel.getAge().setValue(age);
            calorieCalculatorViewModel.getWeight().setValue(weight);
            calorieCalculatorViewModel.getFt().setValue(feet);
            calorieCalculatorViewModel.getInches().setValue(inches);
            calorieCalculatorViewModel.getCalories().setValue(calories);

            macroCalculatorViewModel.getDiet().setValue(diet);
            macroCalculatorViewModel.getCarbs().setValue(carbs);
            macroCalculatorViewModel.getProteins().setValue(proteins);
            macroCalculatorViewModel.getFats().setValue(fats);
            macroCalculatorViewModel.getCalories().setValue(calories);
        }

    }


}