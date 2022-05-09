package com.example.foodstorm.data.remote.responses

data class SearchResults(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)