package com.app.titan_fit.ui.landing;

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
import com.app.titan_fit.databinding.FragmentDietBinding;
import com.app.titan_fit.ui.macro.MacroCalculatorViewModel;
import com.google.android.material.chip.ChipGroup;

public class DietFragment extends Fragment {
    private FragmentDietBinding binding;
    private MacroCalculatorViewModel macroCalculatorViewModel;
    private ChipGroup diet;
    private Button continueBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDietBinding.inflate(inflater,container,false);
        macroCalculatorViewModel = new ViewModelProvider(requireActivity()).get(MacroCalculatorViewModel.class);
        diet = binding.diet;
        diet.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if(checkedIds.size()>0){
                switch (checkedIds.get(0)){
                    case R.id.zone_diet:
                        macroCalculatorViewModel.getDiet().setValue(AppConstants.DIET_3);
                        break;
                    case R.id.moderate:
                        macroCalculatorViewModel.getDiet().setValue(AppConstants.DIET_2);
                        break;
                    case R.id.high_carb:
                        macroCalculatorViewModel.getDiet().setValue(AppConstants.DIET_1);
                        break;
                }
            }
            else {
                macroCalculatorViewModel.getDiet().setValue("");

            }

        });
        View root = binding.getRoot();
        continueBtn = binding.continueBtn;
        continueBtn.setOnClickListener(view -> {
            if(macroCalculatorViewModel.getDiet().getValue().equals("")){
                Toast.makeText(container.getContext(), "Please select from above", Toast.LENGTH_SHORT).show();
                return;
            }
            Navigation.findNavController(view).navigate(R.id.action_dietFragment_to_resultsFragment);
        });
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}