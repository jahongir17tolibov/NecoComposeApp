package com.example.necocomposeapp.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.necocomposeapp.presentation.navigation.Destinations
import com.example.necocomposeapp.presentation.screens.ProductsListRoute

fun NavGraphBuilder.homeScreensGraph(navController: NavHostController) {
    composable(Destinations.Home.route) {
        ProductsListRoute(onNavigateToSettingsScreen = {
            navController.navigate(route = Destinations.Settings.route)
        })
    }
}