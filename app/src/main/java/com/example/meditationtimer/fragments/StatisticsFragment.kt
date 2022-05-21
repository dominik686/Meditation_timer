package com.example.meditationtimer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.compose.*
import com.example.meditationtimer.databinding.StatisticsFragmentBinding
import com.example.meditationtimer.models.Statistics
import com.example.meditationtimer.viewmodels.StatsViewModel
import com.example.meditationtimer.viewmodels.StatsViewModelFactory

class StatisticsFragment : Fragment() {

    private var _binding: StatisticsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StatsViewModel by viewModels {
        StatsViewModelFactory((requireActivity().application as MeditationApplication).sharedPrefRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StatisticsFragmentBinding.inflate(inflater, container, false)

        binding.composeView.apply { setViewCompositionStrategy(ViewCompositionStrategy.
        DisposeOnLifecycleDestroyed(viewLifecycleOwner))

            setContent { Stats(viewModel.getStats()) }
        }

        return binding.root
    }
}


@Composable
fun Stats(statsParam : Statistics)
{
    MaterialTheme() {
        Column(Modifier.padding(10.dp)) {
            val stats by remember { mutableStateOf(statsParam)}

            TotalMeditations(totalMeditations = stats.totalMeditations)
            DaysInARow(daysInARow = stats.daysInARow)
            LongestStreak(longestStreak = stats.longestStreak)
        }
    }
}

@Composable
fun TotalMeditations(totalMeditations : Int)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(8.dp) // margin
        .clip(RoundedCornerShape(12.dp))
        .background(Gray700))
    {
        Column(Modifier.padding(8.dp)) {
            Text("Total meditations sessions:", color = Gray300)
            Text(text = totalMeditations.toString(), color = Gray300)
        }

    }
}
@Composable
fun DaysInARow(daysInARow : Int)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(8.dp) // margin
        .clip(RoundedCornerShape(12.dp))
        .background(Gray700))
    {
        Column(Modifier.padding(8.dp))  {
            Text("Days meditated in a row:", color = Gray300)
            Text(text = daysInARow.toString(), color = Gray300)
        }
    }
}

@Composable
fun LongestStreak(longestStreak : Int)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .padding(8.dp) // margin
        .clip(RoundedCornerShape(12.dp))
        .background(Gray700))
    {
        Column(Modifier.padding(8.dp)) {
            Text("Longest streak:", color = Gray300)
            Text(text = longestStreak.toString(), color = Gray300)
        }
    }
}
