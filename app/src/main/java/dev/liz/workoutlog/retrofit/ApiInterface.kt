package dev.liz.workoutlog.retrofit

import dev.liz.workoutlog.models.LoginRequest
import dev.liz.workoutlog.models.LoginResponse
import dev.liz.workoutlog.models.RegisterRequest
import dev.liz.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
}