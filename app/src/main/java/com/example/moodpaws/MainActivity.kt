package com.example.moodpaws

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.moodpaws.screens.MoodPawsApp
import com.example.moodpaws.ui.theme.MoodPawsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodPawsTheme {
                MoodPawsApp() // ✅ Launch your actual app UI
            }
        }
    }
}

