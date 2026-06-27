package com.example.findinglogs.view.compose.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.findinglogs.model.model.Weather

fun Weather.backgroundGradient(): Brush {
    val code = weather?.firstOrNull()?.icon.orEmpty()
    val isNight = code.endsWith("n")
    val type = code.take(2)

    val colors = when (type) {
        "01" -> if (isNight)
            listOf(Color(0xFF0B1B3A), Color(0xFF1A2A52))
        else
            listOf(Color(0xFF2980B9), Color(0xFF6DD5FA))

        "02", "03", "04" -> if (isNight)
            listOf(Color(0xFF232B3E), Color(0xFF3A4A63))
        else
            listOf(Color(0xFF1C3A6B), Color(0xFF5B7DA8))

        "09", "10" -> listOf(Color(0xFF1A2536), Color(0xFF3E5871))

        "11" -> listOf(Color(0xFF15151F), Color(0xFF3A2E4D))

        "13" -> listOf(Color(0xFF4A6585), Color(0xFFB8D0E6))

        "50" -> listOf(Color(0xFF4B5563), Color(0xFF6B7785))

        else -> listOf(WeatherColors.BackgroundTop, WeatherColors.BackgroundBottom)
    }
    return Brush.verticalGradient(colors)
}