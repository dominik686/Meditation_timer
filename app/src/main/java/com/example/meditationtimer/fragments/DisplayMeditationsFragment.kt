package com.example.meditationtimer.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.R
import com.example.meditationtimer.databinding.DisplayMeditationsFragmentBinding
import com.example.meditationtimer.viewmodels.DisplayMeditationsViewModel
import com.example.meditationtimer.viewmodels.DisplayMeditationsViewModelFactory

class DisplayMeditationsFragment : Fragment() {

    // Will need to create a viewmodel factory and initialize it the same way i did in TimerViewModel
    companion object {
        fun newInstance() = DisplayMeditationsFragment()
    }

    private val viewModel: DisplayMeditationsViewModel by viewModels{
        DisplayMeditationsViewModelFactory((activity!!.application as MeditationApplication).repository)
    }

    private var _binding : DisplayMeditationsFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DisplayMeditationsFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }



}