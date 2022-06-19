package com.app.titan_fit.ui.macro;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentMacroCalculatorBinding;
import com.app.titan_fit.ui.calorie.CalorieCalculatorViewModel;

public class MacroCalculatorFragment extends Fragment {

    private MacroCalculatorViewModel macroCalculatorViewModel;
    private FragmentMacroCalculatorBinding binding;
    private Context context;
    private Button macroFltr;
    private int[] dietCheck = {1};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMacroCalculatorBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = container.getContext();
        macroFltr = binding.macroFltr;
        macroCalculatorViewModel = new
                ViewModelProvider(this).get(MacroCalculatorViewModel.class);
        macroCalculatorViewModel.getDiet().observe(getViewLifecycleOwner(),s->{
            macroFltr.setText(macroCalculatorViewModel.getDiet().getValue());
        });
        macroFltr.setOnClickListener(view -> { setDiet(); });

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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}