package com.example.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.meditationtimer.R
import android.view.View
import android.view.LayoutInflater

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.meditationtimer.databinding.MoodDialogFragmentBinding
import com.example.meditationtimer.databinding.TimerFragmentBinding
import com.example.meditationtimer.models.MoodEmoji
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText


class MoodDialogFragment(private val listener : MoodChipOnClickListener) : DialogFragment()
{

    private lateinit var builder : AlertDialog.Builder

    private lateinit var animContext : Context
    private lateinit var currentEmoji: MoodEmoji
    private lateinit var chipGroup : ChipGroup
    private lateinit var inputField : TextInputEditText

    private var _binding : MoodDialogFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        animContext = context
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        builder = AlertDialog.Builder(activity)
        builder.setTitle("Describe your mood")

        builder.setPositiveButton(
            resources.getString(R.string.ok)
        ) { _, _ ->
            // Check if all fields were fileld out
            // if yes then call this
           // listener.onOkButtonPressed(currentEmoji, inputField.text.toString())
        }


        _binding = MoodDialogFragmentBinding.inflate(LayoutInflater.from(context))

        inputField = binding.descriptionEdittext
        chipGroup = binding.chipGroup
        builder.setView(binding.root)


        setNeutralChipClickListener(binding.neutralChip)
        setBadChipClickListener(binding.badChip)
        setVeryBadChipClickListener(binding.veryBadChip)
        setGoodChipClickListener(binding.goodChip)
        setGreatChipClickListener(binding.greatChip)



        val dialog =  builder.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            // Check if all fields were fileld out
            // if yes then call this
            if (chipGroup.checkedChipId != -1) {
                listener.onOkButtonPressed(currentEmoji, inputField.text.toString())
                dismiss()
            }
            else
                shake(chipGroup)

        }
        return dialog
    }




    private fun setNeutralChipClickListener(neutralChip: View)
    {
        neutralChip.setOnClickListener  {
            bounceAnimation(it)
            currentEmoji = MoodEmoji.NEUTRAL
        }
    }
   private fun setBadChipClickListener(badChip: View)
    {
        badChip.setOnClickListener  {
            bounceAnimation(it)
            currentEmoji = MoodEmoji.NEUTRAL
        }
    }
    private fun setVeryBadChipClickListener(veryBadChip: View)
    {
        veryBadChip.setOnClickListener  {
            bounceAnimation(it)
            currentEmoji = MoodEmoji.VERY_BAD
        }
    }
    private fun setGoodChipClickListener(goodChip : View)
    {
        goodChip.setOnClickListener  {
            bounceAnimation(it)
            currentEmoji = MoodEmoji.GOOD
        }
    }
    private fun setGreatChipClickListener(greatChip : View)
    {
        greatChip.setOnClickListener  {
            bounceAnimation(it)
            currentEmoji = MoodEmoji.GREAT
        }
    }
    private fun bounceAnimation(view : View)
    {
       val animation = AnimationUtils.loadAnimation(animContext, R.anim.bounce)
        view.startAnimation(animation)
    }
    private fun shake(view : View)
    {
        val animation = AnimationUtils.loadAnimation(animContext, R.anim.shake)
        view.startAnimation(animation)
    }
    // When pressed ok, send (use a custom listener) the currently chosen emoji and description to parent activity as a Meditation object?
}

interface MoodChipOnClickListener
{
    fun onOkButtonPressed(emoji : MoodEmoji, description : String)
}