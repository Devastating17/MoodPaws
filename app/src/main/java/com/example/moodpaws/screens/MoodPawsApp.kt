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
import com.example.moodpaws.screens.HomeScreen
import com.example.moodpaws.screens.MoodEntryScreen
import com.example.moodpaws.screens.MoodHistoryScreen
import com.example.moodpaws.screens.MoodChartScreen
import androidx.lifecycle.ViewModelProvider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.currentBackStackEntryAsState






@Composable
fun MoodPawsApp() {
    val navController: NavHostController = rememberNavController()
    val context = LocalContext.current

    val diaryViewModel: DiaryViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(
            context.applicationContext as Application
        )
    )
    val moodViewModel: MoodViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(
            context.applicationContext as Application
        )
    )

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("diary_entry") {
                DiaryEntryScreen(viewModel = diaryViewModel, navController = navController)
            }
            composable("diary_list") {
                DiaryListScreen(viewModel = diaryViewModel)
            }
            composable("entry") {
                MoodEntryScreen(viewModel = moodViewModel, navController = navController)
            }
            composable("history") {
                MoodHistoryScreen(viewModel = moodViewModel, navController = navController)
            }
            composable("chart") {
                MoodChartScreen(viewModel = moodViewModel, navController = navController)
            }
        }
    }
}
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        val items = listOf(
            NavigationItem("Home", "home", Icons.Default.Home),
            NavigationItem("Mood", "entry", Icons.Default.Favorite),
            NavigationItem("Diary", "diary_list", Icons.Default.List),
            NavigationItem("Chart", "chart", Icons.Default.DateRange)
        )

        val currentDestination = navController.currentBackStackEntryAsState().value?.destination

        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.route == item.route,
                onClick = {
                    if (currentDestination?.route != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}

data class NavigationItem(val label: String, val route: String, val icon: ImageVector)
