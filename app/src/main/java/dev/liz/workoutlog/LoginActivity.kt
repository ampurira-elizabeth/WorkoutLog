package dev.liz.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var btnLogin:Button
    lateinit var tilEmail:TextInputLayout
    lateinit var tilPassword:TextInputLayout
    lateinit var etMail:TextInputEditText
    lateinit var etPassword:TextInputEditText
    lateinit var etSign:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin=findViewById(R.id.btnLogin)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)
        etMail=findViewById(R.id.etMail)
        etPassword=findViewById(R.id.etPassword)
        etSign=findViewById(R.id.etSign)
        etSign.setOnClickListener {
            var intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            ValidateLogin()
//            var intent=Intent(this,HomeActivity::class.java)
//            startActivity(intent)


        }
    }
    fun ValidateLogin(){
        var email=etMail.text.toString()
        var password=etPassword.text.toString()
        var error=false
        if (email.isBlank()) {
            tilEmail.error = "Email is required"
            error=true
        }
        if(password.isBlank()){
            tilPassword.error="Password is required"
            error=true
        }
        if (!error){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }



}