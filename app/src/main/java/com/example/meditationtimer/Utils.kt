package com.example.meditationtimer

class Utils
{
    companion object {
        fun createListDivisibleByFive(min: Int, max: Int): MutableList<Int> {
            var result = mutableListOf<Int>()
            for (i in min..max) {
                if (i % 5 == 0)
                    result.add(i)
            }

            return result
        }
    }
}