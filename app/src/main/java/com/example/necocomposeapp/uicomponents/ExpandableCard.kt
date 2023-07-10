package com.example.necocomposeapp.uicomponents

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.necocomposeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpendableCard() {
    var expendedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expendedState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 200,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = RoundedCornerShape(8.dp),
        onClick = { expendedState = !expendedState }
    ) {

        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //title text
                Text(
                    text = "MyTitle",
                    modifier = Modifier
                        .padding(12.dp)
                        .weight(7f),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                //Drop down button
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(0.5f)
                        .rotate(rotationState),
                    onClick = { expendedState = !expendedState }) {

                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-down arrow"
                    )
                }
            }
            if (expendedState) {
                LongText()
            }
        }
    }
}

@Composable
private fun LongText() = Text(
    text = stringResource(id = R.string.long_text),
    modifier = Modifier,
    fontSize = MaterialTheme.typography.titleMedium.fontSize,
    fontWeight = FontWeight.W300,
    maxLines = 5,
    overflow = TextOverflow.Ellipsis
)

@Preview(showBackground = true)
@Composable
fun PreviewCard() = Surface(modifier = Modifier.padding(8.dp)) {
    ExpendableCard()
}
