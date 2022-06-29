package com.app.titan_fit.ui.meal;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.titan_fit.video_model.VideoModel;

public class MealPlanViewModel extends ViewModel {
    private MutableLiveData<String> screen;

    public MealPlanViewModel() {
        this.screen = new MutableLiveData<>("Food Recommendations");
    }

    public MutableLiveData<String> getScreen() {
        return screen;
    }
}
