package com.example.foodstorm.recipedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodstorm.data.remote.responses.RecipeDetails
import com.example.foodstorm.data.remote.responses.Result
import com.example.foodstorm.repository.FoodRepository
import com.example.foodstorm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val repository : FoodRepository
) : ViewModel(){


    suspend fun getRecipeDetails(id : Int) : Resource<RecipeDetails> {
            return repository.getDetailedRecipe(id)
        }
}

