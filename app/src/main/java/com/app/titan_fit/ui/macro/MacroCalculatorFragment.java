package com.app.titan_fit.ui.macro;

import android.content.Context;
import android.os.Bundle;

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
import com.google.android.material.slider.RangeSlider;

public class MacroCalculatorFragment extends Fragment {

    private MacroCalculatorViewModel macroCalculatorViewModel;
    private FragmentMacroCalculatorBinding binding;
    private Context context;
    private RangeSlider calories_slider;
    private RangeSlider meals_slider;
    private Button macroFltr;
    private Button calculate;
    private int[] dietCheck = {1};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMacroCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = container.getContext();
        calories_slider = binding.calorieSlider;
        meals_slider = binding.mealsSlider;
        macroFltr = binding.macroFltr;
        calculate = binding.calculateBtn;

        macroCalculatorViewModel = new
                ViewModelProvider(this).get(MacroCalculatorViewModel.class);
        //Observers
        macroCalculatorViewModel.getCalories().observe(getViewLifecycleOwner(),s->{ calories_slider.setValues(Float.valueOf(s)); });
        macroCalculatorViewModel.getMeals().observe(getViewLifecycleOwner(),s->meals_slider.setValues(Float.valueOf(s)));
        macroCalculatorViewModel.getDiet().observe(getViewLifecycleOwner(),s->{ macroFltr.setText(s); });

        //Listeners
        calories_slider.addOnChangeListener((slider, value, fromUser) -> macroCalculatorViewModel.getCalories().setValue((int) value));
        meals_slider.addOnChangeListener((slider, value, fromUser) -> macroCalculatorViewModel.getMeals().setValue((int) value));
        macroFltr.setOnClickListener(view -> { setDiet(); });
        calculate.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_macroCalculatorFragment_to_macroResultFragment);
        });

        return root;
    }
    private void setDiet(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setTitle("Choose an item");
        final String[] listItems = new String[]{
                "6/25/15 (High Card)",
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
    private void calculateMacros(){
        
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}