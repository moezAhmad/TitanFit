package com.app.titan_fit.landing;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentMeasurementBinding;
import com.app.titan_fit.ui.calorie.CalorieCalculatorViewModel;
import com.app.titan_fit.ui.muscle.MuscleViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class MeasurementFragment extends Fragment {
    private Context context;
    private FragmentMeasurementBinding binding;
    private MuscleViewModel muscleViewModel;
    private CalorieCalculatorViewModel calorieCalculatorViewModel;
    private Button continueBtn;
    private Button userFltr;
    private TextInputLayout age;
    private TextInputLayout feet;
    private TextInputLayout inches;
    private int[] userCheck = {-1};

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        context = container.getContext();
        binding = FragmentMeasurementBinding.inflate(inflater, container, false);
        muscleViewModel = new ViewModelProvider(requireActivity()).get(MuscleViewModel.class);
        calorieCalculatorViewModel = new ViewModelProvider(requireActivity()).get(CalorieCalculatorViewModel.class);
        View root = binding.getRoot();
        userFltr = binding.userFilter;
        age = binding.age;
        feet= binding.feet;
        inches = binding.inch;
        continueBtn = binding.continueBtn;


        muscleViewModel.getUserType().observe(getViewLifecycleOwner(),s->userFltr.setText(s));
        userFltr.setOnClickListener(view -> setUser());
        continueBtn.setOnClickListener(view -> {
            if(this.age.getEditText().getText().toString().trim().equals("")){
                age.setError("required");
                return;
            }
            if(this.feet.getEditText().getText().toString().trim().equals("")){
                feet.setError("required");
                return;
            }
            if(this.inches.getEditText().getText().toString().trim().equals("")){
                inches.setError("required");
                return;
            }
            setViewModels();
            Navigation.findNavController(view).navigate(R.id.action_measurementFragment_to_workOutRoutuneFragment);
        });
        return root;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void setViewModels(){
        calorieCalculatorViewModel.getAge().setValue(Integer.valueOf(age.getEditText().getText().toString()));
        calorieCalculatorViewModel.getFt().setValue(Integer.valueOf(feet.getEditText().getText().toString()));
        calorieCalculatorViewModel.getInches().setValue(Integer.valueOf(inches.getEditText().getText().toString()));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}