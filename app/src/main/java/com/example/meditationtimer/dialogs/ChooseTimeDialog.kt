package com.example.meditationtimer.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import androidx.fragment.app.DialogFragment

open class ChooseTimeDialog : DialogFragment()
{
    lateinit var valueChangeListener : NumberPicker.OnValueChangeListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val numberPicker =  NumberPicker(activity)

        // Set the maximum and minimum time of meditation (in minutes)
        numberPicker.minValue = 1
        numberPicker.minValue = 120

        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Choose a value")
        builder.setMessage("Choose a number")


        builder.setPositiveButton(
            "OK"
        ) { dialog, which ->
            valueChangeListener.onValueChange(
                numberPicker,
                numberPicker.value, numberPicker.value
            )
        }
        builder.setNegativeButton("CANCEL") {
            dialog, which ->
            valueChangeListener.onValueChange(
                numberPicker,
                numberPicker.value, numberPicker.value)
        }

        builder.setView(numberPicker)

        return builder.create()
    }

    @JvmName("getValueChangeListener1")
    fun getValueChangeListener(): OnValueChangeListener? {
        return valueChangeListener
    }

    @JvmName("setValueChangeListener1")
     fun setValueChangeListener(valueChangeListener: OnValueChangeListener?) {
        this.valueChangeListener = valueChangeListener!!
    }
}