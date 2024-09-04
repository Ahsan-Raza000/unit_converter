package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitconverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitconverterTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Unitconverter()
                }
            }
        }
    }
}

@Composable
fun Unitconverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Kilograms") }
    var outputUnit by remember { mutableStateOf("Kilograms") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }


    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 16.sp,
        color = Color.Blue
    )

    fun convertUnits(){

        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.exchange),
            contentDescription = "Unit Converter Icon",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Easy Weight Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it.filter { char -> char.isDigit() || char == '.' }
                convertUnits()
            },
            label = { Text("Enter Value") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )



        Spacer(modifier = Modifier.height(16.dp))
        Row {

            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Milligrams") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milligrams"
                            conversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Grams") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Grams"
                            conversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilograms") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Kilograms"
                            conversionFactor.value = 1000.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Ounces") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Ounces"
                            conversionFactor.value = 28.3495
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pounds") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Pounds"
                            conversionFactor.value = 453.592
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Stones") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Stones"
                            conversionFactor.value = 6350.29
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Milligrams") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milligrams"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Grams") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Grams"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilograms") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Kilograms"
                            oConversionFactor.value = 1000.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Ounces") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Ounces"
                            oConversionFactor.value = 28.3495
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Pounds") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Pounds"
                            oConversionFactor.value = 453.592
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Stones") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Stones"
                            oConversionFactor.value = 6350.29
                            convertUnits()
                        }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    Unitconverter()
}
