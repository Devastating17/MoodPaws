package com.example.moodpaws.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moodpaws.data.DiaryEntry
import com.example.moodpaws.viewmodel.DiaryViewModel
import java.time.LocalDate

@Composable
fun DiaryEntryScreen(viewModel: DiaryViewModel, navController: NavController) {
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Write about your day", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Diary entry") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        Button(onClick = {
            val today = LocalDate.now().toString()
            if (text.isNotBlank()) {
                viewModel.insertDiary(DiaryEntry(date = today, content = text))
                Toast.makeText(context, "Diary saved!", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            } else {
                Toast.makeText(context, "Entry cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text("Save Entry")
        }
    }
}
