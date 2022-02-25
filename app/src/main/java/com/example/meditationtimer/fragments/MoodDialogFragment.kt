package com.example.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.meditationtimer.R
import android.view.View
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton


class MoodDialogFragment : DialogFragment()
{
    private lateinit var builder : AlertDialog.Builder

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = this.layoutInflater

        val dialogView: View = inflater.inflate(R.layout.mood_dialog_fragment, null)

        builder = AlertDialog.Builder(activity)



        val but = dialogView.findViewById(R.id.neutral_emoji) as ImageButton
        but.setOnClickListener{
            YoYo.with(Techniques.Shake)
                .duration(700)
                .repeat(5)
                .playOn(it);
        }


        builder.setView(dialogView)

        builder.create().show()
        return super.onCreateDialog(savedInstanceState)
    }
}