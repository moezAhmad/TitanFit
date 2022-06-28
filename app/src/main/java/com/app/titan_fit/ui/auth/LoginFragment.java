package com.app.titan_fit.ui.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.MainActivity;
import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentLoginBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FragmentLoginBinding binding;
    private TextInputLayout email;
    private TextInputLayout password;
    private TextView toSignup;
    private Button login;
    private ProgressBar progressBar;
    private SharedPreferences sharedPrefs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        email = binding.email;
        password = binding.password;
        login = binding.login;
        progressBar = binding.loading;
        mAuth = FirebaseAuth.getInstance();
        toSignup = binding.toSignup;
        login.setOnClickListener(view -> {
            if (this.email.getEditText().getText().toString().trim().equals("")) {
                email.setError("required");
                return;
            }
            if (password.getEditText().getText().toString().trim().equals("")) {
                password.setError("required");
                return;
            }
            mAuth.signInWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString())
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = mAuth.getCurrentUser();
                                sharedPrefs = requireActivity().getSharedPreferences(user.getEmail(), Context.MODE_PRIVATE);
                                String name = sharedPrefs.getString(AppConstants.NAME_PREFS, "");
                                String user = sharedPrefs.getString(AppConstants.USER_PREFS, "");

                                String weight_fltr = sharedPrefs.getString(AppConstants.WEIGHT_FLTR_PREFS, "");
                                String exercise_fltr = sharedPrefs.getString(AppConstants.EXERCISE_FLTR_PREFS, "");
                                int age = sharedPrefs.getInt(AppConstants.AGE_PREFS, -1);
                                int weight = sharedPrefs.getInt(AppConstants.WEIGHT_PREFS, -1);
                                int feet = sharedPrefs.getInt(AppConstants.FEET_PREFS, -1);
                                int inches = sharedPrefs.getInt(AppConstants.INCHES_PREFS, -1);
                                int calories = sharedPrefs.getInt(AppConstants.CALORIES_PREFS, -1);

                                String diet = sharedPrefs.getString(AppConstants.DIET_PREFS, "");
                                int carbs = sharedPrefs.getInt(AppConstants.CARBS_PREFS, -1);
                                int proteins = sharedPrefs.getInt(AppConstants.PROTEINS_PREFS, -1);
                                int fats = sharedPrefs.getInt(AppConstants.FATS_PREFS, -1);
                                Toast.makeText(requireActivity(), age + " " + weight + " " + feet + " " + inches + " " + diet, Toast.LENGTH_SHORT).show();
                                if (name.equals("")||user.equals("") || weight_fltr.equals("") || exercise_fltr.equals("") || age == -1 || weight == -1 || feet == -1 || inches == -1 || calories == -1 ||
                                        diet.equals("") || carbs == -1 || proteins == -1 || fats == -1) {
                                    Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_measurementFragment);
                                    return;
                                }
                                Intent intent = new Intent(requireActivity(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(container.getContext(), "Wrong Email or password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        toSignup.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment);
        });
        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}