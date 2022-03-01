package com.example.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.R
import com.example.meditationtimer.Utils
import com.example.meditationtimer.viewmodels.ChooseTimeDialogViewModel
import com.example.meditationtimer.viewmodels.ChooseTimeDialogViewModelFactory
import com.example.meditationtimer.viewmodels.TimerViewModel
import com.example.meditationtimer.viewmodels.TimerViewModelFactory

open class ChooseTimeDialogFragment : DialogFragment() {
    private lateinit var valueChangeListener: OnValueChangeListener

    private val viewModel: ChooseTimeDialogViewModel by viewModels{
        ChooseTimeDialogViewModelFactory()
    }

    private lateinit var builder : AlertDialog.Builder
    private lateinit var numberPicker : NumberPicker
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {



        initializeNumberPicker()
        setMinAndMaxValue()
        setDisplayedValues()

        createDialogBuilder()
        buildDialogTitle()
        buildBuilderMessage()


        buildPositiveButton()
        buildNegativeButton()

        builder.setView(numberPicker)


        return builder.create()
    }


   private fun initializeNumberPicker()
    {
     numberPicker =  NumberPicker(activity)
     numberPicker.id = R.id.dialog_number_picker
    }

    private fun setMinAndMaxValue()
    {
        numberPicker.minValue = 0
        numberPicker.maxValue = viewModel.getListOfIntervalsSize() - 1
    }

    private fun setDisplayedValues()
    {
        val array = viewModel.getListOfIntervalsAsArray()
        numberPicker.displayedValues = array
    }

    private fun createDialogBuilder()
    {
        builder = AlertDialog.Builder(activity)
    }

    private fun buildDialogTitle()
    {
        builder.setTitle("How many minutes would you like to meditate for")
    }

    private fun buildBuilderMessage()
    {
        builder.setMessage("Choose a number")
    }

    private fun buildPositiveButton()
    {
        builder.setPositiveButton(
            resources.getString(R.string.ok)
        ) { _, _ ->
            valueChangeListener.onValueChange(
                numberPicker,
                numberPicker.value, numberPicker.value
            )


        }
    }

    private fun buildNegativeButton()
    {
        builder.setNegativeButton(resources.getString(android.R.string.cancel)) {
                _, _ ->
        }
    }


    fun getValueChangeListener(): OnValueChangeListener? {
        return valueChangeListener
    }


     fun setValueChangeListener(valueChangeListener: OnValueChangeListener?) {
        this.valueChangeListener = valueChangeListener!!
    }
}