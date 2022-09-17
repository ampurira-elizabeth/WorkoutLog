package dev.liz.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.liz.workoutlog.R
import dev.liz.workoutlog.databinding.ActivityHomeBinding
import dev.liz.workoutlog.databinding.ActivityLoginBinding
import dev.liz.workoutlog.util.Constants
import dev.liz.workoutlog.viewmodel.ExerciseViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs:SharedPreferences
    val exerciseViewModel:ExerciseViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()

        binding.tvLog.setOnClickListener {
        sharedPrefs=getSharedPreferences(Constants.prefsFile, MODE_PRIVATE)
            val token= sharedPrefs.getString(Constants.accessToken, Constants.EMPTY_STRING)
                exerciseViewModel.fetchExerciseCategories(token!!)

            val editor= sharedPrefs.edit()
        editor.putString(Constants.accessToken,"")
        editor.putString(Constants.userId,"")
        editor.putString(Constants.profileId,"")
        editor.apply()
        startActivity(Intent(this,LoginActivity::class.java))

    }
    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveDate.observe(this, Observer { exerciseCategories->
            Toast.makeText(baseContext,"fetched ${exerciseCategories.size} categories",Toast.LENGTH_LONG).show()
        })
        exerciseViewModel.errorLiveData.observe(this, Observer { errorMsg->
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()


        })
    }

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