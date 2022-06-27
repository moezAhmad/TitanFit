package com.app.titan_fit.ui.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private FragmentLoginBinding binding;
    private TextInputLayout email;
    private TextInputLayout password;
    private TextView toSignup;
    private Button login;
    private ProgressBar progressBar;
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
            if(this.email.getEditText().getText().toString().trim().equals("")){
                email.setError("required");
                return;
            }
            if(password.getEditText().getText().toString().trim().equals("")){
                password.setError("required");
                return;
            }
            mAuth.signInWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString())
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(requireActivity(), MainActivity.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(container.getContext(), "Wrong Email or password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        toSignup.setOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_signupFragment);
        });
        return binding.getRoot();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(requireActivity(), MainActivity.class);
            startActivity(intent);
        }
    }
}