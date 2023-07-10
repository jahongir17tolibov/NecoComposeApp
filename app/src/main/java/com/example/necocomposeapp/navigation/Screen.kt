package com.example.necocomposeapp.navigation

import com.example.necocomposeapp.utils.DETAIL_SCREEN_CONST
import com.example.necocomposeapp.utils.HOME_SCREEN_CONST

sealed class Screen(val route: String) {
    object Home : Screen(route = HOME_SCREEN_CONST)
    object Detail : Screen(route = DETAIL_SCREEN_CONST)
}
