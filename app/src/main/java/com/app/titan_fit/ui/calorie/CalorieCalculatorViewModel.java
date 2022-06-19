package com.app.titan_fit.ui.calorie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.titan_fit.AppConstants;

public class CalorieCalculatorViewModel extends ViewModel {

    private MutableLiveData<String> exercise;
    private MutableLiveData<String> weight;

    public CalorieCalculatorViewModel() {
        exercise = new MutableLiveData<>();
        weight = new MutableLiveData<>();
        exercise.setValue(AppConstants.EXERCISE_1);
        weight.setValue(AppConstants.WEIGHT_5);
    }

    public MutableLiveData<String> getExercise() {
        return exercise;
    }

    public MutableLiveData<String> getWeight() {
        return weight;
    }
}