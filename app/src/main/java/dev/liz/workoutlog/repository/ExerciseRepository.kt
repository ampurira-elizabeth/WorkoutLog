package dev.liz.workoutlog.repository

import dev.liz.workoutlog.api.ApiClient
import dev.liz.workoutlog.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient= ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun fetchExerciseCategory(accessToken:String)= withContext(Dispatchers.IO){
        return@withContext apiClient.fetchExerciseCategory(accessToken)
    }
}