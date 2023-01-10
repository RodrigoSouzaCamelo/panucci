package br.com.rodrigo.panucci.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.rodrigo.panucci.R
import br.com.rodrigo.panucci.models.Product
import br.com.rodrigo.panucci.sampledata.sampleProductWithImage
import br.com.rodrigo.panucci.sampledata.sampleProductWithoutImage
import br.com.rodrigo.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage

@Composable
fun HighlightProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onOrderClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            product.image?.let { image ->
                AsyncImage(
                    image,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(116.dp)
                )
            }
            Column(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
            ) {
                Text(text = product.name)

                Text(text = product.price.toString())

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = product.description,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Button(
                onClick = { onOrderClick() },
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 24.dp
                    )
                    .clickable { onOrderClick() }
                    .align(Alignment.End)
            ) {
                Text(text = "Pedir")
            }
        }
    }
}

@Preview
@Composable
private fun HighlightProductPreview() {
    PanucciTheme {
        HighlightProductCard(
            product = sampleProductWithoutImage
        )
    }
}

@Preview
@Composable
private fun HighlightProductCardWithImagePreview() {
    PanucciTheme {
        HighlightProductCard(
            product = sampleProductWithImage
        )
    }
}