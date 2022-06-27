package com.app.titan_fit.ui.macro;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentMacroCalculatorBinding;
import com.app.titan_fit.ui.muscle.MuscleViewModel;
import com.google.android.material.slider.RangeSlider;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class MacroCalculatorFragment extends Fragment {
    private MuscleViewModel muscleViewModel;
    private MacroCalculatorViewModel macroCalculatorViewModel;
    private FragmentMacroCalculatorBinding binding;
    private Context context;
    private TextInputLayout calories_slider;
    private TextInputLayout meals_slider;
    private Button macroFltr;
    private Button calculate;
    private int[] dietCheck = {1};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMacroCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = container.getContext();
        calories_slider = binding.calorieSlider;
        meals_slider = binding.mealsSlider;
        macroFltr = binding.macroFltr;
        calculate = binding.calculateBtn;
        //View Models
        muscleViewModel = new
                ViewModelProvider(requireActivity()).get(MuscleViewModel.class);
        macroCalculatorViewModel = new
                ViewModelProvider(requireActivity()).get(MacroCalculatorViewModel.class);
        //Observers
        macroCalculatorViewModel.getCalories().observe(getViewLifecycleOwner(),s-> calories_slider.getEditText().setText(Integer.toString(s)));
        macroCalculatorViewModel.getMeals().observe(getViewLifecycleOwner(),s->meals_slider.getEditText().setText(Integer.toString(s)));
        macroCalculatorViewModel.getDiet().observe(getViewLifecycleOwner(),s-> macroFltr.setText(s));

        //Listeners
        macroFltr.setOnClickListener(view -> setDiet());
        calculate.setOnClickListener(view -> {
            calculateMacros(view);
        });

        return root;
    }
    private void setDiet(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setTitle("Choose an item");
        final String[] listItems = new String[]{
                "60/25/15 (High Card)",
                "50/30/20 (Moderate)",
                "40/30/30 (Zone Diet)"};
        alertDialog.setSingleChoiceItems(listItems, dietCheck[0], (dialogInterface, i) -> {
            dietCheck[0] = i;
            switch (i){
                case 0:
                    macroCalculatorViewModel.getDiet().setValue(AppConstants.DIET_1); break;
                case 1:
                    macroCalculatorViewModel.getDiet().setValue(AppConstants.DIET_2); break;
                case 2:
                    macroCalculatorViewModel.getDiet().setValue(AppConstants.DIET_3); break;
            }
            dialogInterface.dismiss();
        });
        AlertDialog customAlertDialog = alertDialog.create();
        customAlertDialog.show();
    }
    private void calculateMacros(View view){
        if(calories_slider.getEditText().getText().toString().trim().equals("")){
            calories_slider.setError("required");
            return;
        }
        if(meals_slider.getEditText().getText().toString().trim().equals("")){
            meals_slider.setError("required");
            return;
        }
        macroCalculatorViewModel.getCalories().setValue(Integer.parseInt(calories_slider.getEditText().getText().toString()));
        macroCalculatorViewModel.getMeals().setValue(Integer.parseInt(meals_slider.getEditText().getText().toString()));
        int calories = Objects.requireNonNull(macroCalculatorViewModel.getCalories().getValue());
        int meals = Objects.requireNonNull(macroCalculatorViewModel.getMeals().getValue());
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
        Navigation.findNavController(view).navigate(R.id.action_macroCalculatorFragment_to_macroResultFragment);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}