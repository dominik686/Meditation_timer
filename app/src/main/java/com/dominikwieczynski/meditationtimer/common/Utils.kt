package com.dominikwieczynski.meditationtimer.common

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper

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

        fun playBell(context: Context, bellResourceID : Int){

            var mp = MediaPlayer.create(context, bellResourceID)

            mp.setOnCompletionListener {
                it.reset()
                it.release()
                mp = null
            }

            mp.start()
            // Pause the sound after two seconds
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    if(mp.isPlaying)
                    {
                        mp.reset()
                        mp.release()
                        mp = null
                    }
                }, 2000)
        }

    }
}