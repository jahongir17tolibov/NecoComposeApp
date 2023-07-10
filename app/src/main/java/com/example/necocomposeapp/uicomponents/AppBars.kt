package com.example.necocomposeapp.uicomponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    appBarTitle: @Composable () -> Unit,
    modifier: Modifier
) = TopAppBar(
    title = appBarTitle,
    navigationIcon = {
        Icon(
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "back_button",
            tint = Color.White,
            modifier = modifier
        )
    },
    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF006064)),
    windowInsets = WindowInsets(left = 10.dp, right = 10.dp)
)