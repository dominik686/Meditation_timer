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

        fun playSound(preference : String)
        {
            if(preference == Constants.CARTOON_TELEPHONE_BELL_PREF)
            {

            }
            else if(preference == Constants.FRONT_DESK_BELL_PREF)
            {

            }
            else if(preference == Constants.ANALOG_WATCH_BELL_PREF)
            {

            }
            else if(preference == Constants.TIBETAN_BELL_PREF)
            {

            }
        }
    }
}