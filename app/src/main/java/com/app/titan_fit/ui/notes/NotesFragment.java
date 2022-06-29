package com.app.titan_fit.ui.notes;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.titan_fit.AppConstants;
import com.app.titan_fit.R;
import com.app.titan_fit.databinding.FragmentNotesBinding;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class NotesFragment extends Fragment {
    private FragmentNotesBinding binding;
    private TextInputLayout notes;
    private NotesViewModel notesViewModel;
    private SharedPreferences sharedPrefs;
    private FirebaseUser user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotesBinding.inflate(inflater,container,false);
        notesViewModel = new ViewModelProvider(requireActivity()).get(NotesViewModel.class);
        notes = binding.outlinedTextField;
        notesViewModel.getNotes().observe(getViewLifecycleOwner(),s->notes.getEditText().setText(s));
        user = FirebaseAuth.getInstance().getCurrentUser();
        sharedPrefs = requireActivity().getSharedPreferences(user.getEmail(), Context.MODE_PRIVATE);
        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        SharedPreferences.Editor editor = sharedPrefs.edit();
        notesViewModel.getNotes().setValue(notes.getEditText().getText().toString());
        editor.putString(AppConstants.NOTES_PREFS, Objects.requireNonNull(notesViewModel.getNotes().getValue()));
        editor.apply();
        binding = null;
    }
}