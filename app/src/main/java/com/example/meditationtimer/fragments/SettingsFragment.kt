package com.example.meditationtimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.example.meditationtimer.Constants
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.SharedPrefRepository
import com.example.meditationtimer.Utils
import com.example.meditationtimer.databinding.SettingsFragmentBinding
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.room.MeditationRepository
import com.example.meditationtimer.viewmodels.SettingsViewModel
import com.example.meditationtimer.viewmodels.SettingsViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// Bell sound choice

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


        setUpBellSoundsOnClicks()
        checkCurrentlyChosenBell()
        setUpResetStatsButtonOnClick()
       setUpResetMeditationHistoryButtonOnClick()



        return binding.root
    }


    private fun checkCurrentlyChosenBell()
    {
        when (viewModel.getBellPreference()) {
            Constants.TIBETAN_BELL_PREF -> binding.bellsGroup.check(binding.tibetanBellsSound.id)
            Constants.ANALOG_WATCH_BELL_PREF -> binding.bellsGroup.check(binding.analogWatchSound.id)
            Constants.FRONT_DESK_BELL_PREF -> binding.bellsGroup.check(binding.frontDeskBells.id)
            Constants.CARTOON_TELEPHONE_BELL_PREF -> binding.bellsGroup.check(binding.cartoonTelephone.id)
        }

    }
    private fun setUpBellSoundsOnClicks()
    {
        setUpTibetanBellOnClick()
        setUpAnalogWatchBellOnClick()
        setUpCartoonTelephoneBellOnClick()
        setUpFrontDeskBellOnClick()
    }
    private fun setUpTibetanBellOnClick()
    {
        val id = View.generateViewId()
        binding.tibetanBellsSound.id = id
        binding.tibetanBellsSound.setOnClickListener {
            if (binding.tibetanBellsSound.isChecked) {


                viewModel.putBellPreference(Constants.TIBETAN_BELL_PREF)
                Utils.playBell(requireContext(),
                    Constants.BELL_RESOURCES[Constants.TIBETAN_BELL_PREF]!!
                )
            }
        }
    }

    private fun setUpAnalogWatchBellOnClick()
    {
        val id = View.generateViewId()
        binding.analogWatchSound.id = id
        binding.analogWatchSound.setOnClickListener {
            if (binding.analogWatchSound.isChecked) {
                viewModel.putBellPreference(Constants.ANALOG_WATCH_BELL_PREF)
                Utils.playBell(requireContext(),
                    Constants.BELL_RESOURCES[Constants.ANALOG_WATCH_BELL_PREF]!!
                )
            }
        }
    }

    private fun setUpCartoonTelephoneBellOnClick()
    {
        val id = View.generateViewId()
        binding.cartoonTelephone.id = id
        binding.cartoonTelephone.setOnClickListener {
            if (binding.cartoonTelephone.isChecked) {

                viewModel.putBellPreference(Constants.CARTOON_TELEPHONE_BELL_PREF)
                 Utils.playBell(requireContext(),
                    Constants.BELL_RESOURCES[Constants.CARTOON_TELEPHONE_BELL_PREF]!!)
            }
        }
    }
    private fun setUpFrontDeskBellOnClick()
    {
        val id = View.generateViewId()
        binding.frontDeskBells.id = id
        binding.frontDeskBells.setOnClickListener {
            if (binding.frontDeskBells.isChecked) {

                // Set as current sound in Shared Preferences
                viewModel.putBellPreference(Constants.FRONT_DESK_BELL_PREF)
                Utils.playBell(requireContext(),
                    Constants.BELL_RESOURCES[Constants.FRONT_DESK_BELL_PREF]!!)
            }
        }
    }

    private fun setUpResetStatsButtonOnClick()
    {
        binding.resetStatisticsButton.setOnClickListener{
            viewModel.resetStatistics()
        }
    }

    private fun setUpResetMeditationHistoryButtonOnClick()
    {
           binding.resetMeditationHistoryButton.setOnClickListener{
               lifecycleScope.launch(Dispatchers.IO) {
                   viewModel.resetMeditationHistory()
               }
        }

    }

    private fun setUpAddDefaultMeditationsButtonOnClickDEBUG()
    {
        binding.addDefaultMeditationsButton.setOnClickListener{

            view
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}