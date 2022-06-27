package com.app.titan_fit.ui.muscle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.titan_fit.AppConstants;

public class MuscleViewModel extends ViewModel {

    private MutableLiveData<String> userType;
    private MutableLiveData<String> name;
    public MuscleViewModel() {
        userType = new MutableLiveData<>();
        name = new MutableLiveData<>();
        userType.setValue(AppConstants.MALE_USER);
    }

    public MutableLiveData<String> getName() { return name; }

    public MutableLiveData<String> getUserType() {
        return userType;
    }
}