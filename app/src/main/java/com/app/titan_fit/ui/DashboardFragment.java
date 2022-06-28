package com.app.titan_fit.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentDashboardBinding;
import com.app.titan_fit.ui.calorie.CalorieCalculatorViewModel;
import com.app.titan_fit.ui.macro.MacroCalculatorViewModel;
import com.app.titan_fit.ui.muscle.MuscleViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class DashboardFragment extends Fragment {

    FirebaseUser user;
    private FragmentDashboardBinding binding;
    private MuscleViewModel muscleViewModel;
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    private MacroCalculatorViewModel macroCalculatorViewModel;
    private Button userFltr;
    private Context context;
    private TextView calories;
    private TextView carbs;
    private TextView proteins;
    private TextView fats;

    private TextView name;
    private TextView email;
    private TextView age;
    private TextView weight;
    private TextView height;
    private int[] userCheck = {-1};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        user = FirebaseAuth.getInstance().getCurrentUser();
        context = container.getContext();
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userFltr = binding.userFilter;
        calories = binding.calories;
        carbs = binding.carbs;
        proteins = binding.proteins;
        fats = binding.fats;

        name = binding.name;
        email = binding.email;
        age = binding.age;
        weight = binding.weight;
        height = binding.height;
        calorieCalculatorViewModel = new ViewModelProvider(requireActivity()).get(CalorieCalculatorViewModel.class);
        muscleViewModel = new ViewModelProvider(requireActivity()).get(MuscleViewModel.class);
        macroCalculatorViewModel = new ViewModelProvider(requireActivity()).get(MacroCalculatorViewModel.class);
        setViews();
        userFltr.setOnClickListener(view -> setUser());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void setUser(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setTitle("Choose an item");
        final String[] listItems = new String[]{
                "Male",
                "Female"
        };
        alertDialog.setSingleChoiceItems(listItems, userCheck[0], (dialogInterface, i) -> {
            userCheck[0] = i;
            switch (i){
                case 0:
                    muscleViewModel.getUserType().setValue(AppConstants.MALE_USER);
                    break;
                case 1:
                    muscleViewModel.getUserType().setValue(AppConstants.FEMALE_USER);
                    break;
            }
            dialogInterface.dismiss();
        });
        AlertDialog customAlertDialog = alertDialog.create();
        customAlertDialog.show();
    }
    private void setViews(){
        macroCalculatorViewModel.getCalories().observe(getViewLifecycleOwner(),s->calories.setText(String.valueOf(s) + 'g'));
        macroCalculatorViewModel.getCarbs().observe(getViewLifecycleOwner(),s->carbs.setText(String.valueOf(s) + 'g'));
        macroCalculatorViewModel.getProteins().observe(getViewLifecycleOwner(),s->proteins.setText(String.valueOf(s) + 'g'));
        macroCalculatorViewModel.getFats().observe(getViewLifecycleOwner(),s->fats.setText(String.valueOf(s) + 'g'));
        muscleViewModel.getName().observe(getViewLifecycleOwner(),s->name.setText(String.valueOf(s)));
        muscleViewModel.getUserType().observe(getViewLifecycleOwner(),s->userFltr.setText(s));
        email.setText(user.getEmail());
        calorieCalculatorViewModel.getAge().observe(getViewLifecycleOwner(),s->age.setText(String.valueOf(s) + " years"));
        calorieCalculatorViewModel.getWeight().observe(getViewLifecycleOwner(),s->weight.setText(String.valueOf(s) + " Kg"));
        String ft = calorieCalculatorViewModel.getFt().getValue().toString();
        String in = calorieCalculatorViewModel.getInches().getValue().toString();
        height.setText(ft+"'"+in+"''");
    }
}