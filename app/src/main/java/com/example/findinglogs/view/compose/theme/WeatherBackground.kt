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
            listOf(Color(0xFF0B1026), Color(0xFF232C5B))
        else
            listOf(Color(0xFF2196F3), Color(0xFFFFB74D))

        "02" -> if (isNight)
            listOf(Color(0xFF1B2238), Color(0xFF3A4A63))
        else
            listOf(Color(0xFF2D9CDB), Color(0xFFF2C879))
        "03", "04" -> if (isNight)
            listOf(Color(0xFF2B2F3A), Color(0xFF4A5366))
        else
            listOf(Color(0xFF5C6B7A), Color(0xFF9BA8B5))

        "09", "10" -> listOf(Color(0xFF1A2536), Color(0xFF3E5871))

        "11" -> listOf(Color(0xFF15151F), Color(0xFF3A2E4D))

        "13" -> listOf(Color(0xFF6E8CA8), Color(0xFFC7DCEC))

        "50" -> listOf(Color(0xFF4B5563), Color(0xFF8A95A3))

        else -> listOf(WeatherColors.BackgroundTop, WeatherColors.BackgroundBottom)
    }
    return Brush.verticalGradient(colors)
}