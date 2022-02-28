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
import com.google.android.material.chip.Chip


class MoodDialogFragment : DialogFragment()
{
    private lateinit var builder : AlertDialog.Builder

    lateinit var animContext : Context

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




        }
        return super.onCreateDialog(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.mood_dialog_fragment, null)

        builder.setView(view)

        val neutralChip = view.findViewById(R.id.neutral_chip) as Chip

        neutralChip.setOnClickListener  {
            bounce(it)
        }


        val badChip = view.findViewById(R.id.bad_chip) as Chip

        badChip.setOnClickListener  {
            bounce(it)
        }

        val veryBadChip = view.findViewById(R.id.very_bad_chip) as Chip

        veryBadChip.setOnClickListener  {
            bounce(it)
        }

        val goodChip = view.findViewById(R.id.good_chip) as Chip

        goodChip.setOnClickListener  {
            bounce(it)
        }
        val greatChip = view.findViewById(R.id.great_chip) as Chip

        greatChip.setOnClickListener  {
            bounce(it)
        }



        builder.create().show()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun bounce(view : View)
    {
       val animation = AnimationUtils.loadAnimation(animContext, R.anim.bounce)
        view.startAnimation(animation)
    }

}