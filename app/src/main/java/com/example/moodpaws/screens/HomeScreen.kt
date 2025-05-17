package com.example.moodpaws.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moodpaws.network.AnimalFactApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var fact by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }
    var selectedAnimal by remember { mutableStateOf("fox") }

    val animalOptions = listOf("fox", "cat", "dog", "panda", "bird", "koala")

    fun loadAnimalFact(animal: String) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val response = AnimalFactApi.retrofitService.getAnimalFact(animal)
                fact = response.fact
                imageUrl = response.image
            } catch (e: Exception) {
                fact = "Could not load animal fact."
                imageUrl = ""
            }
        }
    }

    LaunchedEffect(Unit) {
        loadAnimalFact(selectedAnimal)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to MoodPaws!",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = "Track your mood, write diary entries, and reflect on your emotional journey.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Simple dropdown replacement using buttons
        Text("Selected animal: $selectedAnimal", style = MaterialTheme.typography.bodyMedium)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            animalOptions.take(3).forEach { animal ->
                Button(onClick = {
                    selectedAnimal = animal
                    loadAnimalFact(animal)
                }) {
                    Text(animal)
                }
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            animalOptions.drop(3).forEach { animal ->
                Button(onClick = {
                    selectedAnimal = animal
                    loadAnimalFact(animal)
                }) {
                    Text(animal)
                }
            }
        }

        Button(onClick = {
            val randomAnimal = animalOptions.random()
            selectedAnimal = randomAnimal
            loadAnimalFact(randomAnimal)
        }) {
            Text("Surprise Me!")
        }

        if (imageUrl.isNotBlank()) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Animal image",
                modifier = Modifier.size(120.dp)
            )
        }
        if (fact.isNotBlank()) {
            Text(
                text = fact,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("entry") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Track Mood")
        }

        Button(
            onClick = { navController.navigate("history") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Mood History")
        }

        Button(
            onClick = { navController.navigate("chart") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mood Chart")
        }

        Button(
            onClick = { navController.navigate("diary_entry") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("New Diary Entry")
        }

        Button(
            onClick = { navController.navigate("diary_list") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("View Diary History")
        }
    }
}
