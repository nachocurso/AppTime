package com.example.apptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.apptime.ui.components.ScaffoldPrincipal
import com.example.apptime.ui.theme.AppTimeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTimeTheme {
                ScaffoldPrincipal()
            }
        }
    }
}
