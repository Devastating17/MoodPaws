package com.example.moodpaws.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoodSelector(
    selectedMood: String?,
    onMoodSelected: (String) -> Unit
) {
    val moodIcons = mapOf(
        "Happy" to "\uD83E\uDD81",  // 🦁 Lion
        "Neutral" to "\uD83D\uDC22", // 🐢 Turtle
        "Sad" to "\uD83D\uDC27",     // 🐧 Penguin
        "Angry" to "\uD83D\uDC2F",   // 🐯 Tiger
        "Excited" to "\uD83D\uDC12"  // 🐒 Monkey
    )

    val moodColors = mapOf(
        "Happy" to Color(0xFFFFC107),     // Amber
        "Neutral" to Color.Gray,
        "Sad" to Color(0xFF2196F3),       // Blue
        "Angry" to Color(0xFFF44336),     // Red
        "Excited" to Color(0xFF4CAF50)    // Green
    )

    var showLegend by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("How are you feeling today?", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { showLegend = true }) {
                Icon(Icons.Default.Info, contentDescription = "Mood Legend")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            moodIcons.forEach { (mood, emoji) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { onMoodSelected(mood) }
                        .padding(8.dp)
                        .then(
                            if (mood == selectedMood) Modifier.border(
                                width = 2.dp,
                                color = Color.Black,
                                shape = MaterialTheme.shapes.small
                            ) else Modifier
                        )
                ) {
                    Text(
                        text = emoji,
                        fontSize = 32.sp,
                        modifier = Modifier.semantics { contentDescription = mood }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = mood,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = if (mood == selectedMood) Color.Black else (moodColors[mood] ?: Color.Unspecified)
                        )
                    )
                }
            }
        }

        if (showLegend) {
            AlertDialog(
                onDismissRequest = { showLegend = false },
                confirmButton = {
                    TextButton(onClick = { showLegend = false }) {
                        Text("Got it")
                    }
                },
                title = { Text("Mood Emoji Legend") },
                text = {
                    Text(
                        "\uD83E\uDD81 = Happy\n" +
                                "\uD83D\uDC22 = Neutral\n" +
                                "\uD83D\uDC27 = Sad\n" +
                                "\uD83D\uDC2F = Angry\n" +
                                "\uD83D\uDC12 = Excited"
                    )
                }
            )
        }
    }
}

