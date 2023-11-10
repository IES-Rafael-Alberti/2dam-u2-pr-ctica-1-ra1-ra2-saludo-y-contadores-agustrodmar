@file:Suppress("SpellCheckingInspection")

package com.arodmar432p.a2dam_u2_pr_ctica_1_ra1_ra2_saludo_y_contadores_agustrodmar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arodmar432p.a2dam_u2_pr_ctica_1_ra1_ra2_saludo_y_contadores_agustrodmar.ui.theme._2damu2prctica1ra1ra2saludoycontadoresagustrodmarTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _2damu2prctica1ra1ra2saludoycontadoresagustrodmarTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            GreetingButton()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun GreetingButton() {
    val text = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val name = remember { mutableStateOf("") }
    val acceptCount = remember { mutableStateOf(0) }
    val cancelCount = remember { mutableStateOf(0) }

    Button(onClick = { showDialog.value = true }) {
        Text("Saludar A${acceptCount.value} C${cancelCount.value}")
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(text = text.value)

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Configuración", style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold)) },
            text = {
                Column(
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 20.dp)
                ) {
                    Text("Introduce tu nombre")
                    TextField(value = name.value, onValueChange = { name.value = it })
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            text.value = "¡Hola, ${name.value}!"
                            showDialog.value = false
                            acceptCount.value++
                        }) {
                            Text("Aceptar")
                        }
                        Button(onClick = { name.value = "" }) {
                            Text("Limpiar")
                        }
                        Button(onClick = {
                            showDialog.value = false
                            cancelCount.value++
                        }) {
                            Text("Cancelar")
                        }
                    }
                }
            },
            confirmButton = { }
        )
    }
}