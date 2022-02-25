package com.example.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.meditationtimer.R
import android.view.View
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast


class MoodDialogFragment : DialogFragment()
{
    private lateinit var builder : AlertDialog.Builder


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.mood_dialog_fragment, null)

        builder = AlertDialog.Builder(activity)
        builder.setMessage("Choose a number")
        builder.setTitle("Choose your mood emoji:")
        builder.setView(dialogView)
        val but = dialogView.findViewById(R.id.neutral_emoji) as ImageButton

        but.setOnClickListener  {
            but.isPressed = true }
        builder.create().show()
        return super.onCreateDialog(savedInstanceState)
    }
}