package com.app.titan_fit.ui.macro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.titan_fit.AppConstants;

public class MacroCalculatorViewModel extends ViewModel {
    private MutableLiveData<String> diet;
    private MutableLiveData<Integer> calories;
    private MutableLiveData<Integer> meals;
    private MutableLiveData<Integer> carbs;
    private MutableLiveData<Integer> proteins;
    private MutableLiveData<Integer> fats;

    public MacroCalculatorViewModel() {
        diet = new MutableLiveData<>();
        diet.setValue(AppConstants.DIET_2);
        calories = new MutableLiveData<>();
        meals = new MutableLiveData<>();
        carbs = new MutableLiveData<>();
        proteins = new MutableLiveData<>();
        fats = new MutableLiveData<>();
    }

    public MutableLiveData<String> getDiet() {
        return diet;
    }
    public MutableLiveData<Integer> getCalories() { return calories; }
    public MutableLiveData<Integer> getMeals() { return meals; }
    public MutableLiveData<Integer> getCarbs() { return carbs; }
    public MutableLiveData<Integer> getProteins() { return proteins; }
    public MutableLiveData<Integer> getFats() { return fats; }
}
