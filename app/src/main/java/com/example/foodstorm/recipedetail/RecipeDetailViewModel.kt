package com.example.foodstorm.recipedetail

import androidx.lifecycle.ViewModel
import com.example.foodstorm.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val repository : FoodRepository
) : ViewModel(){


}