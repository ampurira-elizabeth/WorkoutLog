package dev.liz.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.liz.workoutlog.R
import dev.liz.workoutlog.databinding.ActivityHomeBinding
import dev.liz.workoutlog.databinding.ActivityLoginBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        castViews()
        setupBottomNav()

        binding.tvLog.setOnClickListener {
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
            val editor= sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN","")
        editor.putString("USER_ID","")
        editor.putString("PROFILE_ID","")
        editor.apply()
        startActivity(Intent(this,LoginActivity::class.java))
    }
    }


//    fun castViews() {
//        binding.fcvHome = findViewById(R.id.fcvHome)
//       binding. bnvHome = findViewById(R.id.bnvHome)
//
//    }

    fun setupBottomNav() {
       binding.bnvHome.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.plan -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, PlanFragment())
                    transaction.commit()
                    true
                }
                R.id.track -> {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, TrackFragment())
                    transaction.commit()
                    true
                }
                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                else -> false
            }

        }
    }
}