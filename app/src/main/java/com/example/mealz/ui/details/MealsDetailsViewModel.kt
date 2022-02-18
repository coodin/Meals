package com.example.mealz.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.model.MealsRepository
import com.example.model.response.MealResponse

class MealsDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val repository: MealsRepository = MealsRepository.getInstance()
    var mealState by mutableStateOf<MealResponse?>(null)

    init {
        val mealId = savedStateHandle.get<String>("meal_category_id") ?: ""
        mealState = repository.getMeal(mealId)
    }
}