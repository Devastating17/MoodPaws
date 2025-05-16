package com.example.moodpaws.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MoodSelector(
    selectedMood: String?,
    onMoodSelected: (String) -> Unit
) {
    val moodIcons = mapOf(
        "Happy" to "\uD83E\uDD81", //LION
        "Neutral" to "\uD83D\uDC22", //TURTLE
        "Sad" to "\uD83D\uDC27", //Penguin
        "Angry" to "\uD83D\uDC2F", //tIGER
        "Excited" to "\uD83D\uDC12"//mONKEY
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text("How are you feeling today?", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            moodIcons.forEach{ (mood,emoji) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {  onMoodSelected(mood) }
                        .padding(8.dp)
                ){
                    Text(
                        text =emoji,
                        fontStyle = 32.sp
                    )
                    Text(
                        text = mood,
                        style =MaterialTheme.typography.bodySmall,
                        color = if (mood == selectedMood) Color.Blue else Color.Unspecified
                    )
                }

            }
        }
    }
}