package com.example.findinglogs.view

import android.os.Bundle
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.findinglogs.view.compose.add.AddCityModal
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val strat = System.currentTimeMillis()
        splashScreen.setKeepOnScreenCondition {
            val date = !viewModel.weatherList.value.isNullOrEmpty()
            val timeout = System.currentTimeMillis() - strat > 3000
            !date && !timeout
        }

        setContent {
            val viewModel: MainViewModel = viewModel()
            val weatherList: List<Weather> by viewModel.weatherList.observeAsState(emptyList())
            var selected by remember { mutableStateOf<Weather?>(null) }

            if (selected == null) {
                var showAddSheet by remember { mutableStateOf(false) }
                HomeScreen(
                    weatherList = weatherList,
                    onAddClick = {showAddSheet = true},
                    onRefreshClick = { viewModel.refresh() },
                    onCityClick = { selected = it },
                    onDeleteCity = { weather ->
                        weather.latLon?.let { viewModel.removeCity(it) }
                    }
                )
                if(showAddSheet){
                    AddCityModal(
                        onDismiss = { showAddSheet = false },
                        onAddCity = { city -> viewModel.addCity(city.latLon) }
                    )
                }
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