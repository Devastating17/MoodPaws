package com.example.moodpaws.screens

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.moodpaws.viewmodel.DiaryViewModel
import com.example.moodpaws.viewmodel.MoodViewModel

@Composable
fun MoodPawsApp() {
    val navController: NavHostController = rememberNavController()

    val context = LocalContext.current
    val diaryViewModel: DiaryViewModel = viewModel(
        factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory(
            context.applicationContext as Application
        )
    )

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
            composable("home") {
                // Replace this with your actual HomeScreen
                Text("Home Screen")
            }
            composable("diary_entry") {
                DiaryEntryScreen(viewModel = diaryViewModel, navController = navController)
            }
            composable("diary_list") {
                DiaryListScreen(viewModel = diaryViewModel)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

}

