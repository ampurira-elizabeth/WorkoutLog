package dev.liz.workoutlog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.liz.workoutlog.models.LoginRequest
import dev.liz.workoutlog.models.LoginResponse
import dev.liz.workoutlog.models.RegisterRequest
import dev.liz.workoutlog.models.RegisterResponse
import dev.liz.workoutlog.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(){
    val userRepository= UserRepository()
    var loginResponseLiveData=MutableLiveData<LoginResponse>()
    val loginErrorLiveData= MutableLiveData<String?>()

    val registerResponseLiveDate=MutableLiveData<RegisterResponse>()
    val registerErrorLiveData= MutableLiveData<String?>()


    fun loginUser(loginRequest: LoginRequest){
      viewModelScope.launch {
          val response=userRepository.loginUser(loginRequest)
          if (response.isSuccessful){
              loginResponseLiveData.postValue(response.body())
          }
          else{
              val error=response.errorBody()?.string()
              loginErrorLiveData.postValue(error)
          }
      }
    }

    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                registerResponseLiveDate.postValue(response.body())
            }
            else{
                val error = response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }

}