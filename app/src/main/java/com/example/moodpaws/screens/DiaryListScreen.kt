package com.example.moodpaws.screens

@Composable
fun DiaryListScreen(viewModel: DiaryViewModel) {
    val diaryList by remember { mutableStateOf<List<DiaryEntry>>(emptyList()) }

    LaunchedEffect(true) {
        diaryList = viewModel.getAllDiaries()
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        items(diaryList) { entry ->
            Column(modifier = Modifier.padding(vertical = 8.dp)) {
                Text(text = entry.date, style = MaterialTheme.typography.titleMedium)
                Text(text = entry.content, style = MaterialTheme.typography.bodySmall)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}
