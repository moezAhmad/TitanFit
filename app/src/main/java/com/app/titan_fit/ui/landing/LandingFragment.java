package com.app.titan_fit.ui.landing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentLandingBinding;

public class LandingFragment extends Fragment {

    private FragmentLandingBinding binding;
    private Button continueBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLandingBinding.inflate(inflater, container, false);
        continueBtn = binding.continueBtn;

        continueBtn.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_LandingFragment_to_loginFragment);
        });
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}