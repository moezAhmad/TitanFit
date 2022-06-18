package com.app.titan_fit.ui.muscle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.titan_fit.User;

public class MuscleViewModel extends ViewModel {

    private MutableLiveData<String> userType;

    public MuscleViewModel() {
        userType = new MutableLiveData<>();
        userType.setValue(User.MALE_USER);
    }

    public LiveData<String> getUserType() {
        return userType;
    }
}