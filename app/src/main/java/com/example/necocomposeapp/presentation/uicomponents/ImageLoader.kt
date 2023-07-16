package com.example.necocomposeapp.presentation.uicomponents

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.necocomposeapp.R

private const val IMG1_URL =
    "https://images.pexels.com/photos/268533/pexels-photo-268533.jpeg?cs=srgb&dl=pexels-pixabay-268533.jpg&fm=jpg"

private const val BITTA_APA = "https://pixlr.com/images/index/remove-bg.webp"

@Preview(showBackground = true)
@Composable
fun PreviewImgLoad() = Box(
    modifier = Modifier
        .size(300.dp)
        .background(color = Color.LightGray),
    contentAlignment = Alignment.Center
) {

    val painter = rememberAsyncImagePainter(
        model = IMG1_URL,
        imageLoader = ImageLoader(LocalContext.current),
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        error = painterResource(id = R.drawable.google_icon)
    )

    Image(
        painter = painter,
        contentDescription = "title of image",
        modifier = Modifier.fillMaxSize()
    )
    when (painter.state) {
        is AsyncImagePainter.State.Success -> CircleCropTransformation()

        is AsyncImagePainter.State.Loading -> CircularProgressIndicator()

        is AsyncImagePainter.State.Error -> Toast.makeText(
            LocalContext.current,
            "Error!!!",
            Toast.LENGTH_SHORT
        ).show()

        else -> Unit
    }
}

@Preview
@Composable
fun PreviewAsyncImageLoad() = Box(
    modifier = Modifier
        .size(300.dp)
        .background(color = Color.LightGray),
    contentAlignment = Alignment.Center
) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(BITTA_APA)
            .placeholder(R.drawable.google_icon)
            .error(R.drawable.round_key_24)
            .crossfade(1000)
            .transformations(CircleCropTransformation())
            .build(),
        contentDescription = "default",
        onLoading = {
        },
        modifier = Modifier.fillMaxSize()
    )
}