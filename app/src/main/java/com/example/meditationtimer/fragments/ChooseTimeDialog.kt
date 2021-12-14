package com.example.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import androidx.fragment.app.DialogFragment
import com.example.meditationtimer.Utils

open class ChooseTimeDialog : DialogFragment()
{
    lateinit var valueChangeListener : NumberPicker.OnValueChangeListener
    var minValue : Int = 1
    var maxValue  : Int = 120

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val numberPicker =  NumberPicker(activity)

        // Set the maximum and minimum time of meditation (in minutes)

       var  minutes : MutableList<Int> = Utils.createListDivisibleByFive(minValue, maxValue)

        // Cast minutes into a type that Number Picker will take (array of strings)
        val arrayList : ArrayList<String> = ArrayList(minutes.size)
        for(i in 0 until minutes.size)
        {
            arrayList.add(minutes[i].toString())
        }
        numberPicker.minValue = 0
        numberPicker.maxValue = minutes.size - 1
        var array = emptyArray<String>()
        array =  arrayList.toArray(array)
        numberPicker.displayedValues = array

        val builder = AlertDialog.Builder(activity)
        builder.setTitle("How many minutes would you like to meditate for:")
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