package com.example.findinglogs.view.compose.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material.icons.outlined.Thermostat
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findinglogs.model.model.Weather
import com.example.findinglogs.view.compose.components.descriptionLabel
import com.example.findinglogs.view.compose.components.iconVector
import com.example.findinglogs.view.compose.components.toCelsius
import com.example.findinglogs.view.compose.components.toWhole
import com.example.findinglogs.view.compose.theme.WeatherColors
import com.example.findinglogs.view.compose.theme.backgroundGradient
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WeatherDetailScreen(
    weather: Weather,
    onBack: () -> Unit
) {
    val info = weather.main
    val scroll = rememberScrollState()
    val hora = remember { SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()) }

    Box(
        Modifier.fillMaxSize().background(weather.backgroundGradient())
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(scroll)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .size(46.dp)
                        .background(WeatherColors.IconCircle, CircleShape)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Voltar", tint = WeatherColors.TextPrimary)
                }
                Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("BRASIL", color = WeatherColors.TextSecondary, fontSize = 13.sp, letterSpacing = 2.sp)
                    Text(weather.name.orEmpty(), color = WeatherColors.TextPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(Modifier.height(32.dp))

            Box(
                Modifier.size(140.dp).background(WeatherColors.IconCircle, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(weather.iconVector(), null, tint = WeatherColors.TextPrimary, modifier = Modifier.size(64.dp))
            }

            Spacer(Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.Top) {
                Text("${info.temp.toCelsius()}", color = WeatherColors.TextPrimary, fontSize = 90.sp, fontWeight = FontWeight.Thin)
                Text("°C", color = WeatherColors.TextPrimary, fontSize = 28.sp, modifier = Modifier.padding(top = 14.dp))
            }
            Text(weather.descriptionLabel(), color = WeatherColors.TextPrimary, fontSize = 20.sp)

            Spacer(Modifier.height(28.dp))

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                DetailCard(Modifier.weight(1f), Icons.Outlined.WaterDrop, "UMIDADE", "${info.humidity.toWhole()}%", comfortLabel(info.humidity))
                DetailCard(Modifier.weight(1f), Icons.Outlined.Speed, "PRESSÃO", "${info.pressure.toWhole()}", "hPa")
            }
            Spacer(Modifier.height(14.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                DetailCard(Modifier.weight(1f), Icons.Outlined.Thermostat, "SENSAÇÃO", "${info.feels_like.toCelsius()}°", "Sensação térmica")
                DetailCard(Modifier.weight(1f), Icons.Outlined.Schedule, "ATUALIZADO", hora, "Hora local")
            }

            Spacer(Modifier.height(14.dp))
            Column(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(WeatherColors.CardGlass)
                    .border(1.dp, WeatherColors.CardBorder, RoundedCornerShape(20.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.LocationOn, null, tint = WeatherColors.TextSecondary, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("SOBRE ${weather.name.orEmpty().uppercase()}", color = WeatherColors.TextSecondary, fontSize = 13.sp, letterSpacing = 1.sp)
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    "Condições atuais: ${weather.descriptionLabel().lowercase()}. " +
                            "Sensação térmica próxima de ${info.feels_like.toCelsius()}°C, " +
                            "com máxima de ${info.temp_max.toCelsius()}° e mínima de ${info.temp_min.toCelsius()}°.",
                    color = WeatherColors.TextPrimary, fontSize = 15.sp, lineHeight = 22.sp
                )
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun DetailCard(
    modifier: Modifier,
    icon: ImageVector,
    label: String,
    value: String,
    subtitle: String
) {
    Column(
        modifier
            .clip(RoundedCornerShape(20.dp))
            .background(WeatherColors.CardGlass)
            .border(1.dp, WeatherColors.CardBorder, RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, null, tint = WeatherColors.TextSecondary, modifier = Modifier.size(16.dp))
            Spacer(Modifier.width(6.dp))
            Text(label, color = WeatherColors.TextSecondary, fontSize = 12.sp, letterSpacing = 1.sp)
        }
        Spacer(Modifier.height(12.dp))
        Text(value, color = WeatherColors.TextPrimary, fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
        Text(subtitle, color = WeatherColors.TextSecondary, fontSize = 13.sp)
    }
}

private fun comfortLabel(humidity: Float): String = when {
    humidity < 30 -> "Seco"
    humidity < 60 -> "Confortável"
    humidity < 80 -> "Úmido"
    else -> "Abafado"
}