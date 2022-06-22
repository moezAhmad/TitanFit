package com.app.titan_fit.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentMacroResultBinding;
import com.app.titan_fit.ui.macro.MacroCalculatorViewModel;

public class MacroResultFragment extends Fragment {
    private MacroCalculatorViewModel macroCalculatorViewModel;
    private FragmentMacroResultBinding binding;
    private Button finish;
    private TextView carbs;
    private TextView proteins;
    private TextView fats;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMacroResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        carbs = binding.carbsResult;
        proteins = binding.protiensResult;
        fats = binding.fatsResult;
        finish = binding.finishBtn;
        //View models
        macroCalculatorViewModel = new ViewModelProvider(requireActivity()).get(MacroCalculatorViewModel.class);
        //Observers
        macroCalculatorViewModel.getCarbs().observe(getViewLifecycleOwner(),s->carbs.setText(String.valueOf(s)));
        macroCalculatorViewModel.getProteins().observe(getViewLifecycleOwner(),s->proteins.setText(String.valueOf(s)));
        macroCalculatorViewModel.getFats().observe(getViewLifecycleOwner(),s->fats.setText(String.valueOf(s)));
        //Listeners
        finish.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_macroResultFragment_to_navigation_muscle);
        });
        return root;
    }
}