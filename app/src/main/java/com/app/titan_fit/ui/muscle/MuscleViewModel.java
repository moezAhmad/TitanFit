package com.app.titan_fit.ui.muscle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MuscleViewModel extends ViewModel {

    private MutableLiveData<String> userType;

    public MuscleViewModel() {
        userType = new MutableLiveData<>();
        userType.setValue("Male");
    }

    public LiveData<String> getText() {
        return userType;
    }
}