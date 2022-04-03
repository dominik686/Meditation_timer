package com.example.meditationtimer.fragments

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.NumberPicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.meditationtimer.BuildConfig
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.R
import com.example.meditationtimer.databinding.TimerFragmentBinding
import com.example.meditationtimer.models.MoodEmoji
import com.example.meditationtimer.models.TimeLeft
import com.example.meditationtimer.services.TimerService
import com.example.meditationtimer.viewmodels.TimerViewModel
import com.example.meditationtimer.viewmodels.TimerViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


//the service should start in startTimer(), and stop when the app is destroyed or timer is finished/canceled?
class TimerFragment : Fragment(), NumberPicker.OnValueChangeListener, MoodChipOnClickListener {
    // https://stackoverflow.com/questions/31205720/two-floating-action-buttons-next-to-each-other
    companion object {
        fun newInstance() = TimerFragment()
        const val TIMER_START = "${BuildConfig.APPLICATION_ID}.start"
        const val TIMER_STOP = "${BuildConfig.APPLICATION_ID}.timerStop"
    }

    private val timerVM: TimerViewModel by viewModels {
        TimerViewModelFactory((requireActivity().application as MeditationApplication).repository)
    }
    private var _binding: TimerFragmentBinding? = null
    private val binding get() = _binding!!
    private var secondsLeftLiveData: LiveData<Int> = MutableLiveData(0)

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
        //  createMoodDialog()

    }

    // Should i stop the timer and start the service in onPause instead? The timer runs for a couple seconds after leaving the app

    // Service should also be stopped in onResume
    // Get teh value from the service here


    // send intent to stop service in onResume and tell the service to emit the current seconds
    // then set a broadcast receiver to listen here for a broadcast from the service emitting the seconds?
    override fun onResume() {

        super.onResume()

        if (timerVM.isTimerStartedAndRunning() || timerVM.isTimerStartedAndPaused()) {
            stopTimerService()
            resumeTimer()

        }
    }

    fun stopTimerService() {
        val intentTimerStop = Intent(context, TimerService::class.java)
        intentTimerStop.action = TIMER_STOP
        activity?.startService(intentTimerStop)
    }


    override fun onStop() {
        super.onStop()

        if (timerVM.isTimerStartedAndRunning()) {
            timerVM.pauseTimer()
            startTimerService()
        }
    }


    private fun startTimerService() {
        val intentTimerStart = Intent(context, TimerService::class.java)
        intentTimerStart.action = TIMER_START
        intentTimerStart.putExtra("seconds", secondsLeftLiveData.value)
        context?.startService(intentTimerStart)

        // need to bind the service
    }

    private fun createMoodDialog() {
        val newFragment = MoodDialogFragment(this)
        newFragment.show(childFragmentManager, "time picker")
    }

    private fun startTimer(seconds: Int) {
        addLockScreenFlag()
        secondsLeftLiveData = timerVM.startTimer(seconds)
        observeSecondsLeftLiveData()
        hideBottomBar()
        //  startTimerService()

    }

    private fun hideBottomBar() {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.INVISIBLE
    }


    private fun addLockScreenFlag()
    {
        requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
    private fun observeSecondsLeftLiveData()
    {
        secondsLeftLiveData.observe(this) { secondsLeft: Int ->
            val timeLeft = TimeLeft(secondsLeft)
            binding.timeLeftTextView.text = timeLeft.toString()

            if (secondsLeft == 0) {
                timerIsFinished()
            }
        }
    }


    private fun timerIsFinished()
    {
        showBottomAppBar()

        removeLockScreenFlag()
        playFinishSound()
        createMoodDialog()

        binding.cancelTimerButton.visibility=View.GONE
        binding.timeLeftTextView.text = getString(R.string.timer_not_set)
        timerVM.cancelTimer()
        changeFabIcon()
    }

    private fun showBottomAppBar() {
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.VISIBLE
    }


    private fun removeLockScreenFlag()
    {
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
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
        binding.cancelTimerButton.setOnClickListener{
            showBottomAppBar()

            binding.timeLeftTextView.text = resources.getText(R.string.timer_not_set)
            timerVM.cancelTimer()
            changeFabIcon()
            binding.cancelTimerButton.visibility = View.INVISIBLE
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
            binding.floatingActionButton.setImageResource(R.drawable.pause_icon)
        }
        else if(timerVM.isTimerStartedAndPaused())
        {
            binding.floatingActionButton.setImageResource(R.drawable.play_icon)
        }
        else if(timerVM.isTimerNotStartedAndNotRunning())
        {
            binding.floatingActionButton.setImageResource(R.drawable.ic_baseline_add_alarm_24)
        }
    }
    private fun initializeNumberPicker()
    {
        binding.floatingActionButton.setOnClickListener{
            onFabPressed()

        }
    }

    private fun resumeTimer()
    {
        secondsLeftLiveData = timerVM.resumeTimer()
        secondsLeftLiveData.observe(viewLifecycleOwner) { secondsLeft: Int ->
            val timeLeft = TimeLeft(secondsLeft)
            binding.timeLeftTextView.text = timeLeft.toString()

            if (timerVM.isTimerFinished()) {
                timerIsFinished()
            }
        }

    }



    override fun onValueChange(p0: NumberPicker?, p1: Int, p2: Int) {
        if (p0 != null) {

            var minutes = (p0.value + 1) * 5

            startTimer(minutes*60)


            changeFabIcon()
            // Show the cancel button
            binding.cancelTimerButton.visibility = View.VISIBLE
        }
    }

    override fun onOkButtonPressed(emoji: MoodEmoji, description: String) {
        // Save the meditation by creating an object and calling the vm method?
        Toast.makeText(context, "TEST", Toast.LENGTH_SHORT).show()
        saveMeditation(emoji, description)
    }
    fun saveMeditation(emoji: MoodEmoji, description: String)
    {
        timerVM.insertMeditation(description, emoji)
    }
}