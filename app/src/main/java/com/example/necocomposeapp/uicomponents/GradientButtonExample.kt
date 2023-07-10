package com.example.necocomposeapp.uicomponents

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.necocomposeapp.ui.theme.MemarianiColor1
import com.example.necocomposeapp.ui.theme.MemarianiColor2
import com.example.necocomposeapp.ui.theme.MemarianiColor3
import kotlinx.coroutines.delay

@Composable
fun GradientButton(
    text: String,
    textColor: Color,
    gradient: Brush,
    scale: Float,
    onClick: () -> Unit
) {
    
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        shape = CutCornerShape(8.dp),
        onClick = onClick,
    ) {

        Box(
            modifier = Modifier
                .background(gradient)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .scale(scale = scale)
                .animateContentSize()
                .size(width = 160.dp, height = 60.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text, color = textColor)
        }

    }

}

@Preview
@Composable
fun PreviewGradientButton() {

    var isBouncing by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isBouncing) 1.2f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    GradientButton(
        text = "Button",
        textColor = Color.White,
        gradient = Brush.horizontalGradient(
            colors = listOf(MemarianiColor1, MemarianiColor2, MemarianiColor3)
        ),
        scale = scale,
        onClick = { isBouncing = true }
    )

    LaunchedEffect(isBouncing) {
        if (isBouncing) {
            delay(300)
            isBouncing = false
        }
    }

}

@Preview
@Composable
fun PreviewTapButton() {
    var isBouncing by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isBouncing) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )

    Button(
        onClick = { isBouncing = true },
        contentPadding = PaddingValues(),
        shape = CutCornerShape(8.dp),
        modifier = Modifier
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isBouncing = true
                        true
                    },
                    onLongPress = {
                        isBouncing = true
                        true
                    })
            }
            .animateContentSize()
            .background(
                color = if (isBouncing) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                shape = CircleShape
            )
    ) {

        Box(
            modifier = Modifier
                .background(color = Color.Red)
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .scale(scale = scale)
                .animateContentSize()
                .size(width = 160.dp, height = 60.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "text", color = Color.White)
        }

    }

    LaunchedEffect(isBouncing) {
        if (isBouncing) {
            delay(300)
            isBouncing = false
        }
    }
}

