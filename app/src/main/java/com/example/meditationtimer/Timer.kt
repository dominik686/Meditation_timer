package com.example.meditationtimer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import com.example.meditationtimer.databinding.TimerFragmentBinding
import com.example.meditationtimer.viewmodels.TimerViewModel

class Timer : Fragment() {

    companion object {
        fun newInstance() = Timer()
    }

    private lateinit var viewModel: TimerViewModel
    private var _binding : TimerFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = TimerFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()
        initializeNumberPicker()
    }

    fun initializeViewModel()
    {
        viewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initializeNumberPicker()
    {
        binding.numberPicker.minValue = 1
        binding.numberPicker.maxValue = 50
    }
}