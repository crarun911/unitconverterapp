package com.dcoders.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dcoders.myfirstapp.ui.theme.MyFirstAppTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverterUi()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverterUi() {
    var inputValue by remember { mutableStateOf(" ") }
    var outputValue by remember { mutableStateOf(" ") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor by remember { mutableStateOf(1.00) }
    var oConversionFactor by remember { mutableStateOf(1.00) }

    fun convertionUnit() {

        val inputValueToDouble = inputValue.toDoubleOrNull() ?: 0.0
        var result = (inputValueToDouble * conversionFactor * 100.0/oConversionFactor).roundToInt() / 100.0
        outputValue =  result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter",style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue = it
                convertionUnit()
            },
            label = { Text(text = "Enter the value") })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(text = { Text(text = "Centemeters") }, onClick = {
                            iExpanded = false
                            inputUnit = "Centemeters"
                            conversionFactor = 0.01
                            convertionUnit()
                        })
                        DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor = 1.0
                            convertionUnit()
                        })
                        DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor = 0.3048
                            convertionUnit()

                        })
                        DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor = 0.001
                            convertionUnit()

                        })
                    }
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit
                    )
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                        DropdownMenuItem(text = { Text(text = "Centemeters") }, onClick = {
                            oExpanded = false
                            outputUnit = "Centemeters"
                            oConversionFactor = 0.01
                            convertionUnit()
                        })
                        DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor = 1.0
                            convertionUnit()

                        })
                        DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor = 0.3048
                            convertionUnit()

                        })
                        DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor = 0.001
                            convertionUnit()

                        })
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Result : $outputValue $outputUnit"
            , style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyFirstAppTheme {
    }
}