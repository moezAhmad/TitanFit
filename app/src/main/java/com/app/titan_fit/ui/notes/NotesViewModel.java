package com.app.titan_fit.ui.notes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotesViewModel extends ViewModel {
    private MutableLiveData<String> notes;

    public NotesViewModel() {
        this.notes = new MutableLiveData<>("");
    }

    public MutableLiveData<String> getNotes() {
        return notes;
    }
}
