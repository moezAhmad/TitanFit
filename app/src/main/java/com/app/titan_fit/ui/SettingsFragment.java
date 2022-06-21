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
import com.app.titan_fit.databinding.FragmentSettingsBinding;
import com.app.titan_fit.ui.muscle.MuscleViewModel;


public class SettingsFragment extends Fragment {


    private FragmentSettingsBinding binding;
    private MuscleViewModel muscleViewModel;
    private Button userFltr;
    private Context context;
    private int[] userCheck = {-1};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        userFltr = binding.userFilter;
        muscleViewModel =
                new ViewModelProvider(requireActivity()).get(MuscleViewModel.class);
        muscleViewModel.getUserType().observe(getViewLifecycleOwner(), s -> {
            userFltr.setText(s);
        });
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
}