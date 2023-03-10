package br.com.rodrigo.panucci.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.rodrigo.panucci.models.Product
import br.com.rodrigo.panucci.sampledata.sampleProducts
import br.com.rodrigo.panucci.ui.components.HighlightProductCard
import br.com.rodrigo.panucci.ui.theme.PanucciTheme
import br.com.rodrigo.panucci.ui.theme.caveatFont

@Composable
fun HighlightsListScreen(
    modifier: Modifier = Modifier,
    title: String = "Destaques do dia",
    products: List<Product> = emptyList(),
    onOrderClick: () -> Unit = {},
    onProductClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Surface {
            Text(
                text = title,
                fontFamily = caveatFont,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products) { p ->
                HighlightProductCard(
                    product = p,
                    onOrderClick = onOrderClick,
                    modifier = Modifier.clickable { onProductClick() },
                )
            }
        }
    }
}

@Preview
@Composable
fun HighlightsListScreenPreview() {
    PanucciTheme {
        Surface {
            HighlightsListScreen(
                products = sampleProducts,
                title = "Destaques do dia"
            )
        }
    }
}