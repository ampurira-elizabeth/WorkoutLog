package dev.liz.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class SignupActivity : AppCompatActivity() {
    lateinit var btnSignup:Button
    lateinit var tilFirstname:TextInputLayout
    lateinit var tilSecondname:TextInputLayout
    lateinit var tilEmail2:TextInputLayout
    lateinit var tilComfirm:TextInputLayout
    lateinit var etFirstname:EditText
    lateinit var etSecondname:EditText
    lateinit var etEmail2:EditText
    lateinit var etComfirm:EditText
    lateinit var tvLog2:TextView
    lateinit var tilPassword2:TextInputLayout
    lateinit var etPassword2:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        btnSignup=findViewById(R.id.btnSignup)
        tilFirstname=findViewById(R.id.tilFirstname)
        tilSecondname=findViewById(R.id.tilSecondname)
        tilEmail2=findViewById(R.id.tilEmail2)
        tilComfirm=findViewById(R.id.tilComfirm)
        etFirstname=findViewById(R.id.etFirstname)
        etSecondname=findViewById(R.id.etSecondname)
        etEmail2=findViewById(R.id.etEmail2)
        etComfirm=findViewById(R.id.etComfirm)
        etPassword2=findViewById(R.id.etPassword2)
        tvLog2=findViewById(R.id.tvLog2)
        tilPassword2=findViewById(R.id.tilPassword2)

        tvLog2.setOnClickListener {
            var intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        btnSignup.setOnClickListener {
            validate()
        }
    }
    fun validate(){
        var firstname= etFirstname.text.toString()
        var secondname= etSecondname.text.toString()
        var email= etEmail2.text.toString()
        var password= etPassword2.text.toString()
        var comfirm= etComfirm.text.toString()

        if (firstname.isBlank()) {
            tilFirstname.error = "First name required"
        }
        if (secondname.isBlank()) {
            tilSecondname.error = "Second name required"
        }
        if (email.isBlank()) {
            tilEmail2.error = "Email Address is required"
        }
        if (password.isBlank()) {
            tilPassword2.error = "Please enter password"
        }
        if (comfirm.isBlank()) {
            tilComfirm.error = "Email does not Match"
        }
    }



}