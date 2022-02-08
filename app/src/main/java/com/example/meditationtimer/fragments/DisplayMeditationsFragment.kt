package com.example.meditationtimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.adapters.DisplayMeditationsAdapter
import com.example.meditationtimer.databinding.DisplayMeditationsFragmentBinding
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.viewmodels.DisplayMeditationsViewModel
import com.example.meditationtimer.viewmodels.DisplayMeditationsViewModelFactory


class DisplayMeditationsFragment : Fragment() {

    // Will need to create a viewmodel factory and initialize it the same way i did in TimerViewModel
    companion object {
        fun newInstance() = DisplayMeditationsFragment()
    }

    private val viewModel: DisplayMeditationsViewModel by viewModels{
        DisplayMeditationsViewModelFactory((activity!!.application as MeditationApplication).repository)
    }

    private var _binding : DisplayMeditationsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var allMeditationsObserver : Observer<List<Meditation>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DisplayMeditationsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       createAllMeditationsObserver()
       observeAllMeditations()
    }

    private fun createAllMeditationsObserver()
    {

        allMeditationsObserver = Observer<List<Meditation>>{ allMeditations ->
            setupRecyclerview(allMeditations)
        }
    }

    private fun setupRecyclerview(meditations: List<Meditation>)
    {
        setupAdapter(meditations)
        setupLayoutManager()
        addItemDivider()
    }
     fun setupAdapter(meditations: List<Meditation>)
    {
        val adapter = DisplayMeditationsAdapter(meditations)
        _binding!!.displayMeditationsRecyclerview.adapter = adapter
    }

    private fun setupLayoutManager()
    {
        val layoutManager =  LinearLayoutManager(context)
        _binding!!.displayMeditationsRecyclerview.layoutManager = layoutManager
    }

    private fun addItemDivider()
    {
        _binding!!.displayMeditationsRecyclerview.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun observeAllMeditations()
    {
        viewModel.allMeditations.observe(this, allMeditationsObserver)
    }


}