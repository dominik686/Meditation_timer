package com.example.meditationtimer.models

data class TimeLeft(var hours : Int, var minutes : Int, var seconds : Int)
{
   constructor(seconds : Int) : this(0, 0, seconds)
   {
       convertSecondsToMinutes()
   }





    private fun convertSecondsToMinutes()
    {
        // Divide  by 60
        // The product of the division is the minutes, subtract that * 60 from seconds
        minutes = seconds / 60
        seconds -= minutes * 60
    }

    override fun toString(): String {
        if(hours < 0)
        {
            if(seconds < 10)
            {
                // Show a zero for seconds  eg. "4:05 instead of 4:5
                return "$hours:$minutes:0$seconds"
            }
            return "$hours:$minutes:$seconds"
        }

        if(seconds < 10)
        {
            // Show a zero for seconds  eg. "4:05 instead of 4:5
            return "$minutes:0$seconds"
        }
        return "$minutes:$seconds"
    }

    // ADD MINUTES AS WELL

    // THIS FUNCTION IS UNFINISHED
}