package com.example.meditationtimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.meditationtimer.databinding.SettingsFragmentBinding
import com.example.meditationtimer.databinding.TimerFragmentBinding
import com.example.meditationtimer.viewmodels.SettingsViewModel
import com.example.meditationtimer.viewmodels.SettingsViewModelFactory
import com.example.meditationtimer.viewmodels.TimerViewModelFactory


// Bell sound choice

class SettingsFragment : Fragment() {


    private val viewModel : SettingsViewModel by viewModels {
        SettingsViewModelFactory()
    }
    private var _binding: SettingsFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SettingsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }
}