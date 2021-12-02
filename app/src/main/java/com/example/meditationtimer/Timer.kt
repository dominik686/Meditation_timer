package com.example.meditationtimer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.meditationtimer.databinding.TimerFragmentBinding
import com.example.meditationtimer.dialogs.ChooseTimeDialog
import com.example.meditationtimer.viewmodels.TimerViewModel

class Timer : Fragment(), NumberPicker.OnValueChangeListener {

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


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViewModel()
        initializeNumberPicker()
    }

    private fun initializeViewModel()
    {
        viewModel = ViewModelProvider(this).get(TimerViewModel::class.java)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initializeNumberPicker()
    {
      //  binding.numberPicker.minValue = 1
       // binding.numberPicker.maxValue = 50
        val newFragment = ChooseTimeDialog()
        newFragment.setValueChangeListener(this)
        newFragment.show(childFragmentManager, "time picker")
    }

    override fun onValueChange(p0: NumberPicker?, p1: Int, p2: Int) {
        if (p0 != null) {

            //Add one because the list postion started at 0
                p0.value += 1

            Toast.makeText(context,
                "selected number " + p0.value * 5, Toast.LENGTH_SHORT).show()
        };
    }
}