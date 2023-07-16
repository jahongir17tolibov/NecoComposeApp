package com.example.necocomposeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.necocomposeapp.presentation.navigation.graph.homeScreensGraph
import com.example.necocomposeapp.presentation.screens.DetailScreen
import com.example.necocomposeapp.presentation.screens.HomeScreen
import com.example.necocomposeapp.presentation.screens.SettingsScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destinations.Home.route) {
        homeScreensGraph(navController = navController)
        composable(route = Destinations.Detail.route) {
            DetailScreen(navController = navController)
        }
        composable(route = Destinations.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}