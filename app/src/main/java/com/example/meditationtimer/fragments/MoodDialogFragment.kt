package com.example.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.meditationtimer.R
import android.view.View
import android.view.LayoutInflater
import android.view.ViewAnimationUtils

import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.example.meditationtimer.databinding.MoodDialogFragmentBinding
import com.example.meditationtimer.models.MoodEmoji
import com.example.meditationtimer.viewmodels.MoodDialogFragmentViewModel
import com.example.meditationtimer.viewmodels.MoodDialogFragmentViewModelFactory
import com.google.android.material.chip.ChipGroup
import android.widget.Toast
import androidx.test.core.app.ApplicationProvider

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.meditationtimer.AnimationHelper

import com.google.android.material.chip.Chip
import kotlin.math.hypot


class MoodDialogFragment(private val listener : MoodChipOnClickListener) : DialogFragment()
{

    private lateinit var builder : AlertDialog.Builder
    private lateinit var dialog : AlertDialog

    private lateinit var animContext : Context
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
        initializeBinding()

        setDialogTitle()
        setEmptyOKButtonListener()
        builder.setView(binding.root)
        setupMoodEmojis()
        binding.chipGroup.setOnCheckedChangeListener { _, _ ->
            AnimationHelper.circularReveal(binding.textInputLayout)
            binding.chipGroup.setOnCheckedChangeListener(null)
        }


        createDialog()
        showDialog()
        replaceEmptyOKButtonListener()
        return dialog
    }
    private fun revealAnimation()
    {

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
            AnimationHelper.bounceAnimation(it)
            viewModel.setCurrentEmojiToVeryBad()
        }
    }

    private fun setBadChipClickListener(badChip: View)
    {
        badChip.setOnClickListener  {
            AnimationHelper.bounceAnimation(it)
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
            AnimationHelper.bounceAnimation(it)
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
            AnimationHelper.bounceAnimation(it)
            viewModel.setCurrentEmojiToGood()
        }
    }

    private fun setGreatChipClickListener(greatChip : View)
    {
        greatChip.setOnClickListener  {
            AnimationHelper.bounceAnimation(it)
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
                AnimationHelper.shakeAnimation(binding.chipGroup)
        }
    }
    private fun isEmojiSelected() : Boolean
    {
        return binding.chipGroup.checkedChipId != -1
    }
    private fun finishDialog()
    {
        listener.onOkButtonPressed(viewModel.currentEmoji, binding.descriptionEdittext.text.toString())
        dismiss()
    }



}

interface MoodChipOnClickListener
{
    fun onOkButtonPressed(emoji : MoodEmoji, description : String)
}