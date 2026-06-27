package com.example.findinglogs.view.compose.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findinglogs.model.model.Weather
import com.example.findinglogs.view.compose.components.CityCard
import com.example.findinglogs.view.compose.components.CurrentWeatherSection
import com.example.findinglogs.view.compose.components.WeatherTopBar
import com.example.findinglogs.view.compose.theme.WeatherColors
import com.example.findinglogs.view.compose.theme.backgroundGradient
import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun HomeScreen(
    weatherList: List<Weather>,
    onAddClick: () -> Unit = {},
    onRefreshClick: () -> Unit = {},
    onCityClick: (Weather) -> Unit = {},
    onDeleteCity: (Weather) -> Unit = {}
) {
    val background = if (weatherList.isEmpty())
        WeatherColors.BackgroundGradient
    else
        weatherList.first().backgroundGradient()
    Box(
        Modifier.fillMaxSize().background(background)
    ) {
        if (weatherList.isEmpty()) {
            Column(
                Modifier.fillMaxSize().statusBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = WeatherColors.TextPrimary)
                Spacer(Modifier.height(12.dp))
                Text("Carregando previsões...", color = WeatherColors.TextSecondary)
            }
            return@Box
        }

        val current = weatherList.first()
        val others = weatherList.drop(1)

        LazyColumn(
            modifier = Modifier.fillMaxSize().statusBarsPadding(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                WeatherTopBar(
                    onAddClick = onAddClick,
                    onRefreshClick = onRefreshClick,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            item {
                CurrentWeatherSection(
                    weather = current,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
            item {
                Text(
                    "OUTRAS CIDADES",
                    color = WeatherColors.TextSecondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 2.sp
                )
            }
            itemsIndexed(others) { index, weather ->
                CityCard(
                    weather = weather,
                    onClick = { onCityClick(weather) },
                    onDelete = { onDeleteCity(weather) },
                    isCurrentLocation = index == 0
                )
            }
        }
    }
}