package com.app.titan_fit.ui.meal;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.titan_fit.R;


public class MealPlanFragment extends Fragment {
    private Context context;
    private final int[] screenCheck = {-1};
    private Button changeScreen;
    private ConstraintLayout generalFoodItems;
    private ConstraintLayout foodItems;
    private ConstraintLayout detail_lose_weight;
    private ConstraintLayout detail_gain_weight;
    private MealPlanViewModel mealPlanViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_plan, container, false);
        context = container.getContext();
        mealPlanViewModel = new ViewModelProvider(requireActivity()).get(MealPlanViewModel.class);
        changeScreen = view.findViewById(R.id.plans_filter);
        generalFoodItems = view.findViewById(R.id.generalFoodItems);
        foodItems = view.findViewById(R.id.foodItems);
        detail_lose_weight = view.findViewById(R.id.detail_lose_weight);
        detail_gain_weight = view.findViewById(R.id.detail_gain_weight);
        mealPlanViewModel.getScreen().observe(getViewLifecycleOwner(),s->changeScreen.setText(s));
        changeScreen.setOnClickListener(view1 -> setScreen());
        return view;
    }
    private void setScreen(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setIcon(R.drawable.logo);
        alertDialog.setTitle("Choose an item");
        final String[] listItems = new String[]{
                "General Food Items",
                "Food Recommendations",
                "Meal Details to Lose Weight",
                "Meal Details to Gain Weight"};
        alertDialog.setSingleChoiceItems(listItems, screenCheck[0], (dialogInterface, i) -> {
            screenCheck[0] = i;
            switch (i){
                case 0:
                    generalFoodItems.setVisibility(View.VISIBLE);
                    foodItems.setVisibility(View.GONE);
                    detail_lose_weight.setVisibility(View.GONE);
                    detail_gain_weight.setVisibility(View.GONE);
                    mealPlanViewModel.getScreen().setValue("General Food Items");
                     break;
                case 1:
                    generalFoodItems.setVisibility(View.GONE);
                    foodItems.setVisibility(View.VISIBLE);
                    detail_lose_weight.setVisibility(View.GONE);
                    detail_gain_weight.setVisibility(View.GONE);
                    mealPlanViewModel.getScreen().setValue("Food Recommendations");
                     break;
                case 2:
                    generalFoodItems.setVisibility(View.GONE);
                    foodItems.setVisibility(View.GONE);
                    detail_lose_weight.setVisibility(View.VISIBLE);
                    detail_gain_weight.setVisibility(View.GONE);
                    mealPlanViewModel.getScreen().setValue("Meal Details to Lose Weight");
                     break;
                case 3:
                    generalFoodItems.setVisibility(View.GONE);
                    foodItems.setVisibility(View.GONE);
                    detail_lose_weight.setVisibility(View.GONE);
                    detail_gain_weight.setVisibility(View.VISIBLE);
                    mealPlanViewModel.getScreen().setValue("Meal Details to Gain Weight");
                     break;

            }
            dialogInterface.dismiss();
        });
        AlertDialog customAlertDialog = alertDialog.create();
        customAlertDialog.show();
    }
}