package com.example.meditationtimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.content.Context
import android.content.SharedPreferences
import com.example.meditationtimer.Constants
import com.example.meditationtimer.databinding.SettingsFragmentBinding
import com.example.meditationtimer.viewmodels.SettingsViewModel
import com.example.meditationtimer.viewmodels.SettingsViewModelFactory


// Bell sound choice

class SettingsFragment : Fragment() {


    private val viewModel : SettingsViewModel by viewModels {
        SettingsViewModelFactory(requireContext())
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

        binding.bellsGroup.check(binding.analogWatchSound.id)
        return binding.root
    }


    private fun checkCurrentlyChosenBell()
    {

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
                test()
            }
        }
    }

    private fun test()
    {
        binding.textView.text = viewModel.getBellPreference()

    }
    private fun setUpAnalogWatchBellOnClick()
    {
        val id = View.generateViewId()
        binding.analogWatchSound.id = id
        binding.analogWatchSound.setOnClickListener {
            if (binding.analogWatchSound.isChecked) {
                viewModel.putBellPreference(Constants.ANALOG_WATCH_BELL_PREF)
                test()

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
                test()

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
                test()

            }
        }
    }





}