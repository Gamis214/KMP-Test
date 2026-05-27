package com.example.kmp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.forEach
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmp.koin.modules.CountriesModule
import kmp.shared.generated.resources.Res
import kmp.shared.generated.resources.eg
import kmp.shared.generated.resources.fr
import kmp.shared.generated.resources.id
import kmp.shared.generated.resources.jp
import kmp.shared.generated.resources.mx
import kotlinx.datetime.IllegalTimeZoneException
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.koin.core.Koin
import kotlin.time.Clock

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {

    val listOfCountries = koinInject<CountriesModule>()
    var timeAtLocation by remember { mutableStateOf("No location selected") }
    var showCountries by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(listOfCountries.getCountries()[0].name) }

    MaterialTheme {

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                style = TextStyle(fontSize = 20.sp),
                text = timeAtLocation,
                textAlign = TextAlign.Center,
            )

            ExposedDropdownMenuBox(
                expanded = showCountries,
                onExpandedChange = { showCountries = !showCountries },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                TextField(
                    value = selectedOption,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Selecciona una ubicación") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = showCountries) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = showCountries,
                    onDismissRequest = { showCountries = false }
                ) {
                    listOfCountries.getCountries().forEach { (nameCountry,zoneCountry,imageCountry) ->
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painterResource(imageCountry),
                                        modifier = Modifier.size(50.dp).padding(end = 10.dp),
                                        contentDescription = "$nameCountry flag"
                                    )
                                    Text(nameCountry)
                                }
                            },
                            onClick = {
                                selectedOption = nameCountry
                                showCountries = false
                                timeAtLocation = currentTimeAt(nameCountry, zoneCountry)
                            }
                        )
                    }
                }
            }
            Button(
                modifier = Modifier.padding(top = 10.dp),
                onClick = { showCountries = !showCountries }
            ){
                Text("Select Location")
            }
        }
    }
}

fun currentTimeAt(location: String,zone: TimeZone): String{
    fun LocalTime.formatted() = "$hour:$minute:$second"
    val time = Clock.System.now()
    val localTime = time.toLocalDateTime(zone).time
    return "The time in $location is ${localTime.formatted()}"
}