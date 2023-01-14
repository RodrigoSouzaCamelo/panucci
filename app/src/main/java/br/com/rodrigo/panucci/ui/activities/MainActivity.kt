package br.com.rodrigo.panucci.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.rodrigo.panucci.sampledata.sampleProducts
import br.com.rodrigo.panucci.ui.PanucciApp
import br.com.rodrigo.panucci.ui.navigation.AppDestination
import br.com.rodrigo.panucci.ui.navigation.bottomAppBarItems
import br.com.rodrigo.panucci.ui.screens.*
import br.com.rodrigo.panucci.ui.theme.PanucciTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val backStackEntryState by navController.currentBackStackEntryAsState()
            val currentDestination = backStackEntryState?.destination

            LaunchedEffect(Unit) {
                navController.addOnDestinationChangedListener { _, _, _ ->
                    val routes = navController.backQueue
                        .map { it.destination.route }

                    Log.i("MainActivity", "onCreate: back stack - $routes")
                }
            }

            PanucciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var selectedItem by remember(currentDestination) {
                        val item = currentDestination.let { destination ->
                            bottomAppBarItems.find {
                                it.destination.route == destination?.route
                            } ?: bottomAppBarItems.first()
                        }

                        mutableStateOf(item)
                    }

                    val containsInBottomAppBarItems = currentDestination?.let { destination ->
                        bottomAppBarItems.find {
                            it.destination.route == destination?.route
                        }
                    } != null

                    val isShowFabButton = when (currentDestination?.route) {
                        AppDestination.Menu.route,
                        AppDestination.Drinks.route -> true
                        else -> false
                    }

                    PanucciApp(
                        isShowFabButton  = isShowFabButton,
                        isShowTopBar = containsInBottomAppBarItems,
                        isShowBottomBar = containsInBottomAppBarItems,
                        bottomAppBarItemSelected = selectedItem,
                        onFabClick = { navController.navigate(AppDestination.Checkout.route) },
                        onBottomAppBarItemSelectedChange = {
                            navController.navigate(it.destination.route) {
                                launchSingleTop = true
                                popUpTo(it.destination.route)
                            }
                        }
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = AppDestination.Highlight.route
                        ) {
                            composable(AppDestination.Highlight.route) {
                                HighlightsListScreen(
                                    products = sampleProducts,
                                    onNavigateToCheckout = {
                                        navController.navigate(AppDestination.Checkout.route)
                                    },
                                    onNavigateToDetails = {
                                        navController.navigate(
                                            "${AppDestination.ProductDetails.route}/${it.id}"
                                        )

                                        Log.i("MainActivity", "onCreate: ${it.name}")
                                    }
                                )
                            }
                            composable(AppDestination.Menu.route) {
                                MenuListScreen(
                                    products = sampleProducts,
                                    onNavigateToDetails = {
                                        navController.navigate(
                                            "${AppDestination.ProductDetails.route}/${it.id}"
                                        )
                                    }
                                )
                            }
                            composable(AppDestination.Drinks.route) {
                                DrinksListScreen(
                                    products = sampleProducts,
                                    onNavigateToDetails = {
                                        navController.navigate(
                                            "${AppDestination.ProductDetails.route}/${it.id}"
                                        )
                                    }
                                )
                            }
                            composable(
                                "${AppDestination.ProductDetails.route}/{productId}"
                            ) { backStackEntry ->

                                val productId = backStackEntry.arguments?.getString("productId")
                                ProductDetailsScreen(
                                    product = sampleProducts.first { it.id == productId },
                                    onNavigateToCheckout = {
                                        navController.navigate(AppDestination.Checkout.route)
                                    }
                                )
                            }
                            composable(AppDestination.Checkout.route) {
                                CheckoutScreen(products = sampleProducts)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PanucciTheme {

    }
}