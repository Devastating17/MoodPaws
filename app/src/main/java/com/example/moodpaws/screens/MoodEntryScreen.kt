package com.example.moodpaws.screens

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moodpaws.data.Mood
import com.example.moodpaws.utils.Constants
import java.time.LocalDate
import com.example.moodpaws.viewmodel.MoodViewModel

@Composable
fun MoodEntryScreen() {
    val context = LocalContext.current
    val viewModel: MoodViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(context.applicationContext as Application)
    )

    var selectedMood by remember { mutableStateOf<String?>(null) }
    var note by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("How are you feeling today?", fontSize = 20.sp)

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Constants.moodIcons.forEach { (mood, icon) ->
                Text(
                    text = icon,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .clickable { selectedMood = mood }
                        .padding(8.dp)
                )
            }
        }

        OutlinedTextField(
            value = note,
            onValueChange = { note = it },
            label = { Text("Add a note (optional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val today = LocalDate.now().toString()
                val mood = Mood(moodType = selectedMood!!, date = today, note = note)
                viewModel.insertMood(mood)
            },
            enabled = selectedMood != null
        ) {
            Text("Save Mood")
        }
    }
}


