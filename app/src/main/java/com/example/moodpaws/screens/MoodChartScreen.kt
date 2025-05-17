package com.example.moodpaws.screens

import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moodpaws.viewmodel.MoodViewModel
import com.example.moodpaws.data.Mood
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import androidx.compose.ui.viewinterop.AndroidView
import java.time.LocalDate
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun MoodChartScreen(viewModel: MoodViewModel, navController: NavController) {
    var timeFrame by remember { mutableStateOf("week") }
    val context = LocalContext.current
    val filteredMoods by viewModel.filteredMoods.observeAsState(emptyList())

    LaunchedEffect(timeFrame) {
        viewModel.loadMoodsForTimeframe(timeFrame)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mood Trends", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { timeFrame = "week" }) { Text("Week") }
            Button(onClick = { timeFrame = "month" }) { Text("Month") }
            Button(onClick = { timeFrame = "year" }) { Text("Year") }
        }

        Spacer(modifier = Modifier.height(24.dp))

        AndroidView(factory = { ctx ->
            BarChart(ctx).apply {
                val entries = filteredMoods.mapIndexed { index, mood ->
                    BarEntry(index.toFloat(), moodToValue(mood.moodType))
                }

                val colors = filteredMoods.map { mood ->
                    when (mood.moodType) {
                        "Happy" -> Color.parseColor("#F4E285")
                        "Neutral" -> Color.parseColor("#8CB369")
                        "Sad" -> Color.parseColor("#5B8E7D")
                        "Angry" -> Color.parseColor("#BC4B51")
                        "Excited" -> Color.parseColor("#F4A259")
                        else -> Color.GRAY
                    }
                }

                val dataSet = BarDataSet(entries, "Mood Levels").apply {
                    setColors(colors)
                    valueTextColor = Color.BLACK
                    valueTextSize = 12f
                }

                data = BarData(dataSet)
                description = Description().apply { text = "Mood Levels by $timeFrame" }
                setFitBars(true)
                animateY(1000)
            }
        }, modifier = Modifier.fillMaxWidth().height(300.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Text("${filteredMoods.size} mood entries shown", fontSize = 16.sp)
    }
}

fun moodToValue(mood: String?): Float {
    return when (mood) {
        "Happy" -> 5f
        "Excited" -> 4f
        "Neutral" -> 3f
        "Sad" -> 2f
        "Angry" -> 1f
        else -> 0f
    }
}
