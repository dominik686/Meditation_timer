package com.example.meditationtimer.dialogs

import android.content.Context
import android.text.InputType
import android.widget.EditText

class DescriptionDialog(val context : Context, private val listener: SaveDescriptionOKButtonListener)
{
    val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
    lateinit var inputEditText : EditText

     fun buildDialog()
    {
        buildTitle()
        buildInputEditText()
        buildPositiveButton()
    }
    private fun buildTitle()
    {
        builder.setTitle("How do you feel:")
    }
    private fun buildInputEditText()
    {
        inputEditText = EditText(context)
        inputEditText.hint = "Enter text"

        inputEditText.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(inputEditText)
    }

    private fun buildPositiveButton()
    { builder.setPositiveButton("OK") { _, _ ->
        val description = inputEditText.text.toString()
        listener.saveMeditation(description)
    }

    }

     fun show()
    {
        builder.show()
    }
    interface SaveDescriptionOKButtonListener
    {
        fun saveMeditation(description: String)
    }
}

