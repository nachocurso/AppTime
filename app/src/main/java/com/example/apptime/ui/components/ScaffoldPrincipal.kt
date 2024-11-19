package com.example.apptime.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.apptime.ui.navigation.DestinosNavegacion
import com.example.apptime.ui.navigation.NavGraph
import com.example.apptime.ui.navigation.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldPrincipal() {
    val navController = rememberNavController()
    // Obtiene la entrada actual del back stack para determinar la pantalla activa.
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: DestinosNavegacion.linea.ruta

    Scaffold(
        containerColor = Color(0xFF1A1A40),
        contentColor = Color.White,
        topBar = {
            // Barra superior (TopAppBar) que muestra el título centrado.
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White,
                    //containerColor = MaterialTheme.colorScheme.primaryContainer,
                    // titleContentColor = contentColorFor(Color(0xFF1A1A40))
                ),
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(getScreenTitle(currentRoute)) // Llama a la función para obtener el título de la pantalla actual.
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                // Cargamos la lista de botones
                val botones = bottomNavItems
                // Recorrer lista cargando los botones
                botones.forEach { boton ->
                    NavigationBarItem(
                        icon = {
                           Icon(
                               boton.icon,
                               contentDescription = boton.label,
                               tint = if (currentRoute == boton.route) Color.Black else Color.White
                           )
                        },
                        label = {
                                    Text(
                                        text = boton.label,
                                        color = Color.White,
                                        style = MaterialTheme.typography.labelMedium
                                    )
                                },
                        selected = currentRoute == boton.route, // Indica si el ítem está seleccionado.
                        onClick = {
                            // Navega a la ruta del ítem y configura la pila de navegación.
                            navController.navigate(boton.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true // Guarda el estado al hacer pop up.
                                }
                                launchSingleTop = true // Evita múltiples instancias de la misma pantalla.
                                restoreState = true // Restaura el estado al navegar hacia atrás.
                            }
                        }
                    )
                }
            }
        }

    ) { innerPadding ->
        NavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
    }

}


fun getScreenTitle(route: String): String {
    return when (route) {
        DestinosNavegacion.linea.ruta -> DestinosNavegacion.linea.titulo
        DestinosNavegacion.buscar.ruta -> DestinosNavegacion.buscar.titulo
        DestinosNavegacion.sobre.ruta -> DestinosNavegacion.sobre.titulo
        else -> "" // En caso de que la ruta no coincida con ninguna, devuelve una cadena vacía.
    }
}


@Preview
@Composable
fun PreviewScaffoldPrincipal() {
    ScaffoldPrincipal()
}