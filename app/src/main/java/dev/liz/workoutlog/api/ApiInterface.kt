package dev.liz.workoutlog.api

import dev.liz.workoutlog.models.LoginRequest
import dev.liz.workoutlog.models.LoginResponse
import dev.liz.workoutlog.models.RegisterRequest
import dev.liz.workoutlog.models.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>
    @POST("/login")
   suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}