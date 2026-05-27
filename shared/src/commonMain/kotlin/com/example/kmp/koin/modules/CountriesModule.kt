package com.example.kmp.koin.modules

import kmp.shared.generated.resources.Res
import kmp.shared.generated.resources.eg
import kmp.shared.generated.resources.fr
import kmp.shared.generated.resources.id
import kmp.shared.generated.resources.jp
import kmp.shared.generated.resources.mx
import kotlinx.datetime.TimeZone
import org.jetbrains.compose.resources.DrawableResource

class CountriesModule {
    fun getCountries() = listOf(
        Country("Japan", TimeZone.of("Asia/Tokyo"), Res.drawable.jp),
        Country("France", TimeZone.of("Europe/Paris"), Res.drawable.fr),
        Country("Mexico", TimeZone.of("America/Mexico_City"), Res.drawable.mx),
        Country("Indonesia", TimeZone.of("Asia/Jakarta"), Res.drawable.id),
        Country("Egypt", TimeZone.of("Africa/Cairo"), Res.drawable.eg),
    )
}

data class Country(
    val name: String,
    val zone: TimeZone,
    val imageCountry: DrawableResource
)