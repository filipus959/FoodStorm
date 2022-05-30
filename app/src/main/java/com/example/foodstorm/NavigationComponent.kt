package com.example.foodstorm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.foodstorm.screens.mainscreen.MainScreenViewModel
import com.example.foodstorm.screens.recipedetail.RecipeDetailScreen
import com.example.foodstorm.screens.recipedetail.RecipeDetailViewModel

@Composable
fun NavigationComponent(navController : NavHostController)
{
    val mainScreenViewModel : MainScreenViewModel = hiltViewModel()
    val recipeDetailViewModel : RecipeDetailViewModel = hiltViewModel()
            NavHost(navController = navController, startDestination = "mainScreen"){
        composable("mainScreen") { MainScreen(navController,mainScreenViewModel)}
        composable("detailedScreen/{id}", arguments =  listOf(
            navArgument("id") {
                type = NavType.IntType
            }
        )){
            val id = remember {
                it.arguments?.getInt("id")
            }
            if (id != null) {
                RecipeDetailScreen(id = id, navController = navController,recipeDetailViewModel)
            }
        }
    }

}