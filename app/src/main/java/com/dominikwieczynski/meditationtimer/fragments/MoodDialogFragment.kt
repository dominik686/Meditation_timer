package com.dominikwieczynski.meditationtimer.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.dominikwieczynski.meditationtimer.R
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import com.dominikwieczynski.meditationtimer.databinding.MoodDialogFragmentBinding
import com.dominikwieczynski.meditationtimer.models.MoodEmoji
import com.dominikwieczynski.meditationtimer.viewmodels.MoodDialogFragmentViewModel
import com.dominikwieczynski.meditationtimer.viewmodels.MoodDialogFragmentViewModelFactory

import com.dominikwieczynski.meditationtimer.common.AnimationHelper


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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.dialog.setCanceledOnTouchOutside(false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }



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
        binding.chipGroup.setOnCheckedStateChangeListener { _, _ ->
            revealAnimation()
            binding.chipGroup.setOnCheckedStateChangeListener(null)
        }


        createDialog()
        showDialog()
        replaceEmptyOKButtonListener()
        return dialog
    }
    private fun revealAnimation()
    {
        AnimationHelper.circularReveal(binding.textInputLayout)

    }

    private fun initializeBuilder()
    {
        builder = AlertDialog.Builder(activity)
    }

    private fun setDialogTitle()
    {
        builder.setTitle(getString(R.string.describe_your_mood))
    }


    private fun setEmptyOKButtonListener()
    {
        builder.setPositiveButton(
            resources.getString(R.string.okay)
        ) { _, _ ->
        }
    }

    private fun initializeBinding()
    {
        _binding = MoodDialogFragmentBinding.inflate(layoutInflater)

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