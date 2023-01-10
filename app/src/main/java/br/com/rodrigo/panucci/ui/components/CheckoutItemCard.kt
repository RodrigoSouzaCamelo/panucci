package br.com.rodrigo.panucci.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.rodrigo.panucci.models.Product
import br.com.rodrigo.panucci.sampledata.sampleProductWithImage
import br.com.rodrigo.panucci.sampledata.sampleProductWithoutImage
import br.com.rodrigo.panucci.ui.theme.PanucciTheme
import coil.compose.AsyncImage
import br.com.rodrigo.panucci.R
import br.com.rodrigo.panucci.ui.theme.CheckoutCircleButton

@Composable
fun CheckoutItemCard(
    product: Product,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .height(80.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .weight(9f)
                    .fillMaxHeight()
            ) {
                product.image?.let { image ->
                    AsyncImage(
                        model = image,
                        contentDescription = null,
                        modifier = Modifier.width(80.dp),
                        placeholder = painterResource(
                            id = R.drawable.placeholder,
                        ),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = product.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = product.price.toPlainString())
                }
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                var quantity by remember {
                    mutableStateOf(1)
                }

                val circleButtonModifier = Modifier
                    .size(20.dp)
                    .background(
                        CheckoutCircleButton,
                        shape = CircleShape
                    )
                    .clip(CircleShape)

                Box(
                    modifier = circleButtonModifier
                        .clickable { quantity++ }
                ) {
                    Icon(
                        Icons.Filled.ArrowDropUp,
                        contentDescription = null
                    )
                }

                Text(text = "$quantity")

                Box(
                    modifier = circleButtonModifier
                        .clickable {
                            if (quantity > 1) {
                                quantity--
                            }
                        }
                ) {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CheckoutItemCardPreview() {
    PanucciTheme {
        CheckoutItemCard(
            product = sampleProductWithoutImage
        )
    }
}

@Preview
@Composable
private fun CheckoutItemCardWithImagePreview() {
    PanucciTheme {
        CheckoutItemCard(
            product = sampleProductWithImage
        )
    }
}