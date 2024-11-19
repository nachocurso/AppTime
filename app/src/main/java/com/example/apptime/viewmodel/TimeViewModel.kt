package com.example.apptime.viewmodel

import androidx.lifecycle.ViewModel
import com.example.apptime.data.model.SucesoHistorico
import com.example.apptime.data.repository.ListaSucesosHistoricos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimeViewModel:ViewModel() {
    // obtener datos repositorio
    private val dataSourceListadoSucesos = ListaSucesosHistoricos()
    private val listaSucesos = dataSourceListadoSucesos.getListadoSucesosHistoricos()
    // Estado interno de los datos de sucesos
    private val _sucesosHistoricos = MutableStateFlow(listaSucesos)
    // estado publico de los datos de animales
    val sucesosHistoricos : StateFlow<List<SucesoHistorico>> get() = _sucesosHistoricos

    fun filtrarSucesos(tituloSuceso: String) {
        val sucesosFiltrados = if(tituloSuceso.isEmpty())  {
            listaSucesos
        } else {
            listaSucesos.filter {
                it.tituloSuceso.contains(tituloSuceso, ignoreCase = true)
            }
        }
        _sucesosHistoricos.value = sucesosFiltrados
    }
}