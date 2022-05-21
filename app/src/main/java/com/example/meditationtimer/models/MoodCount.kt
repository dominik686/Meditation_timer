package com.example.meditationtimer.models

import com.github.mikephil.charting.data.PieEntry

data class MoodCount(var great: Int, var good : Int, var neutral : Int, var bad : Int,
                     var veryBad : Int )
{
    fun toPieEntryList() : List<PieEntry>
    {
        var list = listOf(PieEntry(great.toFloat(), "Great"), PieEntry(good.toFloat(), "Good"),
            PieEntry(neutral.toFloat(), "Neutral"), PieEntry(bad.toFloat(), "Bad"),
            PieEntry(veryBad.toFloat(), "Very bad")
        )

        return list.filter { pieEntry -> pieEntry.value > 0  }

    }
}


// Number of great emojis
// Number of good emojis
// Number of neutral emojis\
// number of bad emojis
// number of very bad emojis