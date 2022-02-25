package com.example.meditationtimer.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.R
import com.example.meditationtimer.databinding.TimerFragmentBinding
import com.example.meditationtimer.dialogs.DescriptionDialog
import com.example.meditationtimer.models.TimeLeft
import com.example.meditationtimer.viewmodels.TimerViewModel
import com.example.meditationtimer.viewmodels.TimerViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class TimerFragment : Fragment(), NumberPicker.OnValueChangeListener, DescriptionDialog.SaveDescriptionOKButtonListener {
   // https://stackoverflow.com/questions/31205720/two-floating-action-buttons-next-to-each-other
    companion object {
        fun newInstance() = TimerFragment()
    }

    private val timerVM: TimerViewModel by viewModels{
        TimerViewModelFactory((activity!!.application as MeditationApplication).repository)
    }
    private var _binding : TimerFragmentBinding? = null
    private val binding get() = _binding!!
    private var secondsLeftLiveData : LiveData<Int> = MutableLiveData(0)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = TimerFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


           initializeNumberPicker()
           initializeCancelButton()
           createMoodDialog()

    }

    private fun createMoodDialog()
    {
        val newFragment = MoodDialogFragment()
        newFragment.show(childFragmentManager, "time picker")
    }

    private fun startTimer(seconds : Int)
    {
        addLockScreenFlag()
        secondsLeftLiveData =  timerVM.startTimer(seconds)
       observeSecondsLeftLiveData()
        hideBottomBar()
    }

    private fun hideBottomBar()
    {
       activity!!.findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.INVISIBLE
    }


    private fun addLockScreenFlag()
    {
        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
    private fun observeSecondsLeftLiveData()
    {
        secondsLeftLiveData.observe(this, { secondsLeft: Int ->
            val timeLeft = TimeLeft(secondsLeft)
            _binding!!.timeLeftTextView.text = timeLeft.toString()

            if(secondsLeft == 0)
            {
                timerIsFinished()
            }
        })
    }


    private fun timerIsFinished()
    {
        showBottomAppBar()

        removeLockScreenFlag()
        playFinishSound()
        buildDialog()

        _binding!!.cancelTimerButton.visibility=View.GONE
        _binding!!.timeLeftTextView.text = getString(R.string.timer_not_set)
        timerVM.cancelTimer()
        changeFabIcon()
    }

    private fun showBottomAppBar()
    {
        activity!!.findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE
    }
    private fun buildDialog()
    {
        val d = DescriptionDialog(context!!, this)
        d.buildDialog()
        d.show()
    }
    override fun saveMeditation(description : String)
    {
        timerVM.insertMeditation(description)

    }
    private fun removeLockScreenFlag()
    {
        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun playFinishSound()
    {

        var mp = MediaPlayer.create(context, R.raw.old_fashioned_school_bell_daniel_simon)

        mp.setOnCompletionListener {
            it.reset()
            it.release()
            mp = null
        }

        mp.start()
        // Pause the sound after two seconds
        Handler(Looper.getMainLooper()).postDelayed({
            mp.reset()
            mp.release()
            mp = null
        }, 2000)
    }

    private fun initializeCancelButton()
    {
        _binding!!.cancelTimerButton.setOnClickListener{
            showBottomAppBar()

            _binding!!.timeLeftTextView.text = resources.getText(R.string.timer_not_set)
            timerVM.cancelTimer()
            changeFabIcon()
            _binding!!.cancelTimerButton.visibility = View.INVISIBLE
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        timerVM.cancelTimer()
    }


    private fun onFabPressed()
    {
        if(timerVM.isTimerStartedAndRunning())
        {

            timerVM.pauseTimer()
            changeFabIcon()
        }
        else if(timerVM.isTimerStartedAndPaused())
        {

            resumeTimer()
            changeFabIcon()
        }
        else if(timerVM.isTimerNotStartedAndNotRunning())
        {
            val newFragment = ChooseTimeDialogFragment()
            newFragment.setValueChangeListener(this)
            newFragment.show(childFragmentManager, "time picker")
        }


    }
    private fun changeFabIcon()
    {
        if(timerVM.isTimerStartedAndRunning())
        {
            _binding!!.floatingActionButton.setImageResource(R.drawable.pause_icon)
        }
        else if(timerVM.isTimerStartedAndPaused())
        {
            _binding!!.floatingActionButton.setImageResource(R.drawable.play_icon)
        }
        else if(timerVM.isTimerNotStartedAndNotRunning())
        {
            _binding!!.floatingActionButton.setImageResource(R.drawable.ic_baseline_add_alarm_24)
        }
    }
    private fun initializeNumberPicker()
    {
        _binding!!.floatingActionButton.setOnClickListener{
            onFabPressed()

        }
    }

    private fun resumeTimer()
    {
        secondsLeftLiveData = timerVM.resumeTimer()
        secondsLeftLiveData.observe(this, { secondsLeft: Int ->
            val timeLeft = TimeLeft(secondsLeft)
            _binding!!.timeLeftTextView.text = timeLeft.toString()

            if(timerVM.isTimerFinished())
            {
                timerIsFinished()
            }
        })

    }



    override fun onValueChange(p0: NumberPicker?, p1: Int, p2: Int) {
        if (p0 != null) {

            var minutes = (p0.value + 1) * 5

            startTimer(minutes*60)


            changeFabIcon()
            // Show the cancel button
            _binding!!.cancelTimerButton.visibility = View.VISIBLE
        };
    }
}