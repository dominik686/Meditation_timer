package com.example.meditationtimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meditationtimer.MarginItemDecorator
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.R
import com.example.meditationtimer.adapters.DisplayMeditationListAdapter
import com.example.meditationtimer.databinding.DisplayMeditationsFragmentBinding
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.models.MeditationList
import com.example.meditationtimer.viewmodels.DisplayMeditationsViewModel
import com.example.meditationtimer.viewmodels.DisplayMeditationsViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView


class DisplayMeditationsFragment : Fragment() {

    // Will need to create a viewmodel factory and initialize it the same way i did in TimerViewModel
    companion object {
        fun newInstance() = DisplayMeditationsFragment()
    }

    private val viewModel: DisplayMeditationsViewModel by viewModels{
        DisplayMeditationsViewModelFactory(
            (requireActivity().application as MeditationApplication).meditationRepository,
            (requireActivity().application as MeditationApplication).sharedPrefRepository)
    }

    private var _binding : DisplayMeditationsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var allMeditationsObserver : Observer<List<Meditation>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DisplayMeditationsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       initializeBottomBarNavigation()
       createAllMeditationsObserver()
       observeAllMeditations()


    }

    private fun initializeBottomBarNavigation()
    {
        var bottombar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        var navController = findNavController()

        bottombar.setOnItemSelectedListener { item ->
            findNavController()
            when(item.itemId) {
                R.id.timer_fragment ->{
                    var action = DisplayMeditationsFragmentDirections.actionDisplayMeditationsFragmentToTimer()
                    navController.navigate(action)
                }

                R.id.settings_fragment ->{
                    var action = DisplayMeditationsFragmentDirections.actionDisplayMeditationsFragmentToSettingsFragment()
                    navController.navigate(action)
                }
                R.id.statistics_fragment ->{
                    var action = DisplayMeditationsFragmentDirections.actionDisplayMeditationsFragmentToStatisticsFragment()
                    navController.navigate(action)
                }
            }
            true
        }
    }

    private fun createAllMeditationsObserver()
    {

        allMeditationsObserver = Observer<List<Meditation>>{ allMeditations ->

            pauseLoadingAnimation()
            hideLoadingAnimation()
            if (allMeditations.isEmpty()) {
                binding.noMeditationsRecorded.visibility = View.VISIBLE
            }
            else
            {
                binding.noMeditationsRecorded.visibility = View.GONE
                val groupedMeditationsMap = groupByDate(allMeditations)
                val groupedMeditationsList = mutableListOf<MeditationList>()

                for (entry in groupedMeditationsMap.entries.iterator()) {
                    val meditationsListTemp = MeditationList(entry.key, entry.value.reversed())
                    groupedMeditationsList.add(meditationsListTemp)
                }
                setupRecyclerview(groupedMeditationsList.reversed())
            }
        }
    }
// https://advancedrecyclerview.h6ah4i.com/?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=1432

    private fun pauseLoadingAnimation()
    {
        binding.loadingAnimation.pauseAnimation()
    }
    private fun hideLoadingAnimation()
    {
        binding.loadingAnimation.visibility = View.GONE
    }

    private fun groupByDate(meditations: List<Meditation>): Map<String, List<Meditation>> {

        return viewModel.groupByDate(meditations)
    }

    private fun setupRecyclerview(meditations: List<MeditationList>) {
        setupAdapter(meditations)
        setupLayoutManager()
        addItemDivider()
    }

    private fun setupAdapter(meditations: List<MeditationList>) {
        val adapter = DisplayMeditationListAdapter(requireContext(), meditations)
        binding.displayMeditationsRecyclerview.adapter = adapter
    }

    private fun setupLayoutManager()
    {
        val layoutManager =  LinearLayoutManager(context)
        binding.displayMeditationsRecyclerview.layoutManager = layoutManager
    }

    private fun addItemDivider()
    {
        binding.displayMeditationsRecyclerview.addItemDecoration(
         //   DividerItemDecoration(
           //     context,
          //      DividerItemDecoration.VERTICAL
        //    )
        MarginItemDecorator(32)
        )
    }

    private fun observeAllMeditations()
    {
        viewModel.allMeditations.observe(viewLifecycleOwner, allMeditationsObserver)
    }



}