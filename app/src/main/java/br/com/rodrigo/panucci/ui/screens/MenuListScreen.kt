package br.com.rodrigo.panucci.ui.screens

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
import br.com.rodrigo.panucci.ui.components.MenuProductCard
import br.com.rodrigo.panucci.ui.theme.PanucciTheme
import br.com.rodrigo.panucci.ui.theme.caveatFont


@Composable
fun MenuListScreen(
    modifier: Modifier = Modifier,
    title: String = "Menu",
    products: List<Product> = emptyList(),
) {
    Column(
        modifier = modifier.fillMaxSize()
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
                MenuProductCard(
                    product = p,
                )
            }
        }
    }
}

@Preview
@Composable
fun MenuListScreenPreview() {
    PanucciTheme {
        Surface {
            MenuListScreen(
                products = sampleProducts
            )
        }
    }
}