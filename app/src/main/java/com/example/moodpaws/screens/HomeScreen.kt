package com.example.moodpaws.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to MoodPaws!", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("entry") }) {
            Text("Track Mood")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("history") }) {
            Text("View History")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("chart") }) {
            Text("Mood Chart")
        }
    }
}
