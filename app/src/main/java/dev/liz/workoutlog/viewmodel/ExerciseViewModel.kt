package dev.liz.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.liz.workoutlog.models.ExerciseCategory
import dev.liz.workoutlog.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel:ViewModel() {
    val exerciseRepository=ExerciseRepository()
    val exerciseCategoryLiveDate=MutableLiveData<List<ExerciseCategory>>()
    val errorLiveData=MutableLiveData<String?>()

    fun fetchExerciseCategories(accessToken:String){
        viewModelScope.launch {
            val response= exerciseRepository.fetchExerciseCategory(accessToken)
            if (response . isSuccessful){
                exerciseCategoryLiveDate.postValue(response.body())
            }
            else{
                val errorMsg= response.errorBody()?.string()
                errorLiveData.postValue(errorMsg)
            }
        }
    }
}