package com.app.titan_fit.ui.calorie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.titan_fit.AppConstants;

public class CalorieCalculatorViewModel extends ViewModel {

    private MutableLiveData<String> exerciseFltr;
    private MutableLiveData<String> weightFltr;
    private MutableLiveData<Integer> age;
    private MutableLiveData<Integer> weight;
    private MutableLiveData<Integer> feet;
    private MutableLiveData<Integer> inches;
    private MutableLiveData<Integer> calories;



    public CalorieCalculatorViewModel() {
        exerciseFltr = new MutableLiveData<>();
        weightFltr = new MutableLiveData<>();
        age = new MutableLiveData<>();
        weight = new MutableLiveData<>();
        feet = new MutableLiveData<>();
        inches = new MutableLiveData<>();
        calories = new MutableLiveData<>();
        exerciseFltr.setValue(AppConstants.EXERCISE_1);
        weightFltr.setValue(AppConstants.WEIGHT_5);
    }

    public MutableLiveData<String> getExerciseFltr() {
        return exerciseFltr;
    }
    public MutableLiveData<String> getWeightFltr() {
        return weightFltr;
    }
    public MutableLiveData<Integer> getAge() { return age; }
    public MutableLiveData<Integer> getWeight() { return weight; }
    public MutableLiveData<Integer> getFt() { return feet; }
    public MutableLiveData<Integer> getInches() { return inches; }
    public MutableLiveData<Integer> getCalories() { return calories; }
}