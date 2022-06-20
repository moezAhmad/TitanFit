package com.app.titan_fit.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentMacroResultBinding;

public class MacroResultFragment extends Fragment {
    private FragmentMacroResultBinding binding;
    private Button finish;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMacroResultBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        finish = binding.finishBtn;
        finish.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_macroResultFragment_to_navigation_muscle);
        });
        return root;
    }
}