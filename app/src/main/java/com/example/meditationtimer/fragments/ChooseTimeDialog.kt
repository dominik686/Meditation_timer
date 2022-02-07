package com.example.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.NumberPicker.OnValueChangeListener
import androidx.fragment.app.DialogFragment
import com.example.meditationtimer.R
import com.example.meditationtimer.Utils

open class ChooseTimeDialog : DialogFragment()
{
    private lateinit var valueChangeListener : OnValueChangeListener
    private var minValue : Int = 1
    private var maxValue  : Int = 120
    private lateinit var builder : AlertDialog.Builder
    private lateinit var numberPicker : NumberPicker
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
      initializeNumberPicker()



       var  minutes : MutableList<Int> = Utils.createListDivisibleByFive(minValue, maxValue)

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

        createDialogBuilder()
        buildDialogTitle()
        buildBuilderMessage()


        builder.setPositiveButton(
            resources.getString(R.string.ok)
        ) { dialog, which ->
            valueChangeListener.onValueChange(
                numberPicker,
                numberPicker.value, numberPicker.value
            )


        }
        builder.setNegativeButton(resources.getString(android.R.string.cancel)) {
            dialog, which ->
        }

        builder.setView(numberPicker)


        return builder.create()
    }

   private fun initializeNumberPicker()
    {
    numberPicker =  NumberPicker(activity)
    numberPicker.id = R.id.dialog_number_picker
    }
    fun createListOfFiveMinuteIntervals()
    {
        var  minutes : MutableList<Int> = Utils.createListDivisibleByFive(minValue, maxValue)

    }

    fun convertListToString()
    {

    }

    private fun createDialogBuilder()
    {
        builder = AlertDialog.Builder(activity)
    }
    private fun buildDialogTitle()
    {
        builder.setTitle("How many minutes would you like to meditate for:")
    }
    private fun buildBuilderMessage()
    {
        builder.setMessage("Choose a number")
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