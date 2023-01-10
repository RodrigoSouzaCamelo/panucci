package br.com.rodrigo.panucci.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.tooling.preview.Preview
import br.com.rodrigo.panucci.sampledata.bottomAppBarItems
import br.com.rodrigo.panucci.sampledata.sampleProductWithImage
import br.com.rodrigo.panucci.sampledata.sampleProducts
import br.com.rodrigo.panucci.ui.PanucciApp
import br.com.rodrigo.panucci.ui.screens.*
import br.com.rodrigo.panucci.ui.theme.PanucciTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val initialScreen = "Destaques"
            val screens = remember {
                mutableStateListOf(initialScreen)
            }

            Log.i("MainActivity", "onCreate: screens ${screens.toList()}")

            val currentScreen = screens.last()
            BackHandler(screens.size > 1) {
                screens.removeLast()
            }

            PanucciTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedItem by remember(currentScreen) {
                        val item = bottomAppBarItems.find { currentScreen == it.label }
                        mutableStateOf(item)
                    }

                    PanucciApp(
                        bottomAppBarItemSelected = selectedItem ?: bottomAppBarItems.first(),
                        onFabClick = { screens.add("Pedido") },
                        onBottomAppBarItemSelectedChange = {
                            selectedItem = it
                            screens.add(it.label)
                        }
                    ) {
                        Routes(currentScreen, screens)
                    }
                }
            }
        }
    }
}

@Composable
fun Routes(currentScreen: String, screens: SnapshotStateList<String>) {
    when (currentScreen) {
        "Destaques" -> HighlightsListScreen(
            products = sampleProducts,
            onOrderClick = { screens.add("Pedido") },
            onProductClick = { screens.add("DetalhesProduto") }
        )
        "Menu" -> MenuListScreen(products = sampleProducts)
        "Bebidas" -> DrinksListScreen(products = sampleProducts + sampleProducts)
        "DetalhesProduto" -> ProductDetailsScreen(product = sampleProductWithImage)
        "Pedido" -> CheckoutScreen(products = sampleProducts)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PanucciTheme {

    }
}