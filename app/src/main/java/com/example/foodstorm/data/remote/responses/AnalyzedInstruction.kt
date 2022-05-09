package com.example.foodstorm.data.remote.responses

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)