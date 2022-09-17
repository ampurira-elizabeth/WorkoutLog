package dev.liz.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.liz.workoutlog.databinding.ActivityLoginBinding
import dev.liz.workoutlog.models.LoginRequest
import dev.liz.workoutlog.models.LoginResponse
import dev.liz.workoutlog.api.ApiClient
import dev.liz.workoutlog.api.ApiInterface
import dev.liz.workoutlog.util.Constants
import dev.liz.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharePrefs:SharedPreferences
    val userViewModel:UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharePrefs=getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)

        binding.etSign.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            ValidateLogin()
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer{loginResponse ->
            saveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,HomeActivity::class.java))
            finish()
        })
        userViewModel.loginErrorLiveData.observe(this, Observer{ error ->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
        })

    }

    fun ValidateLogin() {
        var email = binding.etMail.text.toString()
        var password = binding.etPassword.text.toString()
        var error = false
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
            error = true
        }
        if (!error) {
            binding.pbLogin.visibility= View.VISIBLE
            val loginRequest=LoginRequest(email,password)
             userViewModel.loginUser(loginRequest)
        }
    }

    fun saveLoginDetails(loginResponse:LoginResponse){
        val editor= sharePrefs.edit()
        val token= "Bearer ${loginResponse.accessToken}"
        editor.putString(Constants.accessToken,token)
        editor.putString(Constants.userId,loginResponse.userId)
        editor.putString(Constants.profileId,loginResponse.profileId)
        editor.apply()


    }
}