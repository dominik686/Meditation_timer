package com.dominikwieczynski.meditationtimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    lateinit var sharedPrefRepository : SharedPrefRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottombar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottombar.selectedItemId = R.id.timer_fragment

        initializeSharedPrefRepository()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

            //  NavigationUI.setupWithNavController(bottombar, navController)

    }

    private fun initializeSharedPrefRepository()
    {
        sharedPrefRepository = SharedPrefRepository(this)
    }
    override fun onResume() {
        super.onResume()
        updateStreakIfNotZero()
    }

    private fun updateStreakIfNotZero()
    {
        sharedPrefRepository.updateStreakIfNotZero()
    }

    private fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navHostFragment, fragment)
            commit()
        }

}