package com.ncrdesarrollo.fakestoreapicompose.products.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ncrdesarrollo.fakestoreapicompose.R
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.ProductViewData
import com.ncrdesarrollo.fakestoreapicompose.products.ui.models.UiState

@Composable
fun ProductViewScreen(
    viewModel: ProductViewViewModel,
    modifier: Modifier,
    navigateToBack: () -> Unit
) {
    val context = LocalContext.current
    val dataProduct by viewModel.productDataModel.collectAsState()
    when (val state = dataProduct) {
        UiState.Empty -> {}
        is UiState.Error -> {
            Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
        }

        UiState.Loading -> CenterLoading()
        is UiState.Success -> {
            Column(modifier = modifier.padding()) {
                ProductDetailScreen(product = state.data)
            }
        }
    }

}

@Composable
private fun CenterLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ProductDetailScreen(product: ProductViewData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Título del producto
        Text(
            text = product.title ?: "",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Precio del producto
        Text(
            text = "$${product.price}",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Lista horizontal de imágenes
        ImageGallery(images = product.images ?: listOf())

        // Descripción del producto
        Text(
            text = "Descripción",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Text(
            text = product.description ?: "",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

@Composable
fun ImageGallery(images: List<String>) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = "Imágenes",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .height(200.dp)
        ) {
            images.forEach { imageUrl ->
                ImageItem(imageUrl = imageUrl)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun ImageItem(imageUrl: String) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight()
            .padding(end = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter =
            rememberAsyncImagePainter(
                ImageRequest.Builder
                    (LocalContext.current).data(data = imageUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        placeholder(R.drawable.ic_launcher_background) // Asegúrate de tener este drawable
                        error(R.drawable.ic_launcher_background) // Asegúrate de tener este drawable
                    }).build()
            ),
            contentDescription = "Product image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}