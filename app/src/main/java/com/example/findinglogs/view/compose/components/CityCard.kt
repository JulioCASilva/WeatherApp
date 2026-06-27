package com.example.findinglogs.view.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findinglogs.model.model.Weather
import com.example.findinglogs.view.compose.theme.WeatherColors

@Composable
fun CityCard(
    weather: Weather,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isCurrentLocation: Boolean = false
) {
    val info = weather.main

    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, WeatherColors.CardBorder, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = WeatherColors.CardGlass)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(56.dp).background(WeatherColors.IconCircle, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = weather.iconVector(),
                        contentDescription = null,
                        tint = WeatherColors.TextPrimary,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(Modifier.width(14.dp))

                Column(Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (isCurrentLocation) {
                            Icon(
                                Icons.Filled.LocationOn, "Localização atual",
                                tint = WeatherColors.TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                        }
                        Text(
                            text = weather.name.orEmpty(),
                            color = WeatherColors.TextPrimary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = weather.descriptionLabel(),
                        color = WeatherColors.TextSecondary,
                        fontSize = 14.sp
                    )
                }

                Text(
                    text = "${info.temp.toCelsius()}°",
                    color = WeatherColors.TextPrimary,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light
                )
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight, null,
                    tint = WeatherColors.TextMuted,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = WeatherColors.Divider)
            Spacer(Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                Metric(Icons.Outlined.WaterDrop, "${info.humidity.toWhole()}%")
                Metric(Icons.Outlined.Speed, "${info.pressure.toWhole()} hPa")
            }
        }
    }
}

@Composable
private fun Metric(icon: ImageVector, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, null, tint = WeatherColors.TextSecondary, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(6.dp))
        Text(value, color = WeatherColors.TextSecondary, fontSize = 14.sp)
    }
}