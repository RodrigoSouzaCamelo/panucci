package br.com.rodrigo.panucci.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.rodrigo.panucci.R
import br.com.rodrigo.panucci.models.Product
import br.com.rodrigo.panucci.sampledata.sampleProductWithImage
import br.com.rodrigo.panucci.sampledata.sampleProductWithoutImage
import br.com.rodrigo.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage

@Composable
fun MenuProductCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .weight(3f)
            ) {
                Text(
                    text = product.name,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.price.toPlainString(),
                    Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight(400)
                )
            }
            product.image?.let { image ->
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(80.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuProductCardPreview() {
    PanucciTheme {
        MenuProductCard(
            product = sampleProductWithoutImage
        )
    }
}

@Preview
@Composable
fun MenuProductCardWithImagePreview() {
    PanucciTheme {
        MenuProductCard(
            product = sampleProductWithImage
        )
    }
}