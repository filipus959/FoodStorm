package com.example.foodstorm

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodstorm.ui.theme.FoodStormTheme
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.foodstorm.data.remote.responses.Result


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController,viewModel : MainScreenViewModel = hiltViewModel())
{
    val foodList : MutableList<Result>? by viewModel.foodList.observeAsState()
Column(modifier = Modifier.fillMaxSize())
{
    Row(modifier = Modifier.fillMaxWidth())
    {
        TextField(value = viewModel.text, onValueChange = {viewModel.text = it} )
        Button(onClick = {
            viewModel.getRecipesList()
        }) {

        }
    }

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
    )
    {
        foodList?.let {
            items(it.count()) { a ->
                Column()
                {
                    AsyncImage(
                        model = foodList?.get(a)?.image,
                        contentDescription = "",
                        modifier = Modifier
                            .size(120.dp)
                            .align(Alignment.CenterHorizontally)
                    )

                    Text(foodList?.get(a)?.title +"fod")
                    //  Text(recipesList[a].title +"recipe")


                }
            }
        }
    }
}




}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodStormTheme {
        val vm : MainScreenViewModel = hiltViewModel()
        MainScreen(rememberNavController(),vm)

    }
}