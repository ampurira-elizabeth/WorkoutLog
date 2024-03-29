package dev.liz.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.liz.workoutlog.databinding.ActivitySignupBinding
import dev.liz.workoutlog.models.RegisterRequest
import dev.liz.workoutlog.models.RegisterResponse
import dev.liz.workoutlog.api.ApiClient
import dev.liz.workoutlog.api.ApiInterface
import dev.liz.workoutlog.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLog2.setOnClickListener {
            var intent=Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {
            validate()
        }
    }

    fun validate(){
        var firstname= binding.etFirstname.text.toString()
        var secondname= binding.etSecondname.text.toString()
        var email= binding.etEmail2.text.toString()
        var password= binding.etPassword2.text.toString()
        var comfirm= binding.etComfirm.text.toString()
        var phone=binding.etPhoneN.text.toString()
        var error=false

        if (firstname.isBlank()) {
            error=true
            binding.tilFirstname.error = "First name required"
        }
        if (phone.isBlank()) {
            error=true
            binding.tilphone.error = "Phone number is required"
        }
        if (secondname.isBlank()) {
            error=true
            binding.tilSecondname.error = "Second name required"
        }
        if (email.isBlank()) {
            error=true
            binding.tilEmail2.error = "Email Address is required"
        }
        if (password.isBlank()) {
            error=true
            binding.tilPassword2.error = "Please enter password"
        }
        if (comfirm!=password) {
            error=true
            binding.tilComfirm.error = "Email does not Match"
        }
        if(!error) {
            val registerRequest = RegisterRequest(firstname,secondname,phone,email,password)
            userViewModel.registerUser(registerRequest)

        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.registerResponseLiveDate.observe(this, Observer { registerResponse->
//            var message= response.body()?.message
            Toast.makeText(baseContext,registerResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this,LoginActivity::class.java))

        })
        userViewModel.registerErrorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()

        })
    }

}