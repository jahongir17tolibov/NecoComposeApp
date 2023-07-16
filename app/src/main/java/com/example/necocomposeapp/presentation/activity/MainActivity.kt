package com.example.necocomposeapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.necocomposeapp.data.model.LazyModel
import com.example.necocomposeapp.R
import com.example.necocomposeapp.presentation.navigation.SetupNavGraph
import com.example.necocomposeapp.ui.theme.NecoComposeAppTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NecoComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {

                    navController = rememberNavController()
                    SetupNavGraph(navController = navController)

                }
            }
        }
    }
}

@Composable
fun LazyItem(model: LazyModel) {
    var isExpended by remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(color = Color.Yellow, shape = RoundedCornerShape(16.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //index
        SimpleText(
            text = model.numb.toString(),
            color = Color.Blue,
            textSize = 16,
            modifier = Modifier.padding(14.dp, 10.dp, 0.dp, 10.dp)
        )

        //avatar
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "SimpleImage",
            modifier = Modifier
                .padding(10.dp)
                .clip(CircleShape)
                .size(30.dp)
        )

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            //content
            Text(
                modifier = Modifier.clickable {
                    isExpended = !isExpended
                },
                text = model.name,
                style = TextStyle(color = Color.Black, fontSize = 20.sp),
                maxLines = linesLogics(isExpended)
            )
        }

//        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
//            SimpleText(
//                text = model.value,
//                color = Color.Red,
//                textSize = 12,
//                modifier = Modifier.padding(12.dp)
//            )
//        }


    }
}


@Composable
fun SimpleText(
    text: String,
    color: Color,
    textSize: Int,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Normal
) = Text(
    text = text,
    modifier = modifier,
    style = TextStyle(color = color, fontWeight = fontWeight, fontSize = textSize.sp)
)


@Preview(showBackground = true)
@Composable
private fun CircleItem() {
    val counter = remember {
        mutableStateOf(0)
    }

    val color = remember {
        mutableStateOf(Color.Cyan)
    }

    val interactionSource = remember {
        MutableInteractionSource()
    }

    Box(
        modifier = Modifier
            .size(300.dp)
            .background(color = color.value, shape = CutCornerShape(150.dp))
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                when (++counter.value) {
                    4 -> color.value = Color.Blue
                    8 -> color.value = Color.Magenta
                    9 -> color.value = Color.Gray
                    12 -> color.value = Color.Red
                    13 -> color.value = Color.Yellow
                    17 -> color.value = Color.Unspecified
                    30 -> color.value = Color.Green
                }
            },
        contentAlignment = Alignment.Center
    ) {
//        SimpleText(text = counter.value.toString(), fontWeight = FontWeight.Black)
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NecoComposeAppTheme {
        Greeting("Android")
    }
}

private fun linesLogics(isExp: Boolean) = if (isExp) 10 else 1