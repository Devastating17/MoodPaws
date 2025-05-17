package com.example.moodpaws.screens

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
            modifier = Modifier.fillMaxWidth().weight(1f)
        )
        Button(onClick = {
            val today = LocalDate.now().toString()
            viewModel.insertDiary(DiaryEntry(date = today, content = text))
            Toast.makeText(context, "Diary saved!", Toast.LENGTH_SHORT).show()
            navController.popBackStack()
        }) {
            Text("Save Entry")
        }
    }
}
