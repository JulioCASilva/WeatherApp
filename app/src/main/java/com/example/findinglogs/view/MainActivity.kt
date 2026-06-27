package com.example.findinglogs.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.findinglogs.model.model.Weather
import com.example.findinglogs.view.compose.home.HomeScreen
import com.example.findinglogs.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
            val weatherList: List<Weather> by viewModel.weatherList
                .observeAsState(emptyList())

            HomeScreen(
                weatherList = weatherList,
                onAddClick = { },
                onRefreshClick = { viewModel.refresh() },
                onCityClick = { }
            )
        }
    }
}