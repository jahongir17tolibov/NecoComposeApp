package com.example.necocomposeapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.necocomposeapp.uicomponents.AppToolbar
import com.example.necocomposeapp.navigation.Screen

@Composable
fun DetailScreen(navController: NavHostController) {
    Scaffold(topBar = {
        AppToolbar(appBarTitle = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Detail Screen",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }, modifier = Modifier.clickable { navController.popBackStack() })
    }) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(paddingValues)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.size(width = 120.dp, height = 200.dp))

                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(color = Color.Green)
                        .clickable {
                            navController.navigate(Screen.Home.route)
                        },
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(text = "pitipti", color = Color.White, fontSize = 23.sp)
                }

            }


        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(navController = rememberNavController())
}