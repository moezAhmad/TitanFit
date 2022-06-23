package com.app.titan_fit.landing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentWeightBinding;
import com.app.titan_fit.ui.calorie.CalorieCalculatorViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class WeightFragment extends Fragment {
    private FragmentWeightBinding binding;
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    private ChipGroup weightFltr;
    private Button continueBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeightBinding.inflate(inflater, container, false);
        calorieCalculatorViewModel = new ViewModelProvider(requireActivity()).get(CalorieCalculatorViewModel.class);
        weightFltr = binding.weightFilter;

        continueBtn = binding.continueBtn;

        weightFltr.setOnCheckedStateChangeListener((group, checkedIds) -> {
            Toast.makeText(container.getContext(), checkedIds.toString() + " " + checkedIds.size() , Toast.LENGTH_SHORT).show();
            if(checkedIds.size()>0){
                switch (checkedIds.get(0)){
                    case 2131230965:
                        calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_1);
                        break;
                    case 2131230966:
                        calorieCalculatorViewModel.getExerciseFltr().setValue(AppConstants.WEIGHT_2);
                        break;
                    case 2131230967:
                        calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_3);
                        break;
                    case 2131230968:
                        calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_4);
                        break;
                    case 2131230969:
                        calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_5);
                        break;
                    case 2131230970:
                        calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_6);
                        break;
                    case 2131230971:
                        calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_7);
                        break;
                    case 2131231353:
                        calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_8);
                        break;
                    case 2131231354:
                        calorieCalculatorViewModel.getWeightFltr().setValue(AppConstants.WEIGHT_9);
                        break;
                }
            }
            else {
                calorieCalculatorViewModel.getWeightFltr().setValue("");

            }
            Toast.makeText(container.getContext(), calorieCalculatorViewModel.getWeightFltr().getValue(), Toast.LENGTH_SHORT).show();
        });
        continueBtn.setOnClickListener(view -> {
            if(calorieCalculatorViewModel.getWeightFltr().getValue().equals("")){
                Toast.makeText(container.getContext(), "Please select from above", Toast.LENGTH_SHORT).show();
                return;
            }
            Navigation.findNavController(view).navigate(R.id.action_weightFragment_to_dietFragment);
        });
        View root = binding.getRoot();

        return root;
    }
}