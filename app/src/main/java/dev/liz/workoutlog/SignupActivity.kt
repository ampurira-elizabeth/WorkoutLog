package dev.liz.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.liz.workoutlog.databinding.ActivityLoginBinding
import dev.liz.workoutlog.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvLog2.setOnClickListener {
            var intent=Intent(this,LoginActivity::class.java)
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

        if (firstname.isBlank()) {
            binding.tilFirstname.error = "First name required"
        }
        if (secondname.isBlank()) {
            binding.tilSecondname.error = "Second name required"
        }
        if (email.isBlank()) {
            binding.tilEmail2.error = "Email Address is required"
        }
        if (password.isBlank()) {
            binding.tilPassword2.error = "Please enter password"
        }
        if (comfirm!=password) {
            binding.tilComfirm.error = "Email does not Match"
        }
    }



}