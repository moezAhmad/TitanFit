package com.app.titan_fit.ui.calorie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.titan_fit.AppConstants;

public class CalorieCalculatorViewModel extends ViewModel {

    private MutableLiveData<String> exerciseFltr;
    private MutableLiveData<String> weightFltr;
    private int age;
    private int weight;

    public CalorieCalculatorViewModel() {
        exerciseFltr = new MutableLiveData<>();
        weightFltr = new MutableLiveData<>();
        exerciseFltr.setValue(AppConstants.EXERCISE_1);
        weightFltr.setValue(AppConstants.WEIGHT_5);
    }

    public MutableLiveData<String> getExerciseFltr() {
        return exerciseFltr;
    }

    public MutableLiveData<String> getWeightFltr() {
        return weightFltr;
    }
}