package com.example.meditationtimer.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.meditationtimer.MeditationApplication
import com.example.meditationtimer.R
import com.example.meditationtimer.compose.Gray300
import com.example.meditationtimer.compose.Gray700
import com.example.meditationtimer.compose.Pink100
import com.example.meditationtimer.databinding.StatisticsFragmentBinding
import com.example.meditationtimer.models.MoodCount
import com.example.meditationtimer.models.Statistics
import com.example.meditationtimer.viewmodels.StatsViewModel
import com.example.meditationtimer.viewmodels.StatsViewModelFactory
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.bottomnavigation.BottomNavigationView


class StatisticsFragment : Fragment() {

    private var _binding: StatisticsFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StatsViewModel by viewModels {
        StatsViewModelFactory((requireActivity().application as MeditationApplication).sharedPrefRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StatisticsFragmentBinding.inflate(inflater, container, false)
        initializeBottomBarNavigation()
        binding.composeView.apply { setViewCompositionStrategy(ViewCompositionStrategy.
        DisposeOnLifecycleDestroyed(viewLifecycleOwner))

            setContent { Stats(viewModel.getStatistics()) }
        }

        
        
        return binding.root
    }

    private fun initializeBottomBarNavigation()
    {
        var bottombar = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        var navController = findNavController()

        bottombar.setOnItemSelectedListener { item ->
            findNavController()
            when(item.itemId) {
                R.id.timer_fragment ->{
                    var action = StatisticsFragmentDirections.actionStatisticsFragmentToTimer()
                    navController.navigate(action)
                }

                R.id.history_fragment ->{
                    var action = StatisticsFragmentDirections.actionStatisticsFragmentToDisplayMeditationsFragment()
                    navController.navigate(action)
                }
                R.id.settings_fragment ->{
                    var action = StatisticsFragmentDirections.actionStatisticsFragmentToSettingsFragment()
                    navController.navigate(action)
                }
            }
            true
        }
    }
}


@Composable
fun Stats(statsParam : Statistics)
{
    val scrollState  = rememberScrollState()
    val stats by remember { mutableStateOf(statsParam)}

    MaterialTheme {
        Column(
            Modifier
                .padding(10.dp)
                .verticalScroll(state = scrollState, enabled = true)) {
                TotalMeditations(totalMeditations = stats.totalMeditations)
                DaysInARow(daysInARow = stats.daysInARow)
                LongestStreak(longestStreak = stats.longestStreak)
                MoodCount(stats.moodCount)

        }
    }
}

@Composable
fun TotalMeditations(totalMeditations : Int)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        //.height(100.dp)
        .wrapContentHeight()
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
        .wrapContentHeight()
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
        .wrapContentHeight()
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

@Composable
fun MoodCount(count : MoodCount)
{
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp) // margin
        .clip(RoundedCornerShape(12.dp))
        .background(Gray700))
    {
        Column(Modifier.padding(8.dp)) {
            Text("Great mood count:", color = Gray300)
            Text(text = count.great.toString(), color = Gray300)
            Divider(modifier = Modifier.padding(bottom = 4.dp, top = 4.dp))


            Text("Good mood count:", color = Gray300)
            Text(text = count.good.toString(), color = Gray300)
            Divider(modifier = Modifier.padding(bottom = 4.dp, top = 4.dp))

            Text("Neutral mood count:", color = Gray300)
            Text(text = count.neutral.toString(), color = Gray300)
            Divider(modifier = Modifier.padding(bottom = 4.dp, top = 4.dp))

            Text("Bad mood count:", color = Gray300)
            Text(text = count.bad.toString(), color = Gray300)
            Divider(modifier = Modifier.padding(bottom = 4.dp, top = 4.dp))

            Text("Very bad mood count:", color = Gray300)
            Text(text = count.veryBad.toString(), color = Gray300)
            Divider(modifier = Modifier.padding(bottom = 4.dp, top = 4.dp))

            if(count.isNotEmpty())
            MoodCountPieChart(moodCount = count)

        }
    }
}
    @Composable
    fun MoodCountPieChart(moodCount: MoodCount)
    {
        AndroidView(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),factory = { context -> PieChart(context).apply{

            setUsePercentValues(false)
            description.isEnabled = false
            setExtraOffsets(5f, 10f, 5f, 5f)

            setCenterTextColor(Color.LTGRAY)
            dragDecelerationFrictionCoef = 0.99f;
            isDrawHoleEnabled =true
            setHoleColor(Color.GRAY)
            setTransparentCircleColor(Color.GRAY)
            setTransparentCircleAlpha(110)
            transparentCircleRadius = 40f
           setDrawCenterText(false)
            rotationAngle = 0F
            isRotationEnabled = true
            isHighlightPerTapEnabled = true

           // this.setOnChartValueSelectedListener()
            animateY(1400, Easing.EaseInOutQuad)


            var l = legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            l.orientation = Legend.LegendOrientation.HORIZONTAL
            l.setDrawInside(false)
            l.xEntrySpace = 7f
            l.yEntrySpace = 0f
            l.yOffset = 0f



            setEntryLabelColor(Color.GRAY)
            setEntryLabelTextSize(12f)

            var entries : List<PieEntry> = moodCount.toPieEntryList()
            var dataset =  PieDataSet(entries, "Moods")
            dataset.setDrawIcons(false)
            dataset.sliceSpace = 3f
            dataset.iconsOffset = (MPPointF(0F,40f))
            dataset.selectionShift = 5f


            // add a lot of colors
            val colors = ArrayList<Int>()

            for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)

            for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)

            for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)

            for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)

            for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
            colors.add(ColorTemplate.getHoloBlue())

            dataset.colors = colors
            var data = PieData(dataset)

           // data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.GRAY)
            this.data = data

            // undo all highlights
            this.highlightValues(null)

            this.invalidate()
        } })

    }

