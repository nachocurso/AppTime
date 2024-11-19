package com.example.apptime.ui.screenBuscar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptime.R
import com.example.apptime.data.model.SucesoHistorico
import com.example.apptime.viewmodel.TimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBuscar(
    viewModel: TimeViewModel,
    modifier: Modifier = Modifier
) {

    // Estado para gestionar el texto del campo de búsqueda
    var textoBusqueda by remember { mutableStateOf("") }

    // Observar el estado de la lista de sucesos
    val sucesosFiltrados by viewModel.sucesosHistoricos.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Campo de búsqueda
        OutlinedTextField(
            value = textoBusqueda,
            onValueChange = {
                textoBusqueda = it
                viewModel.filtrarSucesos(it)
                            },
            label = { Text("Buscar suceso histórico") },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color.Black,
                unfocusedContainerColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            trailingIcon = { // Agregar icono para borrar texto
                if (textoBusqueda.isNotEmpty()) {
                    IconButton(onClick = {
                        textoBusqueda = ""
                        viewModel.filtrarSucesos(textoBusqueda)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_clear_24), // Reemplaza con tu icono de borrar
                            contentDescription = "Borrar texto",
                            tint = Color.White
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done // Mostrar botón de aceptar en el teclado
            ),
        )

        // Lista filtrada de sucesos
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(sucesosFiltrados) { suceso ->
                VisualizaTarjeta(suceso)
            }
        }
    }
}


@Composable
fun VisualizaTarjeta(sucesoHistorico: SucesoHistorico) {
    // hacer expansible la tarjeta
    var esExpandible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .animateContentSize(
                animationSpec = androidx.compose.animation.core.spring(
                    dampingRatio = 0.4f, // Rebote más visible
                    stiffness = 30f // Animación más lenta
                )
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black // Fondo negro de la tarjeta
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Imagen en la parte superior
            Image(
                painter = painterResource(id = sucesoHistorico.imagenSuceso), // Reemplaza con tu imagen específica
                contentDescription = "Imagen de ${sucesoHistorico.tituloSuceso}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )

            // Texto del título
            Text(
                text = sucesoHistorico.tituloSuceso,
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            // Texto del subtítulo (fechas o descripción)
            Text(
                text = sucesoHistorico.fecha,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            // Descripción expandible
            if (esExpandible) {
                Text(
                    text = sucesoHistorico.descripcionSuceso,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    lineHeight = 20.sp
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = { esExpandible= !esExpandible } // Cambia el estado al pulsar
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (esExpandible) R.drawable.baseline_expand_less_24 else R.drawable.baseline_expand_more_24// Reemplaza con tus iconos
                        ),
                        contentDescription = if (esExpandible) "Contraer" else "Expandir",
                        tint = Color.White
                    )
                }
            }

        }
    }
}