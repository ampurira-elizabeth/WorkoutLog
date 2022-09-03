package dev.liz.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dev.liz.workoutlog.databinding.ActivityLoginBinding
import dev.liz.workoutlog.models.LoginRequest
import dev.liz.workoutlog.models.LoginResponse
import dev.liz.workoutlog.retrofit.ApiClient
import dev.liz.workoutlog.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharePrefs:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharePrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)

        binding.etSign.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            ValidateLogin()
        }
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

            makeLoginRequest(loginRequest)

        }
    }
    fun makeLoginRequest(loginRequest: LoginRequest){
        var apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        val request=apiClient.login(loginRequest)
        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                binding.pbLogin.visibility= View.GONE

                if (response. isSuccessful){
                    val loginResponse=response.body()
                    saveLoginDetails(loginResponse!!)
                      Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
                      startActivity(Intent(baseContext,HomeActivity::class.java))
                    finish()
                  }
                else{
                    var error=response.errorBody()?.string()
                      Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                  }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin.visibility= View.GONE
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()

            }
        })
    }
    fun saveLoginDetails(loginResponse:LoginResponse){
        val editor= sharePrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()


    }
}