package com.example.foodstorm

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
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
    val isLoading by remember { viewModel.isLoading }
Column(modifier = Modifier.fillMaxSize())
{
    Spacer(modifier = Modifier
        .height(120.dp))
    Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically)
    {
        TextField(value = viewModel.text, onValueChange = {viewModel.text = it} )
        OutlinedButton(onClick = {
            viewModel.getRecipesList()
        }) {
            Text("Search")
        }
    }

//    if(isLoading) {
//        CircularProgressIndicator(color = MaterialTheme.colors.primary)
//    }

    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
    )
    {
        foodList?.let {
            items(it.count()) { a ->

               RecipeEntry(entry = foodList!![a], navController = navController)
            }
        }
    }
 }

}


@Composable
fun RecipeEntry(
    entry : Result,
    navController: NavController
) {
    Column()
    {
        AsyncImage(
            model = entry.image,
            contentDescription = "",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    navController.navigate("detailedScreen/${entry.id}")
                }
        )
        Text(entry.title)
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