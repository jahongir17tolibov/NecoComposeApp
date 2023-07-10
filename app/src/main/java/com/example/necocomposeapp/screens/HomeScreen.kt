package com.example.necocomposeapp.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import com.example.necocomposeapp.R
import com.example.necocomposeapp.domain.intent.ResponseState
import com.example.necocomposeapp.domain.viewmodel.StoreViewModel
import com.example.necocomposeapp.model.Product
import com.example.necocomposeapp.uicomponents.AppToolbar
import org.koin.androidx.compose.getViewModel


@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(topBar = {
        AppToolbar(appBarTitle = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Home Screen", color = Color.White, textAlign = TextAlign.Center
                )
            }
        }, modifier = Modifier.clickable { navController.popBackStack() })
    }) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel = getViewModel<StoreViewModel>()
            viewModel.loadProducts()

            InitLoadData(viewModel = viewModel)

        }

    }
}

@Composable
fun InitLoadData(viewModel: StoreViewModel) {
    val productsState by viewModel.products.collectAsState()

    when (productsState) {
        is ResponseState.Success -> {
            val productList = (productsState as ResponseState.Success<List<Product>>).data
            Log.d("jt1771tj", "InitLoadData: $productList")
            SetupLazyColumn(list = productList)
            SetupCircularProgress(false)
        }

        is ResponseState.Loading -> SetupCircularProgress()

        is ResponseState.Error -> Toast.makeText(
            LocalContext.current,
            (productsState as ResponseState.Error).message,
            Toast.LENGTH_SHORT
        ).show()

        else -> Unit
    }

}

@Composable
fun SetupLazyColumn(list: List<Product>) = LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues()
) {

    itemsIndexed(items = list) { _, product ->
        ProductsItem(
            model = Product(
                id = product.id,
                title = product.title,
                price = product.price,
                image = product.image,
                category = product.category,
                description = product.description,
//                rating = product.rating
            )
        )
    }


}

@Composable
fun SetupCircularProgress(visibility: Boolean = true) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visibility,
            enter = fadeIn(tween(1000)),
            exit = fadeOut(tween(1000))
        ) {
            CircularProgressIndicator(modifier = Modifier)
        }
    }
}

@Composable
fun ProductsItem(model: Product) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, color = Color.Blue),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {

            //image's card
            Card(shape = RoundedCornerShape(10.dp)) {
                //painter using for async load image from api
                val painter = rememberAsyncImagePainter(
                    model = model.image,
                    imageLoader = ImageLoader(LocalContext.current),
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    error = painterResource(id = R.drawable.img),
                    contentScale = ContentScale.Crop
                )
                //product's main image
                Image(
                    painter = painter,
                    modifier = Modifier
                        .height(180.dp)
                        .width(150.dp),
                    contentDescription = "products_img"
                )

            }

            Spacer(modifier = Modifier.width(4.dp))

            //title and description's column
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //title text
                Text(
                    text = model.title ?: "Title",
                    color = Color.Black,
                    fontSize = 22.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp)
                )

                //description text
                Text(
                    text = model.description ?: "Description",
                    color = Color.Black,
                    fontSize = 14.sp,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.SansSerif,
                )

            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            //category
            AnnotatedTextView(
                startText = "category",
                endText = model.category ?: "default category"
            )

            //price
            AnnotatedTextView(startText = "price", endText = model.price.toString())

            //rating
//            AnnotatedTextView(startText = "rating", endText = model.rating?.rate.toString())

        }
    }

}

@Composable
fun AnnotatedTextView(startText: String, endText: String) =
    Text(text = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textAlign = TextAlign.Center)) {
            withStyle(
                style = SpanStyle(
                    color = Color.Black,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            ) {
                append("$startText:  ")
            }
            withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 14.sp)) {
                append(text = endText)
            }
        }
    }, modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp))

@Preview(showBackground = true)
@Composable
fun PreviewItem() {
    ProductsItem(
        model = Product(
            id = 25,
            title = "Title",
            price = 27.980,
            category = "men's clothing",
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            image = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg",
//            rating = Product.RatingModel(3.9, 200)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController())
}