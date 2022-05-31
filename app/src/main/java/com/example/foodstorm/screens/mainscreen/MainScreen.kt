package com.example.foodstorm

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodstorm.ui.theme.FoodStormTheme
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.foodstorm.data.remote.responses.Result
import com.example.foodstorm.screens.mainscreen.MainScreenViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(navController: NavController,viewModel : MainScreenViewModel = hiltViewModel())
{
    val foodList : MutableList<Result>? by viewModel.foodList.observeAsState()
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier
    .fillMaxSize()
    .background(color = colorResource(id = R.color.gray)))
{
    Spacer(modifier = Modifier
        .height(60.dp))
    Text("    FoodStorm - Recipe finder",color = Color.White, fontSize = 30.sp)
    Spacer(modifier = Modifier
        .height(60.dp))
    Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically)
    {
        TextField(value = viewModel.text,onValueChange = {viewModel.text = it}, colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White),label = { Text("Type Recipe you want to find")} )
        Spacer(modifier = Modifier.width(10.dp))
        OutlinedButton(onClick = {
            viewModel.getRecipesList()
            focusManager.clearFocus()
        }) {
            Text("Search")
        }
    }


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
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
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
        Text(entry.title,color = Color.White)
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