package com.example.meditationtimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.meditationtimer.databinding.TimerFragmentBinding
import com.example.meditationtimer.fragments.CalendarFragment
import com.example.meditationtimer.fragments.DisplayMeditationsFragment
import com.example.meditationtimer.fragments.TimerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var _binding : TimerFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottombar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        /*
        bottombar.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.history->setCurrentFragment(DisplayMeditationsFragment())
                R.id.timer->setCurrentFragment(TimerFragment())
                R.id.calendar->setCurrentFragment(CalendarFragment())
            }
            true
        }

         */

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupWithNavController(bottombar, navController)
       // setUp
    }

    private fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }

}