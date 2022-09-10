package dev.liz.workoutlog.repository

import dev.liz.workoutlog.api.ApiClient
import dev.liz.workoutlog.api.ApiInterface
import dev.liz.workoutlog.models.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest)= withContext(Dispatchers.IO){
        val response=apiClient.login(loginRequest)
        return@withContext response
    }

}