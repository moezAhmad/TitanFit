package com.app.titan_fit.ui.macro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.titan_fit.AppConstants;

public class MacroCalculatorViewModel extends ViewModel {
    private MutableLiveData<String> diet;

    public MacroCalculatorViewModel() {
        diet = new MutableLiveData<>();
        diet.setValue(AppConstants.DIET_2);
    }

    public MutableLiveData<String> getDiet() {
        return diet;
    }
}
