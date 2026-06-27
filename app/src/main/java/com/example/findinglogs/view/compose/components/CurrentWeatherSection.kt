package com.example.findinglogs.view.compose.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findinglogs.model.model.Weather
import com.example.findinglogs.view.compose.theme.WeatherColors

@Composable
fun CurrentWeatherSection(
    weather: Weather,
    country: String = "Brasil",
    modifier: Modifier = Modifier
) {
    val info = weather.main

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = weather.name.orEmpty(),
            color = WeatherColors.TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(country, color = WeatherColors.TextSecondary, fontSize = 16.sp)

        Spacer(Modifier.height(24.dp))

        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = weather.iconVector(),
                contentDescription = null,
                tint = WeatherColors.TextPrimary,
                modifier = Modifier.size(180.dp).alpha(0.12f)
            )
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = "${info.temp.toCelsius()}",
                    color = WeatherColors.TextPrimary,
                    fontSize = 120.sp,
                    fontWeight = FontWeight.Thin
                )
                Text(
                    text = "°C",
                    color = WeatherColors.TextPrimary,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top = 18.dp)
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = weather.descriptionLabel(),
            color = WeatherColors.TextPrimary,
            fontSize = 22.sp
        )
        Text(
            text = "H: ${info.temp_max.toCelsius()}°  L: ${info.temp_min.toCelsius()}°",
            color = WeatherColors.TextSecondary,
            fontSize = 16.sp
        )
    }
}