package com.example.meditationtimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.meditationtimer.MarginItemDecorator
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.adapters.DisplayMeditationListAdapter
import com.example.meditationtimer.databinding.DisplayMeditationsFragmentBinding
import com.example.meditationtimer.models.Meditation
import com.example.meditationtimer.models.MeditationList
import com.example.meditationtimer.viewmodels.DisplayMeditationsViewModel
import com.example.meditationtimer.viewmodels.DisplayMeditationsViewModelFactory


class DisplayMeditationsFragment : Fragment() {

    // Will need to create a viewmodel factory and initialize it the same way i did in TimerViewModel
    companion object {
        fun newInstance() = DisplayMeditationsFragment()
    }

    private val viewModel: DisplayMeditationsViewModel by viewModels{
        DisplayMeditationsViewModelFactory((requireActivity().application as MeditationApplication).repository)
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
            //  setupRecyclerview(allMeditations)
            val groupedMeditationsMap = groupByDate(allMeditations)
            // val d = groupedMeditations.values.toList()

            val groupedMeditationsList = mutableListOf<MeditationList>()

            for (entry in groupedMeditationsMap.entries.iterator()) {
                val meditationsListTemp = MeditationList(entry.key, entry.value)
                groupedMeditationsList.add(meditationsListTemp)
            }

            setupRecyclerview(groupedMeditationsList.toList())

        }
    }
// https://advancedrecyclerview.h6ah4i.com/?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=1432


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