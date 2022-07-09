package com.example.meditationtimer.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.meditationtimer.fragments.ChooseTimeDialogFragment
import com.example.meditationtimer.models.MoodEmoji
import java.lang.IllegalArgumentException

class MoodDialogFragmentViewModel : ViewModel()
{
     lateinit var currentEmoji: MoodEmoji

    fun setCurrentEmojiToVeryBad()
    {
        currentEmoji = MoodEmoji.VERY_BAD
    }

    fun setCurrentEmojiToBad()
    {
        currentEmoji = MoodEmoji.BAD
    }

    fun setCurrentEmojiToNeutral()
    {
        currentEmoji = MoodEmoji.NEUTRAL
    }

    fun setCurrentEmojiToGood()
    {
        currentEmoji = MoodEmoji.GOOD
    }

    fun setCurrentEmojiToGreat()
    {
        currentEmoji = MoodEmoji.GREAT
    }

}

class MoodDialogFragmentViewModelFactory : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MoodDialogFragmentViewModel::class.java))
            @Suppress("UNCHECKED_CAST")
             return MoodDialogFragmentViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
