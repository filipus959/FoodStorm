package com.example.foodstorm.data.remote

import com.example.foodstorm.data.remote.responses.RecipeDetails
import com.example.foodstorm.data.remote.responses.SearchResults

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface FoodApi {
    @GET("/recipes/complexSearch?&number=100")
    suspend fun getFoodList(@Query("query")query: String?): SearchResults
    @GET("recipes/{id}/information?includeNutrition=false&")
    suspend fun getRecipeDetail(@Path("id") id: Int): RecipeDetails
    @GET("/recipes/random?number=10")
    suspend fun getRandomRecipes(): RecipeDetails



}