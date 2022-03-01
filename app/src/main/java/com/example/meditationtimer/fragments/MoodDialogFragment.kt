package com.example.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.meditationtimer.R
import android.view.View
import android.view.LayoutInflater

import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.example.meditationtimer.databinding.MoodDialogFragmentBinding
import com.example.meditationtimer.models.MoodEmoji
import com.example.meditationtimer.viewmodels.MoodDialogFragmentViewModel
import com.example.meditationtimer.viewmodels.MoodDialogFragmentViewModelFactory


class MoodDialogFragment(private val listener : MoodChipOnClickListener) : DialogFragment()
{

    private lateinit var builder : AlertDialog.Builder
    private lateinit var dialog : AlertDialog

    private lateinit var animContext : Context
    private lateinit var currentEmoji: MoodEmoji
    private val viewModel : MoodDialogFragmentViewModel by viewModels{
        MoodDialogFragmentViewModelFactory()
    }


    private var _binding : MoodDialogFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onAttach(context: Context) {
        super.onAttach(context)
        animContext = context
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        initializeBuilder()
        setDialogTitle()
        setEmptyOKButtonListener()

        initializeBinding()
        builder.setView(binding.root)


        setupMoodEmojis()
        createDialog()
        showDialog()
        replaceEmptyOKButtonListener()
        return dialog
    }


    private fun initializeBuilder()
    {
        builder = AlertDialog.Builder(activity)
    }

    private fun setDialogTitle()
    {
        builder.setTitle("Describe your mood")
    }


    private fun setEmptyOKButtonListener()
    {
        builder.setPositiveButton(
            resources.getString(R.string.ok)
        ) { _, _ ->
        }
    }

    private fun initializeBinding()
    {
        _binding = MoodDialogFragmentBinding.inflate(LayoutInflater.from(context))

    }


    private fun createDialog()
    {
        dialog =  builder.create()
    }
    private fun showDialog()
    {
        dialog.show()
    }


    private fun setupMoodEmojis()
    {
        setupNegativeEmojis()
        setupNeutralEmojis()
        setupPositiveEmojis()
    }

    private fun setupNegativeEmojis()
    {
        setVeryBadChipClickListener(binding.veryBadChip)
        setBadChipClickListener(binding.badChip)
    }
    private fun setVeryBadChipClickListener(veryBadChip: View)
    {
        veryBadChip.setOnClickListener  {
            bounceAnimation(it)
            viewModel.setCurrentEmojiToVeryBad()
        }
    }
    private fun bounceAnimation(view : View)
    {
        val animation = AnimationUtils.loadAnimation(animContext, R.anim.bounce)
        view.startAnimation(animation)
    }
    private fun setBadChipClickListener(badChip: View)
    {
        badChip.setOnClickListener  {
            bounceAnimation(it)
            viewModel.setCurrentEmojiToBad()
        }
    }

    private fun setupNeutralEmojis()
    {
        setNeutralChipClickListener(binding.neutralChip)
    }

    private fun setNeutralChipClickListener(neutralChip: View)
    {
        neutralChip.setOnClickListener  {
            bounceAnimation(it)
            viewModel.setCurrentEmojiToNeutral()
        }
    }
   private fun setupPositiveEmojis()
    {
        setGoodChipClickListener(binding.goodChip)
        setGreatChipClickListener(binding.greatChip)
   }


    private fun setGoodChipClickListener(goodChip : View)
    {
        goodChip.setOnClickListener  {
            bounceAnimation(it)
            viewModel.setCurrentEmojiToGood()
        }
    }

    private fun setGreatChipClickListener(greatChip : View)
    {
        greatChip.setOnClickListener  {
            bounceAnimation(it)
            viewModel.setCurrentEmojiToGreat()
        }
    }

    private fun replaceEmptyOKButtonListener()
    {
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            if (isEmojiSelected()) {
                finishDialog()
            }
            else
                shakeAnimation(binding.chipGroup)
        }
    }
    private fun isEmojiSelected() : Boolean
    {
        return binding.chipGroup.checkedChipId != -1
    }
    private fun finishDialog()
    {
        listener.onOkButtonPressed(currentEmoji, binding.descriptionEdittext.toString())
        dismiss()
    }
    private fun shakeAnimation(view : View)
    {
        val animation = AnimationUtils.loadAnimation(animContext, R.anim.shake)
        view.startAnimation(animation)
    }


}

interface MoodChipOnClickListener
{
    fun onOkButtonPressed(emoji : MoodEmoji, description : String)
}