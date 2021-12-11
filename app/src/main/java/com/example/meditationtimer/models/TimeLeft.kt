package com.example.meditationtimer.models

data class TimeLeft(var hours : Int, var minutes : Int, var seconds : Int)
{
   constructor(seconds : Int) : this(0, 0, seconds)
   {
       convertSecondsToMinutes()
   }





    private fun convertSecondsToMinutes()
    {
        // Divide  by 3600
        // The product of the division is the hours, subtract that * 3600 from seconds
        minutes = seconds / 60
        seconds -= minutes * 60
    }
}