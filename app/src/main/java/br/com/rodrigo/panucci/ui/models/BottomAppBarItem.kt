package br.com.rodrigo.panucci.ui.models

import androidx.compose.ui.graphics.vector.ImageVector
import br.com.rodrigo.panucci.ui.navigation.AppDestination

class BottomAppBarItem(
    val label: String,
    val icon: ImageVector,
    val destination: AppDestination
)