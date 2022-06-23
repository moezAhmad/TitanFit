package com.app.titan_fit.landing;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentMeasurementBinding;
import com.app.titan_fit.databinding.FragmentWorkOutRoutuneBinding;
import com.app.titan_fit.ui.calorie.CalorieCalculatorViewModel;
import com.google.android.material.chip.ChipGroup;

public class WorkOutRoutuneFragment extends Fragment {
    private FragmentWorkOutRoutuneBinding binding;
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    private ChipGroup goals;
    private Button continueBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWorkOutRoutuneBinding.inflate(inflater, container, false);
        calorieCalculatorViewModel = new ViewModelProvider(requireActivity()).get(CalorieCalculatorViewModel.class);
        goals = binding.goals;
        goals.setOnCheckedStateChangeListener((group, checkedIds) -> {
            Toast.makeText(container.getContext(), checkedIds.toString() + " " + checkedIds.size(), Toast.LENGTH_SHORT).show();
           if(checkedIds.size()>0){
               switch (checkedIds.get(0)){
                   case 2131231348:
                       calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_1);
                       break;
                   case 2131231346:
                       calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_2);
                       break;
                   case 2131231347:
                       calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_3);
                       break;
                   case 2131231345:
                       calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_4);
                       break;
                   case 2131231350:
                       calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.EXERCISE_5);
                       break;
               }
           }
           else {
               calorieCalculatorViewModel.getExerciseFltr().setValue("");

           }
            Toast.makeText(container.getContext(), calorieCalculatorViewModel.getExerciseFltr().getValue(), Toast.LENGTH_SHORT).show();
        });
        View root = binding.getRoot();
        continueBtn = binding.continueBtn;
        continueBtn.setOnClickListener(view -> {
            if(calorieCalculatorViewModel.getExerciseFltr().getValue().equals("")){
                Toast.makeText(container.getContext(), "Please select from above", Toast.LENGTH_SHORT).show();
                return;
            }
            Navigation.findNavController(view).navigate(R.id.action_workOutRoutuneFragment_to_weightFragment);
        });
        return root;
    }
}