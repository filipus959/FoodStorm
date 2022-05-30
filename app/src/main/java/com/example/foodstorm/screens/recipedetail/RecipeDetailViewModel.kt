package com.example.foodstorm.screens.recipedetail

import androidx.lifecycle.ViewModel
import com.example.foodstorm.data.remote.responses.RecipeDetails
import com.example.foodstorm.repository.FoodRepository
import com.example.foodstorm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val repository : FoodRepository
) : ViewModel(){


    suspend fun getRecipeDetails(id : Int) : Resource<RecipeDetails> {
            return repository.getDetailedRecipe(id)
        }
}

