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


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController



        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
          .setEnterAnim(R.anim.from_bottom)
            .setExitAnim(R.anim.to_top)
            .setPopEnterAnim(R.anim.from_bottom)
            .setPopExitAnim(R.anim.to_top)
            .setPopUpTo(navController.graph.startDestinationId, false)
            .build()

        bottombar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.timer_fragment ->{
                    navController.navigate(R.id.timer_fragment, null, options)
                }
                R.id.settings_fragment ->{
                    navController.navigate(R.id.settings_fragment, null, options)

                }
                R.id.history_fragment ->{
                    navController.navigate(R.id.history_fragment, null, options)
                }
                R.id.statistics_fragment ->{
                    navController.navigate(R.id.statistics_fragment, null, options)
                }
            }
            true

        }
        bottombar.setOnItemReselectedListener { item ->
            return@setOnItemReselectedListener
        }


            //  NavigationUI.setupWithNavController(bottombar, navController)


    }

    private fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navHostFragment, fragment)
            commit()
        }

}