package com.example.findinglogs.view.compose.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.Grain
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.findinglogs.model.model.Weather
import java.util.Locale
import kotlin.math.roundToInt

private fun Weather.iconCode(): String =
    weather?.firstOrNull()?.icon.orEmpty()

fun Weather.iconVector(): ImageVector {
    return when (iconCode().take(2)) {
        "01" -> Icons.Filled.WbSunny
        "02" -> Icons.Filled.WbCloudy
        "03", "04" -> Icons.Filled.Cloud
        "09", "10" -> Icons.Outlined.Grain
        "11" -> Icons.Outlined.Bolt
        "13" -> Icons.Outlined.AcUnit
        "50" -> Icons.Filled.Cloud
        else -> Icons.Filled.WbCloudy
    }
}

fun Weather.descriptionLabel(): String {
    val raw = weather?.firstOrNull()?.description.orEmpty()
    return raw.split(" ").joinToString(" ") { word ->
        word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale("pt", "BR")) else it.toString() }
    }
}

fun Float.toCelsius(): Int = (this - 273.15f).roundToInt()

fun Float.toWhole(): Int = this.roundToInt()