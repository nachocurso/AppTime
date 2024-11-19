package com.example.apptime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.apptime.ui.screenBuscar.ScreenBuscar
import com.example.apptime.ui.screenLineaTemporal.ScreenLineaTemporal
import com.example.apptime.ui.screenSobreMi.ScreenSobreMi
import com.example.apptime.viewmodel.TimeViewModel


@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // Definimos el contenedor de rutas de navegacion
    NavHost(
        navController = navController,
        startDestination = "linea",
        modifier = modifier
    ) {
        composable(DestinosNavegacion.linea.ruta) { ScreenLineaTemporal(TimeViewModel()) }
        composable(DestinosNavegacion.buscar.ruta) { ScreenBuscar(TimeViewModel()) }
        composable(DestinosNavegacion.sobre.ruta) { ScreenSobreMi() }
    }
}


