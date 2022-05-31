package com.example.foodstorm.screens.recipedetail

import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.foodstorm.data.remote.responses.RecipeDetails
import com.example.foodstorm.util.Resource

@Composable
fun RecipeDetailScreen(
    id : Int,
    navController: NavController,
    viewModel : RecipeDetailViewModel = hiltViewModel()
) {
    val entry = produceState<Resource<RecipeDetails>>(initialValue = Resource.Loading()) {
        value = viewModel.getRecipeDetails(id)
    }.value
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
       RecipeDetailStateWrapper(recipeInfo = entry)
    }
}

@Composable
fun RecipeDetailStateWrapper(
    recipeInfo: Resource<RecipeDetails>
) {
    when(recipeInfo) {
        is Resource.Success -> {
            DetailedEntry(entry = recipeInfo.data!!)
        }
        is Resource.Error -> {
            Text("ERROR ${recipeInfo.message}")
        }
        is Resource.Loading -> {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,modifier = Modifier.fillMaxSize()
            )
        }
    }
}




@Composable
fun DetailedEntry(
    entry :RecipeDetails
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center)
    {
        item{
            AsyncImage(
                model = entry.image,
                contentDescription = "",
                modifier = Modifier
                    .size(420.dp)
            )
            Text(entry.title)
            Text("Ready in :"+entry.readyInMinutes.toString()+" Min")
            Html(entry.summary)
            Html("\nINSTRUCTION: \n"+entry.instructions)
        }

    }
}


@Composable
fun Html(text: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            this.setPadding(20,0,20,0)
            setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
        }
    })
}