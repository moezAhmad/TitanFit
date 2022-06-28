package com.app.titan_fit.ui.auth;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.titan_fit.MainActivity;
import com.app.titan_fit.databinding.FragmentSignupBinding;

import com.app.titan_fit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupFragment extends Fragment {

    private FragmentSignupBinding binding;
    private FirebaseAuth mAuth;
    private TextInputLayout email;
    private TextInputLayout password;
    private Button signup;
    private TextView toSignin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSignupBinding.inflate(inflater, container, false);
        mAuth = FirebaseAuth.getInstance();
        email = binding.email;
        password = binding.password;
        signup = binding.signup;
        toSignin = binding.toSignin;
        signup.setOnClickListener(view -> {
            if(this.email.getEditText().getText().toString().trim().equals("")){
                email.setError("required");
                return;
            }
            if(password.getEditText().getText().toString().trim().equals("")){
                password.setError("required");
                return;
            }
            if(this.password.getEditText().getText().toString().trim().length()<7){
                password.setError("Length must be greater than 6");
                return;
            }
            mAuth.createUserWithEmailAndPassword(email.getEditText().getText().toString(), password.getEditText().getText().toString())
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_measurementFragment);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(container.getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
        toSignin.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_signupFragment_to_loginFragment);
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