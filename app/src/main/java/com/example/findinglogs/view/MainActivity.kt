package com.example.findinglogs.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.findinglogs.model.model.Weather
import com.example.findinglogs.view.compose.detail.WeatherDetailScreen
import com.example.findinglogs.view.compose.home.HomeScreen
import com.example.findinglogs.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
            val weatherList: List<Weather> by viewModel.weatherList.observeAsState(emptyList())
            var selected by remember { mutableStateOf<Weather?>(null) }

            if (selected == null) {
                HomeScreen(
                    weatherList = weatherList,
                    onAddClick = { Toast.makeText(this, "Em breve", Toast.LENGTH_SHORT).show() },
                    onRefreshClick = { viewModel.refresh() },
                    onCityClick = { selected = it }
                )
            } else {
                WeatherDetailScreen(
                    weather = selected!!,
                    onBack = { selected = null }
                )
            }
            BackHandler(enabled = selected != null) { selected = null }
        }
    }
}