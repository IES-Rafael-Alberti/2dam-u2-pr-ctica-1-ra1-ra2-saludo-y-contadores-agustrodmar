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


/**
 * Actividad principal de la aplicación. Primera pantalla que ven los usuarios
 * cuando abren la aplicación. En esta actividad, se muestra el botón de saludo.
 */
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

/**
 * GreetingButton, composable que muestra un botón en el centro de la pantalla.
 * Cuando se hace clic en el botón, se abre un cuadro de diálogo que permite al usuario introducir su nombre.
 * El cuadro de diálogo tiene tres botones: "Aceptar", "Limpiar" y "Cancelar".
 * - "Aceptar" cierra el cuadro de diálogo, muestra un saludo personalizado en la pantalla y actualiza los contadores de los botones.
 * - "Limpiar" borra el campo de texto dentro del cuadro de diálogo.
 * - "Cancelar" cierra el cuadro de diálogo, borra el saludo de la pantalla y actualiza los contadores de los botones.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun GreetingButton() {
    // Variables de estado para almacenar los valores de los campos de texto,
    // los contadores y el estado del diálogo
    val text = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val name = remember { mutableStateOf("") }
    val acceptCount = remember { mutableStateOf(0) }
    val cancelCount = remember { mutableStateOf(0) }
    val hasClicked = remember { mutableStateOf(false) }

    // Box que ocupa todo el espacio disponible
    Box(modifier = Modifier.fillMaxSize()) {
        // Columna centrada para el botón y el texto
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Botón que muestra "Saludar" o los contadores dependiendo
            // de si se ha hecho clic en "Aceptar" o "Cancelar"
            Button(onClick = { showDialog.value = true }) {
                Text(if (hasClicked.value) "Saludar A${acceptCount.value} C${cancelCount.value}" else "Saludar")
            }

            // Espaciador para dar espacio entre el botón y el texto
            Spacer(modifier = Modifier.height(16.dp))

            // Texto que muestra el valor de la variable de estado 'text'
            Text(
                text = text.value,
                modifier = Modifier.widthIn(max = 200.dp)  // Limita el ancho
            )
        }

        // Diálogo que se muestra cuando 'showDialog' es verdadero
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = {
                    // Fila para alinear el título a la derecha
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            "Configuración",
                            style = MaterialTheme.typography.labelLarge.copy(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                },
                text = {
                    // Columna con padding para el contenido del diálogo
                    Column(
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 20.dp)
                    ) {
                        Text("Introduce tu nombre")
                        TextField(value = name.value, onValueChange = { name.value = it })
                        Spacer(modifier = Modifier.height(20.dp)) // Espacio entre TextField y los botones

                        // Fila con los botones "Aceptar", "Limpiar" y "Cancelar"
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly  // Espaciado uniforme entre los botones
                        ) {
                            // Botón "Aceptar" que actualiza el texto y los contadores
                            Button(
                                modifier = Modifier.weight(1f).widthIn(min = 80.dp),
                                onClick = {
                                    text.value = "¡Hola, ${name.value}!"
                                    showDialog.value = false
                                    acceptCount.value++
                                    hasClicked.value = true
                                }
                            ) {
                                Text("Aceptar", fontSize = 8.sp)
                            }

                            Spacer(modifier = Modifier.width(16.dp))  // Espacio entre los botones

                            // Botón "Limpiar" que borra el campo de texto
                            Button(
                                modifier = Modifier.weight(1f).widthIn(min = 80.dp),
                                onClick = { name.value = "" }
                            ) {
                                Text("Limpiar", fontSize = 8.sp)
                            }

                            Spacer(modifier = Modifier.width(16.dp))  // Espacio entre los botones

                            // Botón "Cancelar" que cierra el diálogo y actualiza los contadores
                            Button(
                                modifier = Modifier.weight(1f).widthIn(min = 80.dp),
                                onClick = {
                                    text.value = ""
                                    showDialog.value = false
                                    cancelCount.value++
                                    hasClicked.value = true
                                }
                            ) {
                                Text("Cancelar", fontSize = 7.5.sp)
                            }
                        }
                    }
                },
                confirmButton = { }
            )
        }
    }
}
