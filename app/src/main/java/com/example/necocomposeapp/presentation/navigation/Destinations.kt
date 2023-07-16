package com.example.necocomposeapp.presentation.navigation

import com.example.necocomposeapp.utils.DETAIL_SCREEN_CONST
import com.example.necocomposeapp.utils.HOME_SCREEN_CONST
import com.example.necocomposeapp.utils.SETTINGS_SCREEN_CONST

sealed class Destinations(val route: String) {
    object Home : Destinations(route = HOME_SCREEN_CONST)
    object Detail : Destinations(route = DETAIL_SCREEN_CONST)
    object Settings : Destinations(route = SETTINGS_SCREEN_CONST)
}
