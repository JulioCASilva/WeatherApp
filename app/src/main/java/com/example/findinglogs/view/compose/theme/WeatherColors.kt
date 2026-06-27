package com.example.findinglogs.view.compose.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object WeatherColors {
    val BackgroundTop = Color(0xFF1C3A6B)
    val BackgroundMid = Color(0xFF1E5BA8)
    val BackgroundBottom = Color(0xFF2E89C9)

    val TextPrimary = Color(0xFFFFFFFF)
    val TextSecondary = Color(0xFFB9CDE4)
    val TextMuted = Color(0xFF8FA8C7)

    val CardGlass = Color(0x1FFFFFFF)
    val CardBorder = Color(0x33FFFFFF)
    val Divider = Color(0x1FFFFFFF)
    val IconCircle = Color(0x26FFFFFF)

    val BackgroundGradient = Brush.verticalGradient(
        colors = listOf(BackgroundTop, BackgroundMid, BackgroundBottom)
    )
}