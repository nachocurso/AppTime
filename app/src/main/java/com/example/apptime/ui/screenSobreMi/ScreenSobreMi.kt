package com.example.apptime.ui.screenSobreMi

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptime.R

@Composable
fun ScreenSobreMi() {
    // establecemos control sobre la caja de nombre y correo
    var nombre by remember { mutableStateOf("Nacho Sáez") }
    var correo by remember { mutableStateOf("joserodriguezsaez@gmail.com") }

    var editarNombre by remember { mutableStateOf(false) }
    var editarCorreo by remember { mutableStateOf(false) }

    // Obtenemos el LocalFocusManager
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card (
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black // Fondo negro de la tarjeta
            )
        ){
            Text(
                text = nombre,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge
            )
            Image(
                painter = painterResource(id = R.drawable.unnamed), // Asegúrate de usar tu recurso correcto
                contentDescription = "Foto del estudiante",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(16.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp, // Redondeado
                            topEnd = 0.dp,  // Redondeado
                            bottomStart = 0.dp, // Esquina recta
                            bottomEnd = 16.dp   // Esquina recta
                        )
                    )
            )
            Text(
                text = "Estudiante DAM Semipresencial",
                color = Color.White,
                modifier = Modifier.padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.titleLarge
            )
            if(editarNombre) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Editar Nombre") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            editarNombre = false // Salimos del modo edición
                            focusManager.clearFocus() // Ocultamos el teclado
                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedContainerColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)

                )
            } else {
                Text(
                    text = nombre,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { editarNombre = true }
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.titleLarge
                )
            }
            // editar correo
            if (editarCorreo) {
                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("Editar Correo Electrónico") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            editarCorreo = false // Salimos del modo edición
                            focusManager.clearFocus() // Ocultamos el teclado
                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Black,
                        unfocusedContainerColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            } else {
                Text(
                    text = correo,
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable { editarCorreo = true }
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }
        }
    }

}