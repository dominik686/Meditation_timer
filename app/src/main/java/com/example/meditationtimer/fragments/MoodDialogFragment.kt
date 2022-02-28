package com.example.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.meditationtimer.R
import android.view.View
import android.view.LayoutInflater
import android.widget.ImageButton

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.meditationtimer.models.MoodEmoji
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text


class MoodDialogFragment(private val listener : MoodChipOnClickListener) : DialogFragment()
{

    private lateinit var builder : AlertDialog.Builder

    private lateinit var animContext : Context
    private lateinit var currentEmoji: MoodEmoji
    private lateinit var chipGroup : ChipGroup
    private lateinit var inputField : TextInputEditText
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

        return super.onCreateDialog(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.mood_dialog_fragment, null)
        inputField = view.findViewById(R.id.description_edittext) as TextInputEditText
        chipGroup = view.findViewById(R.id.chip_group)
        builder.setView(view)

        val neutralChip = view.findViewById(R.id.neutral_chip) as Chip

        neutralChip.setOnClickListener  {
            bounce(it)
            currentEmoji = MoodEmoji.NEUTRAL
        }


        val badChip = view.findViewById(R.id.bad_chip) as Chip

        badChip.setOnClickListener  {
            bounce(it)
            currentEmoji = MoodEmoji.BAD
        }

        val veryBadChip = view.findViewById(R.id.very_bad_chip) as Chip

        veryBadChip.setOnClickListener  {
            bounce(it)
            currentEmoji = MoodEmoji.VERY_BAD
        }

        val goodChip = view.findViewById(R.id.good_chip) as Chip

        goodChip.setOnClickListener  {
            bounce(it)
            currentEmoji = MoodEmoji.GOOD
        }
        val greatChip = view.findViewById(R.id.great_chip) as Chip

        greatChip.setOnClickListener  {
            bounce(it)
            currentEmoji = MoodEmoji.GREAT
        }



       val dialog =  builder.create()
           dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            // Check if all fields were fileld out
            // if yes then call this
            if (chipGroup.checkedChipId != -1)
                listener.onOkButtonPressed(currentEmoji, inputField.text.toString())
            else
                shake(chipGroup)

        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun bounce(view : View)
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