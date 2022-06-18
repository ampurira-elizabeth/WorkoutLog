package dev.liz.workoutlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var fcvHome: FragmentContainerView
    lateinit var bnvHome: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        castViews()
    }

    fun castViews() {
        fcvHome = findViewById(R.id.fcvHome)
        bnvHome = findViewById(R.id.bnvHome)
        setupBottomNav()
    }

    fun setupBottomNav() {
        bnvHome.setOnItemSelectedListener { item ->
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