package com.dominikwieczynski.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChooseTimeDialogViewModel : ViewModel() {
    //TODO
    // add two options
    // 5 minute increments
    // and one minute increments
    // make it an option in settings
    private var minTimeInterval: Int = 1
    private var maxTimeInterval: Int = 120
    private var listOfIntervals: ArrayList<String> =
        createListDivisibleByFive(minTimeInterval, maxTimeInterval)

    private fun createListDivisibleByFive(min: Int, max: Int): ArrayList<String> {
        var result = arrayListOf<String>()
        for (number in min..max) {
            if (isDivisibleByFive(number))
                result.add(number.toString())
        }

        return result
    }

    private fun isDivisibleByFive(number: Int): Boolean {
        return number % 5 == 0
    }

    fun getListOfIntervalsSize(): Int {
        return listOfIntervals.size
    }

    fun getListOfIntervalsAsArray(): Array<String> {
        return convertListOfIntervalsToArray()
    }

    private fun convertListOfIntervalsToArray(): Array<String> {
        var array = emptyArray<String>()
        array = listOfIntervals.toArray(array)
        return array
    }


}


class ChooseTimeDialogViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChooseTimeDialogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChooseTimeDialogViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
