package com.example.apptime.ui.navigation

sealed class DestinosNavegacion (val ruta: String, val titulo: String){
    // definicion de los objetos de ruta asociados a las pantallas
    object linea: DestinosNavegacion("linea", "Línea")
    object buscar: DestinosNavegacion("buscar", "Buscar")
    object sobre: DestinosNavegacion("sobre", "Sobre")

}