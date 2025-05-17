package com.example.moodpaws.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodpaws.data.DiaryEntry
import com.example.moodpaws.viewmodel.DiaryViewModel
import kotlinx.coroutines.launch

@Composable
fun DiaryListScreen(viewModel: DiaryViewModel) {
    var diaryList by remember { mutableStateOf<List<DiaryEntry>>(emptyList()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        scope.launch {
            diaryList = viewModel.getAllDiaries()
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("My Diary Entries", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(diaryList) { entry ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = entry.date, style = MaterialTheme.typography.titleMedium)
                    Text(text = entry.content, style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}
