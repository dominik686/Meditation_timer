package com.dominikwieczynski.meditationtimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dominikwieczynski.meditationtimer.*
import com.dominikwieczynski.meditationtimer.common.AnimationHelper
import com.dominikwieczynski.meditationtimer.common.Constants
import com.dominikwieczynski.meditationtimer.common.Utils
import com.dominikwieczynski.meditationtimer.databinding.SettingsFragmentBinding
import com.dominikwieczynski.meditationtimer.viewmodels.SettingsViewModel
import com.dominikwieczynski.meditationtimer.viewmodels.SettingsViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class SettingsFragment : Fragment() {


    private val viewModel : SettingsViewModel by viewModels {
        SettingsViewModelFactory(SharedPrefRepository( requireContext()),
            (requireActivity().application as MeditationApplication).meditationRepository)
    }



    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)


        initializeBottomBarNavigation()
        setupCurrentBellSpinner()
        setupResetStatsButtonOnClick()
        setupResetMeditationHistoryButtonOnClick()

        binding.addDefaultValues.setOnClickListener {
                viewModel.addDefaultValues(Locale.getDefault())
        }

        return binding.root
    }


    private fun initializeBottomBarNavigation()
    {
        var bottombar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        var navController = findNavController()

        bottombar.setOnItemSelectedListener { item ->
            findNavController()
            when(item.itemId) {
                R.id.timer_fragment ->{
                    var action = SettingsFragmentDirections.actionSettingsFragmentToTimer()
                    navController.navigate(action)
                }
                R.id.history_fragment ->{
                    var action = SettingsFragmentDirections.actionSettingsFragmentToDisplayMeditationsFragment()
                    navController.navigate(action)
                }
                R.id.statistics_fragment ->{
                    var action = SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment()
                    navController.navigate(action)
                }
            }
            true
        }
    }
    private fun setupCurrentBellSpinner()
    {
        changeDefaultSpinnerValueToCurrentBell()
        setupSpinnerAdapter()
        setupPlayIconClickListener()
        setupCurrentBellChangeListener()
    }
    private fun setupSpinnerAdapter()
    {
       ArrayAdapter.createFromResource(requireContext(), R.array.bell_sounds,
           android.R.layout.simple_spinner_dropdown_item).also { adapter ->
           binding.currentBellTextview.setAdapter(adapter)
       }
    }

    private fun changeDefaultSpinnerValueToCurrentBell()
    {
        when (viewModel.getBellPreference()) {
            Constants.TIBETAN_BELL_PREF -> binding.currentBellTextview.setText(resources.getString(R.string.tibetan_bell))
            Constants.ANALOG_WATCH_BELL_PREF -> binding.currentBellTextview.setText(resources.getString(R.string.analog_watch))
            Constants.FRONT_DESK_BELL_PREF -> binding.currentBellTextview.setText(resources.getString(R.string.front_desk_bell))
            Constants.CARTOON_TELEPHONE_BELL_PREF -> binding.currentBellTextview.setText(resources.getString(R.string.cartoon_telephone))
        }

    }

    private fun setupPlayIconClickListener()
    {
        binding.currentBellSoundDropdownMenu.setEndIconOnClickListener{
            Utils.playBell(requireContext(),
                Constants.BELL_RESOURCES[viewModel.getBellPreference()]!!
            )
        }
    }

    private fun setupCurrentBellChangeListener()
    {
        binding.currentBellTextview.addTextChangedListener{ text ->
            putBellPreference(text.toString())
        }
    }

    private fun putBellPreference(preference : String)
    {
        when(preference)
        {
            resources.getString( R.string.analog_watch) -> viewModel.putBellPreference(Constants.ANALOG_WATCH_BELL_PREF)
            resources.getString( R.string.tibetan_bell) -> viewModel.putBellPreference(Constants.TIBETAN_BELL_PREF)
            resources.getString( R.string.cartoon_telephone) -> viewModel.putBellPreference(
                Constants.CARTOON_TELEPHONE_BELL_PREF)
            resources.getString( R.string.front_desk_bell) -> viewModel.putBellPreference(Constants.FRONT_DESK_BELL_PREF)

        }
    }

    private fun setupResetStatsButtonOnClick()
    {
        binding.resetStatisticsButton.setOnClickListener{
            AnimationHelper.shakeAnimation(it)
            viewModel.resetStatistics()
        }
    }

    private fun setupResetMeditationHistoryButtonOnClick()
    {
           binding.resetMeditationHistoryButton.setOnClickListener{
               lifecycleScope.launch(Dispatchers.IO) {
                   AnimationHelper.shakeAnimation(it)
                   viewModel.resetMeditationHistory()
               }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}