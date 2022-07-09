package com.example.meditationtimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.meditationtimer.databinding.TimerFragmentBinding
import com.example.meditationtimer.fragments.TimerFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottombar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottombar.selectedItemId = R.id.timer_fragment

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController








            //  NavigationUI.setupWithNavController(bottombar, navController)


    }

    private fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navHostFragment, fragment)
            commit()
        }

}