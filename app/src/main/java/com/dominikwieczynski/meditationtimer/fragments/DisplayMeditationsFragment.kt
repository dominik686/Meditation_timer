package com.dominikwieczynski.meditationtimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dominikwieczynski.meditationtimer.common.MarginItemDecorator
import com.dominikwieczynski.meditationtimer.MeditationApplication
import com.dominikwieczynski.meditationtimer.R
import com.dominikwieczynski.meditationtimer.adapters.DisplayMeditationListAdapter
import com.dominikwieczynski.meditationtimer.databinding.DisplayMeditationsFragmentBinding
import com.dominikwieczynski.meditationtimer.models.Meditation
import com.dominikwieczynski.meditationtimer.models.MeditationList
import com.dominikwieczynski.meditationtimer.viewmodels.DisplayMeditationsViewModel
import com.dominikwieczynski.meditationtimer.viewmodels.DisplayMeditationsViewModelFactory
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
    private lateinit var dragHelper : ItemTouchHelper
    private lateinit var swipeHelper: ItemTouchHelper
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

       initializeSwipeHelper()
       attachSwipeHelper()
    }
    private fun initializeSwipeHelper()
    {
        swipeHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            }

        })

    }
    private fun attachSwipeHelper()
    {
        swipeHelper.attachToRecyclerView(binding.displayMeditationsRecyclerview)
    }

    private fun initializeBottomBarNavigation()
    {
        var bottombar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        var navController = findNavController()

        bottombar.setOnItemSelectedListener { item ->
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
            if (allMeditations.isEmpty()) {
                showNoMeditationsRecordedMessage()
            }
            else
            {
                hideNoMeditationsRecordedMessage()
                val preparedList = prepareMeditationList(allMeditations)
                setupRecyclerview(preparedList)
            }
        }
    }
// https://advancedrecyclerview.h6ah4i.com/?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=1432

    private fun showNoMeditationsRecordedMessage()
    {
        binding.noMeditationsRecorded.visibility = View.VISIBLE

    }
    private fun hideNoMeditationsRecordedMessage()
    {
        binding.noMeditationsRecorded.visibility = View.GONE

    }

    private fun prepareMeditationList(allMeditations : List<Meditation>) : List<MeditationList>
    {
        val groupedMeditationsMap = groupByDate(allMeditations)
        val groupedMeditationsList = mutableListOf<MeditationList>()

        for (entry in groupedMeditationsMap.entries.iterator()) {
            val meditationsListTemp = MeditationList(entry.key, entry.value.reversed())
            groupedMeditationsList.add(meditationsListTemp)
        }
        return groupedMeditationsList.reversed()
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