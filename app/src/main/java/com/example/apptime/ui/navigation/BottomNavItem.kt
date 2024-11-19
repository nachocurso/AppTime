package com.example.apptime.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector


// Data class para definir los botones de la barra inferior

data class BottomNavItem(
    val route : String, // Ruta de navegacion asociada al boton
    val icon : ImageVector, // Icono del boton
    var label : String // Etiqueta del boton
)

// Definir la lista de botones de la barra
val bottomNavItems = listOf(
    BottomNavItem("linea", Icons.Default.DateRange, "Línea"),
    BottomNavItem("buscar", Icons.Default.Search, "Buscar"),
    BottomNavItem("sobre", Icons.Default.Face, "Sobre")
)