package com.example.moodpaws.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodpaws.screens.MoodEntryScreen
import com.example.moodpaws.screens.MoodHistoryScreen
import com.example.moodpaws.screens.MoodChartScreen
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun MoodPawsApp() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(navController)  }
            composable("entry"){ MoodEntryScreen() }
            composable("history") { MoodHistoryScreen() }
            composable("chart") { MoodChartScreen() }
            composable("diary_entry") {
                DiaryEntryScreen(viewModel = diaryViewModel, navController = navController)
            }
            composable("diary_list") {
                DiaryListScreen(viewModel = diaryViewModel)
            }

        }
    }
}
val diaryViewModel: DiaryViewModel = viewModel(
    factory = ViewModelProvider.AndroidViewModelFactory(LocalContext.current.applicationContext as Application)
)
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = true,
            onClick = { navController.navigate("entry") },
            label = { Text("Entry") },
            icon = { Icon(Icons.Default.Favorite, contentDescription = null) }
        )
    }
}


