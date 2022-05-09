package com.example.foodstorm.repository

import com.example.foodstorm.data.remote.FoodApi
import com.example.foodstorm.data.remote.responses.RecipeDetails
import com.example.foodstorm.data.remote.responses.SearchResults
import com.example.foodstorm.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodRepository @Inject constructor(
    private val api : FoodApi
){
    suspend fun getFoodList(query : String) : Resource<SearchResults> {
        val response = try {
            api.getFoodList(query)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    suspend fun getDetailedRecipe(id : Int) : Resource<RecipeDetails> {
        val detailedResponse = try {
            api.getRecipeDetail(id)
        } catch (e : Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(detailedResponse)
    }
}