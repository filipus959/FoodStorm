package com.example.foodstorm.screens.mainscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodstorm.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.foodstorm.data.remote.responses.Result


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel(){

    private val _foodList : MutableLiveData<MutableList<Result>> = MutableLiveData(mutableListOf())
    val foodList : MutableLiveData<MutableList<Result>> = _foodList
    var text by mutableStateOf("")
    var isLoading = mutableStateOf(true)

//    init {
//        getRecipesList()
//    }

    fun getRecipesList() {
        isLoading.value = true
        viewModelScope.launch {
            _foodList.postValue(repository.getFoodList(text).data?.results as MutableList<Result>)
        }
        isLoading.value = false
    }



}